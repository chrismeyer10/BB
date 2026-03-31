package com.example.bahnandbike.core.model

// Describes a physical place that can be used as a start or destination.
data class Place(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double
)
