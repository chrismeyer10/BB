package com.example.bahnandbike.domain.routing.repository

import com.example.bahnandbike.core.model.JourneySegment
import com.example.bahnandbike.core.model.Station

// Provides the rail segment between two selected stations.
interface RailRouteRepository {
    // Creates a rail segment between the two chosen stations.
    fun createSegment(startStation: Station, destinationStation: Station): JourneySegment.RailSegment
}
