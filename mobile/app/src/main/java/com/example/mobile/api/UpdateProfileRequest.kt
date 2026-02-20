package com.example.mobile.api

data class UpdateProfileRequest(
    val firstName: String,
    val lastName: String,
    val bio: String,
    val phone: String
)