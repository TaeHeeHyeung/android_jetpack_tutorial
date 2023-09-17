package com.example.coroutine

class UserRepository {
    var userList: ArrayList<String> = arrayListOf<String>()
    fun register(s: String) {
        userList.add(s)
    }

    fun getAllUsers(): Any? {
        return userList
    }

}
