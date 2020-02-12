package com.asligresik.absensi

import android.content.Context
import com.asligresik.absensi.data.model.LoggedInUser

/**
 * Created by sidiqpermana on 11/17/16.
 */

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val DISPLAY_NAME = "display_name"
        private const val USERID = "user_id"
        private const val EMAIL = "email"
        private const val TOKEN = "token"
        private const val IS_LOGGED = "is_logged"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(value: LoggedInUser) {
        val editor = preferences.edit()
        editor.putString(DISPLAY_NAME, value.displayName)
        editor.putString(USERID, value.userId)
        editor.putString(EMAIL, value.email)
        editor.putString(TOKEN, value.token)
        editor.putBoolean(IS_LOGGED, value.isLogged)
        editor.apply()
    }

    fun getUser(): LoggedInUser {
        val model = LoggedInUser()
        model.displayName = preferences.getString(DISPLAY_NAME, "")
        model.userId = preferences.getString(USERID, "")
        model.email = preferences.getString(EMAIL, "")
        model.token = preferences.getString(TOKEN, "")
        model.isLogged = preferences.getBoolean(IS_LOGGED, false)

        return model
    }
}
