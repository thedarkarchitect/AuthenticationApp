package com.example.authentication.domain.useCases

data class Validations(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword
)
