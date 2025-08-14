package com.hobeen.kovertest.post

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateRequest(
    @field:NotBlank @field:Size(min = 1, max = 200)
    val title: String,
    @field:NotBlank
    val content: String
)

data class UpdateRequest(
    @field:Size(min = 1, max = 200)
    val title: String? = null,
    val content: String? = null
)

data class DetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val authorId: Long,
    val authorName: String
)

