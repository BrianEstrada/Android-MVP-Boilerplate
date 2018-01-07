package com.brianestrada.boilerplate.data

import com.brianestrada.boilerplate.data.preferences.Preferences
import com.brianestrada.boilerplate.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.util.*

class DataManagerImpl(private val preferences: Preferences) : DataManager {
    private val SPK_USERS = "SPK_USERS"

    override fun saveUser(user: User) {
        val users = getUsers()

        users.add(user)

        saveUsers(users)
    }

    override fun saveUsers(userList: List<User>) {
        val userListString = Gson().toJson(userList)
        //TODO FIX THIS
        preferences.putString(SPK_USERS, userListString)
    }

    override fun getUsers(): ArrayList<User> {
        val userListString = preferences.getString(SPK_USERS, "")

        Timber.d("User List: %s", userListString)

        val userType = object : TypeToken<List<User>>() {}.type

        return Gson().fromJson(userListString, userType)
    }
}