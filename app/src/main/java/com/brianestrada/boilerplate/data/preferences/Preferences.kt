package com.brianestrada.boilerplate.data.preferences

interface Preferences {
    fun getString(key: String, defaultValue: String): String
    fun getInt(key: String, defaultValue: Int): Int
    fun getFloat(key: String, defaultValue: Float): Float
    fun getLong(key: String, defaultValue: Long): Long
    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun putString(key: String, value: String?)
    fun putInt(key: String, value: Int?)
    fun putFloat(key: String, value: Float?)
    fun putLong(key: String, value: Long?)
    fun putBoolean(key: String, value: Boolean?)

    fun clear(key: String)
    fun clearAll()
}