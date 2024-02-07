package com.example.randomuserapp

import android.content.Context
import android.content.SharedPreferences
import com.example.randomuserapp.retrofit.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object UserPreferences {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_USER_LIST = "user_list"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isUserListSaved(context: Context): Boolean {
        return getSharedPreferences(context).contains(KEY_USER_LIST)
    }

    fun saveUserList(context: Context, userList: List<User>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(userList)
        editor.putString(KEY_USER_LIST, json)
        editor.apply()
    }

    fun getUserList(context: Context): List<User> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString(KEY_USER_LIST, null)
        val type = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}