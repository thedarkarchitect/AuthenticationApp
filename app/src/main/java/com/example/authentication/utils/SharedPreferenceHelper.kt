package com.example.authentication.utils

import android.content.Context

class SharedPreferenceHelper(// this will be used to save the token values and need to sigin, signout and signup
    private val context: Context
) {
    companion object{
        private const val MY_PREF_KEY = "MY_PREF"
    }

    fun saveStringData(key: String, data: String?) {//saves data as a key valuee pair
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)//this uses backward compatibity to secure the data
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getStringData(key: String): String? { //retrieves data using the key
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }
}