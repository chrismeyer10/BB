package com.example.bahnandbike.core.model

// Represents one leg inside a composed bike-and-rail journey.
sealed interface JourneySegment {
    val title: String
    val durationMinutes: Int

    // Represents a bike leg between two named places.
    data class BikeSegment(
        override val title: String,
        override val durationMinutes: Int,
        val distanceKilometers: Double
    ) : JourneySegment

    // Represents a rail leg between two stations.
    data class RailSegment(
        override val title: String,
        override val durationMinutes: Int,
        val lineName: String
    ) : JourneySegment
}
