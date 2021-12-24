package com.example.room_crud_kotlin.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.room_crud_kotlin.R
import com.example.room_crud_kotlin.mvm.UserViewModel
import com.example.room_crud_kotlin.room.Users
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)

          mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

         view.buttonSaveid.setOnClickListener {
             insertData()
         }
        return view
    }

    private fun insertData() {
        val firstname=editTextTextPersonFNameid.text.toString()
        val lastname=editTextTextPersonLastNameid.text.toString()
        val age=editTextTextPersonageid.text

       if (inputcheck(firstname,lastname,age))
       {
         val user=Users(0,firstname,lastname,Integer.parseInt(age.toString()))
           mUserViewModel.addUser(user)
           Toast.makeText(requireContext(),"Successfully addedd data",Toast.LENGTH_LONG).show()
           requireActivity().supportFragmentManager.beginTransaction()
               .replace(R.id.frameLayout, ListFragment()).commit()

       }
        else{
           Toast.makeText(requireContext(),"Please fill all Fields",Toast.LENGTH_LONG).show()
       }

    }
    private fun inputcheck(firstname:String,lastname:String,age:Editable):Boolean {
        return !(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || age.isEmpty())

    }

}