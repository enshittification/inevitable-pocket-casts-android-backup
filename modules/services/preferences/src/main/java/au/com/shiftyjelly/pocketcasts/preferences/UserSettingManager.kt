package au.com.shiftyjelly.pocketcasts.preferences

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSettingManager @Inject constructor() {

    private var userSettings: MutableList<UserSetting<*>> = mutableListOf()

    fun addUserSetting(userSetting: UserSetting<*>) {
        userSettings.add(userSetting)
        // This UserSetting instance gets passed into this method during construction of the
        // abstract UserSetting superclass. That means that, in theory, the UserSetting might
        // not yet be fully constructed at this point. For that reason, we should be cautious
        // about directly manipulating the UserSetting object in this method.
    }

    fun onSignOut() {
        userSettings.forEach {
            it.modifiedAt = null
        }
    }
}
