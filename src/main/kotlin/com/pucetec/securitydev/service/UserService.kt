package com.pucetec.securitydev.service

import com.pucetec.securitydev.dto.UserRequest
import com.pucetec.securitydev.dto.UserResponse
import com.pucetec.securitydev.mappers.UserMapper
import com.pucetec.securitydev.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {

    // Guarda un nuevo estudiante procesando su formulario de Ionic
    fun registerUser(request: UserRequest): UserResponse {
        val userEntity = userMapper.toEntity(request)
        val savedUser = userRepository.save(userEntity)
        return userMapper.toResponse(savedUser)
    }

    // Devuelve la lista completa de usuarios registrados en el sistema
    fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { user ->
            userMapper.toResponse(user)
        }
    }

    // Busca un estudiante específico mediante su ID único
    fun getUserById(id: Long): UserResponse? {
        val user = userRepository.findById(id).orElse(null) ?: return null
        return userMapper.toResponse(user)
    }

    // Actualiza los datos de un estudiante existente
    fun updateUser(id: Long, request: UserRequest): UserResponse? {
        if (!userRepository.existsById(id)) return null
        val updatedEntity = userMapper.toEntity(request, id)
        val savedUser = userRepository.save(updatedEntity)
        return userMapper.toResponse(savedUser)
    }

    // Elimina un estudiante por su ID
    fun deleteUser(id: Long): Boolean {
        if (!userRepository.existsById(id)) return false
        userRepository.deleteById(id)
        return true
    }
}