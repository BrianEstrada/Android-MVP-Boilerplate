package com.brianestrada.boilerplate.data.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences

@SuppressLint("ApplySharedPref")
class PreferencesImpl(private val sharedPreferences: SharedPreferences) : Preferences {
    override fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun putString(key: String, value: String?) {
        if (value != null) {
            sharedPreferences.edit()
                    .putString(key, value)
                    .commit()
        }
    }

    override fun putInt(key: String, value: Int?) {
        if (value != null) {
            sharedPreferences.edit()
                    .putInt(key, value)
                    .commit()
        }
    }

    override fun putFloat(key: String, value: Float?) {
        if (value != null) {
            sharedPreferences.edit()
                    .putFloat(key, value)
                    .commit()
        }
    }

    override fun putLong(key: String, value: Long?) {
        if (value != null) {
            sharedPreferences.edit()
                    .putLong(key, value)
                    .commit()
        }
    }

    override fun putBoolean(key: String, value: Boolean?) {
        if (value != null) {
            sharedPreferences.edit()
                    .putBoolean(key, value)
                    .commit()
        }
    }

    override fun clear(key: String) {
        sharedPreferences.edit()
                .remove(key)
                .commit()
    }


    override fun clearAll() {
        sharedPreferences.edit()
                .clear()
                .commit()
    }

}