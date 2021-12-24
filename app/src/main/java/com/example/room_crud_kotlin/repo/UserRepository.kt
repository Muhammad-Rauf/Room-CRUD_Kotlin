package com.example.room_crud_kotlin.repo

import androidx.lifecycle.LiveData
import com.example.room_crud_kotlin.room.UserDao
import com.example.room_crud_kotlin.room.Users

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<Users>> = userDao.readAllData()

    suspend fun addUser(user: Users) {
        userDao.adduser(user)
    }

        suspend fun deleteUser(user: Users){
        userDao.deleteUser(user)
    }
    fun searchByID(id: Int) = userDao.searchByID(id)


}