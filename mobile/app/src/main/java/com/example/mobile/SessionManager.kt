package com.example.mobile

import android.content.Context

class SessionManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun saveLogin(token: String, name: String) {
        prefs.edit()
            .putString("TOKEN", token)
            .putString("NAME", name)
            .apply()
    }

    fun getName(): String? {
        return prefs.getString("NAME", null)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun getToken(): String? {
        return prefs.getString("TOKEN", null)
    }
}