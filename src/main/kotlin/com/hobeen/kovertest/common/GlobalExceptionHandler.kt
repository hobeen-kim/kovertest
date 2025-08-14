package com.hobeen.kovertest.common

import jakarta.persistence.EntityNotFoundException
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity(ApiResponse(false, null, e.message), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFound(e: EntityNotFoundException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity(ApiResponse(false, null, e.message), HttpStatus.NOT_FOUND)

    @ExceptionHandler(SecurityException::class)
    fun handleForbidden(e: SecurityException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity(ApiResponse(false, null, e.message), HttpStatus.FORBIDDEN)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Unit>> {
        val msg = e.bindingResult.fieldErrors.joinToString(", ") { "${it.field}: ${it.defaultMessage}" }
        return ResponseEntity(ApiResponse(false, null, msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(e: ConstraintViolationException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity(ApiResponse(false, null, e.message), HttpStatus.BAD_REQUEST)
}
