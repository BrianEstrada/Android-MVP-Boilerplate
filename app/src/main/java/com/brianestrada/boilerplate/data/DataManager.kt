package com.brianestrada.boilerplate.data

import com.brianestrada.boilerplate.models.User

interface DataManager {
     fun saveUser(user: User)

     fun saveUsers(userList: List<User>)

     fun getUsers(): List<User>
}