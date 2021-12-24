package com.example.room_crud_kotlin.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val firstName:String,
    val lastName:String,
    val age:Int)