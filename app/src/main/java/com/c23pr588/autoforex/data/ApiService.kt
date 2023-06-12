package com.c23pr588.autoforex.data

import com.c23pr588.autoforex.data.traffic.LoginResponse
import com.c23pr588.autoforex.data.traffic.RegistrationResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun login(
        @Body request: Login
    ): Call<LoginResponse>

    @POST("registrations")
    fun registration(
        @Body request: Registration
    ): Call<RegistrationResponse>
}

data class Login (
    val username: String,
    val password: String
)

data class Registration (
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
