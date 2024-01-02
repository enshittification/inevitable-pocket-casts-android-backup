package au.com.shiftyjelly.pocketcasts.servers.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import au.com.shiftyjelly.pocketcasts.helper.BuildConfig
import au.com.shiftyjelly.pocketcasts.models.type.PodcastsSortType
import au.com.shiftyjelly.pocketcasts.preferences.Settings
import au.com.shiftyjelly.pocketcasts.preferences.UserSetting
import au.com.shiftyjelly.pocketcasts.preferences.model.PodcastGridLayoutType
import au.com.shiftyjelly.pocketcasts.utils.featureflag.Feature
import au.com.shiftyjelly.pocketcasts.utils.featureflag.FeatureFlag
import au.com.shiftyjelly.pocketcasts.utils.log.LogBuffer
import timber.log.Timber
import java.lang.RuntimeException

class SyncSettingsTask(val context: Context, val parameters: WorkerParameters) : CoroutineWorker(context, parameters) {
    companion object {
        suspend fun run(settings: Settings, namedSettingsCall: NamedSettingsCaller): Result {
            try {
                if (FeatureFlag.isEnabled(Feature.SETTINGS_SYNC)) {
                    syncSettings(settings, namedSettingsCall)
                } else {
                    @Suppress("DEPRECATION")
                    oldSyncSettings(settings, namedSettingsCall)
                }
            } catch (e: Exception) {
                LogBuffer.e(LogBuffer.TAG_BACKGROUND_TASKS, e, "Sync settings failed")
                return Result.failure()
            }

            listOf(
                settings.freeGiftAcknowledged,
                settings.marketingOptIn,
                settings.podcastsSortType,
                settings.skipBackInSecs,
                settings.skipForwardInSecs,
            ).forEach {
                it.modifiedAtTimeForSync = null
            }
            LogBuffer.i(LogBuffer.TAG_BACKGROUND_TASKS, "Settings synced")

            return Result.success()
        }

        private suspend fun syncSettings(
            settings: Settings,
            namedSettingsCall: NamedSettingsCaller,
        ) {

            if (!FeatureFlag.isEnabled(Feature.SETTINGS_SYNC)) {
                LogBuffer.e(LogBuffer.TAG_INVALID_STATE, "syncSettings method should never be called if settings sync flag is not enabled")
                if (BuildConfig.DEBUG) throw RuntimeException("syncSettings method should never be called if settings sync flag is not enabled")
                return
            }

            val request = ChangedNamedSettingsRequest(
                changedSettings = ChangedNamedSettings(
                    gridLayout = settings.podcastGridLayout.toNamedChangedSetting()
                        ?.mapValue { it.id },
                    marketingOptIn = settings.marketingOptIn.toNamedChangedSetting()
                )
            )
            val response = namedSettingsCall.changedNamedSettings(request)
            for ((key, value) in response) {
                when (key) {
                    "gridLayout" -> saveSettingFromResponse(key, value, settings.podcastGridLayout) {
                        val intValue = (value.value as Number).toInt()
                        PodcastGridLayoutType.fromId(intValue)
                    }
                    "marketingOptIn" -> saveSettingFromResponse(key, value, settings.marketingOptIn) {
                        it.value as Boolean
                    }
                }
            }
        }

        private fun <T> saveSettingFromResponse(
            nameForLogs: String,
            changedSettingResponse: ChangedSettingResponse,
            userSetting: UserSetting<T>,
            extractValue: (ChangedSettingResponse) -> T
        ) {
            try {
                userSetting.set(
                    value = extractValue(changedSettingResponse),
                    needsSync = false,
                )
            } catch (e: Exception) {
                LogBuffer.e(LogBuffer.TAG_INVALID_STATE, "Invalid $nameForLogs value: ${changedSettingResponse.value}")
            }
        }

        @Deprecated("This should be removed when we remove the Feature.SETTINGS_SYNC feature flag")
        private suspend fun oldSyncSettings(
            settings: Settings,
            namedSettingsCall: NamedSettingsCaller,
        ) {
            val request = NamedSettingsRequest(
                settings = NamedSettingsSettings(
                    skipForward = settings.skipForwardInSecs.getSyncValue(),
                    skipBack = settings.skipBackInSecs.getSyncValue(),
                    marketingOptIn = settings.marketingOptIn.getSyncValue(),
                    freeGiftAcknowledged = settings.freeGiftAcknowledged.getSyncValue(),
                    gridOrder = settings.podcastsSortType.getSyncValue()?.serverId,
                )
            )

            val response = namedSettingsCall.namedSettings(request)

            for ((key, value) in response) {
                if (value.changed) {
                    Timber.d("$key changed to ${value.value}")

                    if (value.value is Number) { // Probably will have to change this when we do other settings, but for now just Number is fine
                        when (key) {
                            "skipForward" -> settings.skipForwardInSecs.set(value.value.toInt())
                            "skipBack" -> settings.skipBackInSecs.set(value.value.toInt())
                            "gridOrder" -> {
                                val sortType = PodcastsSortType.fromServerId(value.value.toInt())
                                settings.podcastsSortType.set(sortType)
                            }
                        }
                    } else if (value.value is Boolean) {
                        when (key) {
                            "marketingOptIn" -> settings.marketingOptIn.set(value.value)
                            "freeGiftAcknowledgement" -> settings.freeGiftAcknowledged.set(value.value)
                        }
                    }
                } else {
                    Timber.d("$key not changed")
                }
            }
        }
    }

    lateinit var settings: Settings
    lateinit var namedSettingsCaller: NamedSettingsCaller

    override suspend fun doWork(): Result {
        return run(settings, namedSettingsCaller)
    }
}

private fun <T> UserSetting<T>.toNamedChangedSetting(): NamedChangedSetting<T>? =
    // Only create an updated setting if the modifiedAt time is not null
    modifiedAtTimeForSync?.let { modifiedAt ->
        NamedChangedSetting(
            value = value,
            modifiedAt = modifiedAt,
        )
    }
