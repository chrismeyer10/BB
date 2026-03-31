package com.example.bahnandbike.core.model

// Holds the full composed route that the app presents to the user.
data class Journey(
    val title: String,
    val segments: List<JourneySegment>
)
