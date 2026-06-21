package com.pucetec.securitydev.dto

data class VerificationRequest(
    val userId: Long,
    val status: String,
    val hotSpotId: Long,
)
