package com.hobeen.kovertest.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignupRequest(
    @field:NotBlank @field:Size(min = 3, max = 30)
    val username: String,
    @field:NotBlank @field:Size(min = 6, max = 100)
    val password: String,
    @field:NotBlank @field:Size(min = 1, max = 50)
    val displayName: String,
    @field:Email
    val email: String? = null
)

data class LoginRequest(
    @field:NotBlank
    val username: String,
    @field:NotBlank
    val password: String
)

data class MeResponse(
    val id: Long,
    val username: String,
    val displayName: String,
    val email: String?
)

data class UpdateRequest(
    @field:Size(min = 1, max = 50)
    val displayName: String? = null,
    @field:Email
    val email: String? = null,
    @field:Size(min = 6, max = 100)
    val password: String? = null
)

