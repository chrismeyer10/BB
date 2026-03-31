package com.example.bahnandbike.core.model

// Captures the manual routing input for the first app version.
data class RouteRequest(
    val startPlace: Place,
    val destinationPlace: Place,
    val startStation: Station,
    val destinationStation: Station
)
