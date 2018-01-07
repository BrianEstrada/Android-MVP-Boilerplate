package com.brianestrada.boilerplate.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brianestrada.boilerplate.R
import com.brianestrada.boilerplate.models.User
import kotlinx.android.synthetic.main.recycler_item_user.view.*


class UsersAdapter(private var userList: List<User>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user = user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.itemUserTvUsername.text = user.username
        }
    }
}