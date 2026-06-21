package com.pucetec.securitydev.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

//Con Entity indicamos a Kotlin que esto es una tabla en la base de datos.
@Entity
@Table(name = "users")
class Users(
    @Id
    //Definimos un Id autoincremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String = "",
    val email: String = "",
    val number: String ="",
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val hotSpots: MutableList<HotSpot> = mutableListOf()
)