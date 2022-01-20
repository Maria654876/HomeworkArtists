package com.example.myapplication.service

interface AppPreferences {
    fun isSaveCredentialsSelected(): Boolean
    fun setSaveCredentialsSelected(isSelected: Boolean)
    fun getToken(): String
}