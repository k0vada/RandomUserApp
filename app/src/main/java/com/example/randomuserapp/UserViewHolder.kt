package com.example.randomuserapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userImageView: ImageView = itemView.findViewById(R.id.imageView_user)
    val fullNameTextView: TextView = itemView.findViewById(R.id.textView_fullName)
    val addressTextView: TextView = itemView.findViewById(R.id.textView_address)
    val phoneTextView: TextView = itemView.findViewById(R.id.textView_phone)
}