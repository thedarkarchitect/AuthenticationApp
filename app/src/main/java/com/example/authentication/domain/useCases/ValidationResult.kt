package com.example.authentication.domain.useCases

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
