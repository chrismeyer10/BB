package com.example.bahnandbike.data.rail.repository

import com.example.bahnandbike.core.model.JourneySegment
import com.example.bahnandbike.core.model.Station
import com.example.bahnandbike.domain.routing.repository.RailRouteRepository

// Provides static rail routing data until a live rail API is connected.
class FakeRailRouteRepository : RailRouteRepository {

    // Creates a preview rail segment between two stations.
    override fun createSegment(
        startStation: Station,
        destinationStation: Station
    ): JourneySegment.RailSegment {
        return JourneySegment.RailSegment(
            title = "${startStation.name} to ${destinationStation.name} by train",
            durationMinutes = 34,
            lineName = "RE Preview"
        )
    }
}
