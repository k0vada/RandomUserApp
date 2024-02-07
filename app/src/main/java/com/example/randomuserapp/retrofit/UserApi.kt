package com.example.randomuserapp.retrofit
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface UserApi {
    @GET("/api")
    fun getUser(@Query("results") count: Int): Call<UserResponse>
}
