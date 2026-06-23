package com.pucetec.securitydev.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "location_share")
class LocationShare(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(unique = true, nullable = false)
    val shareId: String = UUID.randomUUID().toString(),

    val latitude: Double = 0.0,
    val longitude: Double = 0.0,

    val active: Boolean = true,

    val expiresAt: LocalDateTime = LocalDateTime.now().plusMinutes(10),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    val users: Users? = null
)