package com.example.bahnandbike.domain.routing.repository

import com.example.bahnandbike.core.model.JourneySegment
import com.example.bahnandbike.core.model.Place
import com.example.bahnandbike.core.model.Station

// Provides bike route segments for the composed journey.
interface BikeRouteRepository {
    // Creates a bike segment between a place and a station.
    fun createSegment(startPlace: Place, destinationStation: Station): JourneySegment.BikeSegment

    // Creates a bike segment between a station and a place.
    fun createSegment(startStation: Station, destinationPlace: Place): JourneySegment.BikeSegment
}
