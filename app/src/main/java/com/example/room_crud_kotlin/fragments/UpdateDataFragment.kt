package com.example.room_crud_kotlin.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.room_crud_kotlin.R
import com.example.room_crud_kotlin.mvm.UserViewModel
import com.example.room_crud_kotlin.room.Users
import kotlinx.android.synthetic.main.fragment_update_data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateDataFragment(private val userId: Int) : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
   // lateinit var user: Users
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_update_data, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            var user: Users = mUserViewModel.searchByID(userId)
            lifecycleScope.launch(Dispatchers.Main) {
                updatePersonFNameid.setText(user.firstName)
                updatePersonLastNameid.setText(user.lastName)
                updatePersonAgeid.setText(user.age.toString())
            }
            btnupdateid.setOnClickListener {
                val firstname=updatePersonFNameid.text.toString()
                val lastname=updatePersonLastNameid.text.toString()
                val age=updatePersonAgeid.text
                if (inputcheck(firstname,lastname,age)) {
                    val updateuser = Users(userId, firstname, lastname, Integer.parseInt(age.toString()))
                    mUserViewModel.addUser(updateuser)
                    Toast.makeText(requireContext(), " Data are Insert Successfully ", Toast.LENGTH_LONG)
                        .show()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, ListFragment()).commit()
                }
                else{
                    Toast.makeText(requireContext()," Field may Not Empty ",Toast.LENGTH_LONG).show()
                }
            }
            //////////////////
            btndeleteID.setOnClickListener {
                val firstname=updatePersonFNameid.text.toString()
                val lastname=updatePersonLastNameid.text.toString()
                val age=updatePersonAgeid.text
                val updateuser=Users(userId,firstname,lastname,Integer.parseInt(age.toString()))
                mUserViewModel.deleteUser(updateuser)
                Toast.makeText(requireContext()," Data are Delete Successfully ",Toast.LENGTH_LONG).show()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ListFragment()).commit()
            }
        }

    }

    private fun inputcheck(firstname:String,lastname:String,age: Editable):Boolean {
        return !(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || age.isEmpty())

    }
}

