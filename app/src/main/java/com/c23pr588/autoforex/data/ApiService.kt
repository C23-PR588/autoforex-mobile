package com.c23pr588.autoforex.data

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
//    @POST("login")
//    fun login(
//        @Body request: Login
//    ): Call<LoginResponse>
//
//    @POST("register")
//    fun register(
//        @Body request: Register
//    ): Call<RegisterResponse>
//
//    @GET("stories")
//    fun getStory(
//        @Header("Authorization") token: String
//    ): Call<StoryResponse>
//
//    @GET("stories")
//    suspend fun getAllStories(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int? = null,
//        @Query("size") size: Int? = null,
//        @Query("location") location: Int? = null
//    ): StoryResponse
//
//    @GET("stories")
//    fun getAllStoriesWithLocation(
//        @Header("Authorization") token: String,
//        @Query("location") location: Int? = null
//    ): Call<StoryResponse>
//
//    @Multipart
//    @POST("stories")
//    fun uploadImage(
//        @Header("Authorization") token: String,
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//    ): Call<UploadResponse>
}