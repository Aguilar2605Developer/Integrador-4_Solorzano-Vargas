package com.pucetec.securitydev.controller

import com.pucetec.securitydev.dto.LocationShareRequest
import com.pucetec.securitydev.dto.LocationShareResponse
import com.pucetec.securitydev.service.EmailService
import com.pucetec.securitydev.service.LocationShareService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/location-shares")
class LocationShareController(
    private val locationShareService: LocationShareService,
    private val emailService: EmailService
) {

    data class ShareEmailRequest(val email: String)

    // POST /api/location-shares — inicia el compartir ubicación (requiere JWT)
    @PostMapping
    fun startSharing(@RequestBody request: LocationShareRequest): ResponseEntity<LocationShareResponse> {
        val response = locationShareService.startSharing(request)
        return ResponseEntity.ok(response)
    }

    // PUT /api/location-shares/{shareId} — actualiza la posición (requiere JWT)
    @PutMapping("/{shareId}")
    fun updateLocation(
        @PathVariable shareId: String,
        @RequestBody request: LocationShareRequest
    ): ResponseEntity<LocationShareResponse> {
        val response = locationShareService.updateLocation(shareId, request.latitude, request.longitude)
        return ResponseEntity.ok(response)
    }

    // PUT /api/location-shares/{shareId}/stop — detiene el compartir (requiere JWT)
    @PutMapping("/{shareId}/stop")
    fun stopSharing(@PathVariable shareId: String): ResponseEntity<LocationShareResponse> {
        val response = locationShareService.stopSharing(shareId)
        return ResponseEntity.ok(response)
    }

    // GET /api/location-shares/{shareId} — público, lo usa el contacto sin login
    @GetMapping("/{shareId}")
    fun getByShareId(@PathVariable shareId: String): ResponseEntity<LocationShareResponse> {
        val response = locationShareService.getByShareId(shareId)
        return ResponseEntity.ok(response)
    }

    // POST /api/location-shares/{shareId}/share-email — público, envía el correo con el link
    @PostMapping("/{shareId}/share-email")
    fun sendShareEmail(
        @PathVariable shareId: String,
        @RequestBody request: ShareEmailRequest
    ): ResponseEntity<String> {
        val shareResponse = locationShareService.getByShareId(shareId)

        emailService.sendLocationShareEmail(
            toEmail = request.email,
            username = shareResponse.username ?: "Usuario",
            shareId = shareId
        )

        return ResponseEntity.ok("Correo enviado a ${request.email}")
    }
}