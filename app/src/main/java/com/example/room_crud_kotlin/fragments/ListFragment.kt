package com.example.room_crud_kotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.room_crud_kotlin.R
import kotlinx.android.synthetic.main.fragment_list.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_crud_kotlin.mvm.UserViewModel


class ListFragment : Fragment() {
    private lateinit var mUserViewModel2: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)
        //Recyclerview
        val adapter= com.example.room_crud_kotlin.adapters.ListAdapter()

        val recyclerView=view.recyclerViewid
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        //

        mUserViewModel2=ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel2.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        view.floatingActionButtonid.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AddFragment())
                .addToBackStack(null).commit()
        }
        return view
    }

}