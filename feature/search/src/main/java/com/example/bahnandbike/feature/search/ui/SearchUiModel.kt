package com.example.bahnandbike.feature.search.ui

// Holds the visible search input summary for the first project phase.
data class SearchUiModel(
    val startName: String,
    val destinationName: String,
    val startStationName: String,
    val destinationStationName: String,
    val fallbackLatitude: Double,
    val fallbackLongitude: Double,
    val hasMapsApiKey: Boolean
)
