package com.example.authentication.domain.useCases

class ValidatePassword {
    operator fun invoke(password: String): ValidationResult {
        if(password.length < 8){
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if(containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to contain letters and digits"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}