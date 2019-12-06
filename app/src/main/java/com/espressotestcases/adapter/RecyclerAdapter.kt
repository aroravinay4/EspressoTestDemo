package com.espressotestcases.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.espressotestcases.R
import com.espressotestcases.UserModel
import com.espressotestcases.activity.UserDetailActivity

class RecyclerAdapter(val context: Context, val userList: ArrayList<UserModel>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra("userName", userList.get(position).name)
            context.startActivity(intent)

        }

    }


    override fun getItemCount(): Int {
        return userList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: UserModel) {
            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
            textViewName.text = user.name
        }
    }
}