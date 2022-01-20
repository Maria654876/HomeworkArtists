package com.example.myapplication.service

import android.content.Context
import android.content.SharedPreferences

class AppPreferencesImpl private constructor() : AppPreferences {

    companion object {
        private const val PREFERENCE_NAME = "AppPreferences"
        private const val PREFERENCE_IS_SAVE_CREDENTIALS_SELECTED = "PREFERENCE_IS_SAVE_CREDENTIALS_SELECTED"
        private const val PREFERENCE_TOKEN = "PREFERENCE_TOKEN"

        private var instance: AppPreferences? = null
        private var preferences: SharedPreferences? = null

        fun getInstance(context: Context): AppPreferences {
            if (instance == null) {
                instance = AppPreferencesImpl()
                preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            }
            return instance!!
        }
    }

    override fun isSaveCredentialsSelected(): Boolean {
        return preferences?.getBoolean(PREFERENCE_IS_SAVE_CREDENTIALS_SELECTED, false) ?: false
    }

    override fun setSaveCredentialsSelected(isSelected: Boolean) =
        preferences?.edit()?.putBoolean(PREFERENCE_IS_SAVE_CREDENTIALS_SELECTED, isSelected)?.apply() ?: Unit

    override fun getToken(): String = preferences?.getString(PREFERENCE_TOKEN, "") ?: ""

}