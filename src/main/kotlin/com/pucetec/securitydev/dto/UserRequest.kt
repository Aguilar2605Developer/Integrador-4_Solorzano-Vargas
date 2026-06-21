package com.pucetec.securitydev.dto

data class UserRequest(
    val name: String,
    val email: String,
    val number: String,
    val password: String
)