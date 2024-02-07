package com.example.randomuserapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapp.retrofit.User
import com.squareup.picasso.Picasso

class UserAdapter(private var userList: List<User>) : RecyclerView.Adapter<UserViewHolder>(){

    private var onUserClickListener: OnUserClickListener? = null

    fun setOnUserClickListener(listener: OnUserClickListener) {
        this.onUserClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]

        holder.fullNameTextView.text = "${currentUser.name.first} ${currentUser.name.last}"
        holder.addressTextView.text = currentUser.location.city
        holder.phoneTextView.text = currentUser.phone
        Picasso.get().load(currentUser.picture.large).into(holder.userImageView)

        holder.itemView.setOnClickListener {
            onUserClickListener?.onUserClick(currentUser)
        }
    }

    fun updateData(newList: List<User>) {
        userList = newList
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}