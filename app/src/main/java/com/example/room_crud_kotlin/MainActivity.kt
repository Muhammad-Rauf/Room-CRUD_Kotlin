package com.example.room_crud_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment
import com.example.room_crud_kotlin.fragments.AddFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myFragment= com.example.room_crud_kotlin.fragments.ListFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,myFragment)
    .commit()


    }
}