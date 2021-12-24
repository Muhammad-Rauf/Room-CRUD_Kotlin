package com.example.room_crud_kotlin.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun adduser(user: Users)

    @Delete
    suspend fun deleteUser(user: Users)

    @Query("SELECT * FROM user_table WHERE id LIKE :id LIMIT 1")
   fun searchByID(id : Int) : Users

    @Query("SELECT * FROM user_table")
  //  @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Users>>
}