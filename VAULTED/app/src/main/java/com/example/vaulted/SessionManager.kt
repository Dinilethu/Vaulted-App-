package com.example.vaulted

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val PREF_NAME = "vaulted_session"
    private val KEY_IS_LOGGED_IN = "isLoggedIn"
    private val KEY_EMAIL = "email"
    private val KEY_USERNAME = "username"
    private val KEY_PASSWORD = "password"

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    // --- Registration data ---
    fun saveUserRegistration(email: String, username: String, password: String) {
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    fun getUserEmail(): String? = pref.getString(KEY_EMAIL, null)
    fun getUsername(): String? = pref.getString(KEY_USERNAME, null)
    fun getUserPassword(): String? = pref.getString(KEY_PASSWORD, null)

    // --- Session data ---
    fun saveUserSession(email: String, username: String) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }

    fun isLoggedIn(): Boolean = pref.getBoolean(KEY_IS_LOGGED_IN, false)

    // --- Logout / clear session ---
    fun logoutUser() {
        editor.clear()
        editor.commit() // or editor.apply() if you prefer async
    }
    fun getUserDetails(): Map<String, String?> {
        val user = HashMap<String, String?>()
        user["email"] = getUserEmail()
        user["username"] = getUsername()
        return user
    }


}
