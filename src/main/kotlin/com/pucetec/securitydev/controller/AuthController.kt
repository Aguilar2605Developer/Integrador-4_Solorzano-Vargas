package com.pucetec.securitydev.controller

import com.pucetec.securitydev.dto.AuthRequest
import com.pucetec.securitydev.dto.AuthResponse
import com.pucetec.securitydev.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = ["*"])
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        val response = authService.login(request)
        return if (response != null) {
            ResponseEntity.ok(response)
        } else {
            ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
    }
}