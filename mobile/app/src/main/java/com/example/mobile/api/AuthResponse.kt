package com.example.mobile.api

data class AuthResponse(
    val success: Boolean,
    val message: String,
    val token: String?,
    val userId: Long?,
    val firstName: String?,
    val lastName: String?
)
