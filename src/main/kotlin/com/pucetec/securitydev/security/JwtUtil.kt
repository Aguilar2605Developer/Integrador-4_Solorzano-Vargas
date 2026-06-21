package com.pucetec.securitydev.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtUtil {

    private val secret = Keys.hmacShaKeyFor("securitydev-secret-key-pucetec-2026!!".toByteArray())
    private val expiration = 86400000L // 24 horas

    fun generateToken(email: String, userId: Long): String {
        return Jwts.builder()
            .setSubject(email)
            .claim("userId", userId)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(secret, SignatureAlgorithm.HS256)
            .compact()
    }

    fun extractEmail(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun extractUserId(token: String): Long {
        return Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body
            .get("userId", Integer::class.java)
            .toLong()
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}