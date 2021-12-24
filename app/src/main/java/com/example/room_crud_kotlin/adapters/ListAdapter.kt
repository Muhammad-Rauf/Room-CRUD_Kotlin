package com.example.room_crud_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.room_crud_kotlin.R
import com.example.room_crud_kotlin.room.Users
import com.example.room_crud_kotlin.fragments.UpdateDataFragment
import kotlinx.android.synthetic.main.custom_row.view.*


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyviewHolderClass>() {
    private var userList = emptyList<Users>()

    class MyviewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAdapter.MyviewHolderClass {
        return MyviewHolderClass(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ListAdapter.MyviewHolderClass, position: Int) {
        val currentitem = userList[position]
        holder.itemView.userid.text = currentitem.id.toString()
        holder.itemView.userFnameid.text = currentitem.firstName
        holder.itemView.userLnameid.text = currentitem.lastName
        holder.itemView.userageid.text = currentitem.age.toString()

        holder.itemView.card_viewid.setOnClickListener {
            var fragmentB: UpdateDataFragment? = null
            with(currentitem) {
                fragmentB = UpdateDataFragment(id)
            }
            val appCompatActivity = it.context as AppCompatActivity
            fragmentB?.let { fragment ->
                appCompatActivity.supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    fun setData(user: List<Users>) {
        this.userList = user
        notifyDataSetChanged()
    }


}