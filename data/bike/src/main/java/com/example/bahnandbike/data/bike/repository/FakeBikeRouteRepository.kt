package com.example.bahnandbike.data.bike.repository

import com.example.bahnandbike.core.model.JourneySegment
import com.example.bahnandbike.core.model.Place
import com.example.bahnandbike.core.model.Station
import com.example.bahnandbike.domain.routing.repository.BikeRouteRepository

// Provides static bike routing data until a real provider is integrated.
class FakeBikeRouteRepository : BikeRouteRepository {

    // Creates a preview bike segment from a place to a station.
    override fun createSegment(
        startPlace: Place,
        destinationStation: Station
    ): JourneySegment.BikeSegment {
        return JourneySegment.BikeSegment(
            title = "${startPlace.name} to ${destinationStation.name} by bike",
            durationMinutes = 14,
            distanceKilometers = 3.8
        )
    }

    // Creates a preview bike segment from a station to a place.
    override fun createSegment(
        startStation: Station,
        destinationPlace: Place
    ): JourneySegment.BikeSegment {
        return JourneySegment.BikeSegment(
            title = "${startStation.name} to ${destinationPlace.name} by bike",
            durationMinutes = 11,
            distanceKilometers = 2.9
        )
    }
}
