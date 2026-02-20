package com.example.mobile.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @POST("api/auth/login")
    fun login(
        @Body request: LoginRequest
    ): Call<AuthResponse>

    @PUT("profile/update")
    fun updateProfile(
        @Header("Authorization") token: String,
        @Body request: UpdateProfileRequest
    ): Call<AuthResponse>

    @GET("profile/me")
    fun getProfile(
        @Header("Authorization") token: String
    ): Call<AuthResponse>

}
