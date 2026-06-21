package com.pucetec.securitydev.dto

import java.time.LocalDateTime

data class VerificationResponse(
    val id: Long,
    val username: String,
    val status:String,
    val createdAt: LocalDateTime,
    val userId:Long,
    val hotSpotId: Long

)
