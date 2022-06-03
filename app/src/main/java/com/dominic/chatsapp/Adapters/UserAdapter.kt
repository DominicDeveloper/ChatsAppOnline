package com.dominic.chatsapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dominic.chatsapp.ChatFragment
import com.dominic.chatsapp.Models.Users
import com.dominic.chatsapp.R
import com.dominic.chatsapp.databinding.ItemUsersBinding
import kotlinx.android.synthetic.main.item_users.view.*

class UserAdapter(var context: Context, var userList:ArrayList<Users>,val rvClick: RvClick):
        RecyclerView.Adapter<UserAdapter.UserViewHolder>()

    {
        inner class  UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val binding:ItemUsersBinding = ItemUsersBinding.bind(itemView)


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            var v = LayoutInflater.from(context).inflate(R.layout.item_users, parent,false)
            return UserViewHolder(v)

        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val user = userList[position]
            holder.binding.root.item_user_name.text = user.name
            holder.binding.root.item_user_recently_message.text = user.phoneNumber
            Glide.with(context).load(user.profileImage)
                .placeholder(R.drawable.img)
                .into(holder.binding.root.item_user_image)
            holder.itemView.setOnClickListener {
                rvClick.onClick(user)
            }


        }

        override fun getItemCount(): Int = userList.size
        interface RvClick{
            fun onClick(users: Users)
        }

    }
