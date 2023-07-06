package com.itproger.note.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(private val context: Context) {

    private val pref by lazy {
        context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    fun isUserSeen(): Boolean {
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun saveSeen() {
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    fun saveName(name: String) {
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String? {
        return pref.getString(NAME_KEY, "")
    }

    fun saveLastname(lastname: String) {
        pref.edit().putString(LASTNAME_KEY, lastname).apply()
    }

    fun getLastname(): String? {
        return pref.getString(LASTNAME_KEY, "")
    }
    fun savePhoto(photo: String) {
        pref.edit().putString(PHOTO_KEY, photo).apply()
    }
    fun getPhoto(): String? {
        return pref.getString(PHOTO_KEY, "")
    }


    companion object {
        const val PREF_NAME = "task.pref"
        const val SEEN_KEY = "user_key"
        const val NAME_KEY = "name_key"
        const val LASTNAME_KEY = "lastname_key"
        const val PHOTO_KEY = "photo_key"
    }

}