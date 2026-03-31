package com.example.bahnandbike.core.common.result

// Represents a simple project-wide success or failure result.
sealed interface AppResult<out T> {
    // Holds a successful value returned by a use case or repository.
    data class Success<T>(val value: T) : AppResult<T>

    // Holds a simple error message returned by a use case or repository.
    data class Error(val message: String) : AppResult<Nothing>
}
