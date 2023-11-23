package au.com.shiftyjelly.pocketcasts.servers.sync

import au.com.shiftyjelly.pocketcasts.servers.BuildConfig
import au.com.shiftyjelly.pocketcasts.utils.featureflag.Feature
import au.com.shiftyjelly.pocketcasts.utils.featureflag.FeatureFlag
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NamedSettingsSettings(
    @field:Json(name = "skipForward") val skipForward: Int? = null,
    @field:Json(name = "skipBack") val skipBack: Int? = null,
    @field:Json(name = "marketingOptIn") val marketingOptIn: Boolean? = null,
    @field:Json(name = "freeGiftAcknowledgement") val freeGiftAcknowledged: Boolean? = null,
    @field:Json(name = "gridOrder") val gridOrder: Int? = null,
)

@JsonClass(generateAdapter = true)
data class ChangedNamedSettings(
//    @field:Json(name = "grid_layout") val gridLayout: NamedChangedSettingInt? = null,
    @field:Json(name = "gridLayout") val gridLayout: NamedChangedSetting<Int>? = null,
    @field:Json(name = "marketingOptIn") val marketingOptIn: NamedChangedSetting<Boolean>? = null,
)

@JsonClass(generateAdapter = true)
data class NamedChangedSetting<T>(
    @field:Json(name = "value") val value: T,
    // FIXME: could I parse this directly to/from an Instant automatically?
    @field:Json(name = "modified_at") val modifiedAt: String?
) {
    fun <R> mapValue(transform: (T) -> (R)): NamedChangedSetting<R> =
        NamedChangedSetting(
            value = transform(value),
            modifiedAt = modifiedAt,
        )
}

@JsonClass(generateAdapter = true)
data class NamedChangedSettingInt(
    @field:Json(name = "value") val value: Int,
    // FIXME: could I parse this directly to/from an Instant automatically?
    @field:Json(name = "modified_at") val modifiedAt: String // not sure this is actually a string
)

@JsonClass(generateAdapter = true)
data class NamedSettingsRequest(
    @field:Json(name = "m") val m: String = "Android",
    @field:Json(name = "v") val v: Int = 1,
    @field:Json(name = "settings") val settings: NamedSettingsSettings
)

@JsonClass(generateAdapter = true)
data class ChangedNamedSettingsRequest(
    @field:Json(name = "m") val m: String = "Android",
    @field:Json(name = "v") val v: Int = 1,
    @field:Json(name = "changed_settings") val changedSettings: ChangedNamedSettings?
) {
    init {
        if (BuildConfig.DEBUG) {
            require(FeatureFlag.isEnabled(Feature.SETTINGS_SYNC)) {
                "This class should not be used unless sesttings sync is enabled"
            }
        }
    }
}

typealias NamedSettingsResponse = Map<String, SettingResponse>
typealias ChangedNamedSettingsResponse = Map<String, ChangedSettingResponse>

@JsonClass(generateAdapter = true)
data class SettingResponse(
    @field:Json(name = "value") val value: Any,
    @field:Json(name = "changed") val changed: Boolean
)

@JsonClass(generateAdapter = true)
data class ChangedSettingResponse(
    @field:Json(name = "value") val value: Any,
    @field:Json(name = "changed") val changed: Boolean,
    @field:Json(name = "modifiedAt") val modifiedAt: String? = null,
)

interface NamedSettingsCaller {
    suspend fun namedSettings(request: NamedSettingsRequest): NamedSettingsResponse
    suspend fun changedNamedSettings(request: ChangedNamedSettingsRequest): ChangedNamedSettingsResponse
}
