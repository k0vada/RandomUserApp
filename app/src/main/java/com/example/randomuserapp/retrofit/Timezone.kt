package com.example.randomuserapp.retrofit

import java.io.Serializable

data class Timezone(
    val offset: String,
    val description: String
): Serializable
