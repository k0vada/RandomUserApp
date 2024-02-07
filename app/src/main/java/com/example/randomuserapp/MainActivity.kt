package com.example.randomuserapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapp.retrofit.RetrofitClient
import com.example.randomuserapp.retrofit.User
import com.example.randomuserapp.retrofit.UserApi
import com.example.randomuserapp.retrofit.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), OnUserClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        userAdapter = UserAdapter(UserPreferences.getUserList(this))

        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter.setOnUserClickListener(this)
        updateButton = findViewById(R.id.ButtonUpdate)
        updateButton.setOnClickListener {
            loadUsers()
        }

        if (!UserPreferences.isUserListSaved(applicationContext))
            loadUsers()
        else {
            val savedUserList = UserPreferences.getUserList(applicationContext)
            userAdapter.updateData(savedUserList)
        }
    }

    override fun onUserClick(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun loadUsers(){
        val retrofit = RetrofitClient.create()
        val userApi = retrofit.create(UserApi::class.java)

        userApi.getUser(50).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val userResponse = response.body()
                val userList = userResponse?.results
                if (userList != null){
                    userAdapter.updateData(userList)
                    UserPreferences.saveUserList(applicationContext, userList)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
            }
        })
    }
}