package com.example.bahnandbike.domain.routing.composer

import com.example.bahnandbike.core.model.Journey
import com.example.bahnandbike.core.model.RouteRequest
import com.example.bahnandbike.domain.routing.repository.BikeRouteRepository
import com.example.bahnandbike.domain.routing.repository.RailRouteRepository

// Composes the first project journey from bike and rail repositories.
class DefaultJourneyComposer(
    private val bikeRouteRepository: BikeRouteRepository,
    private val railRouteRepository: RailRouteRepository
) : JourneyComposer {

    // Creates the first combined bike-train-bike journey.
    override fun compose(request: RouteRequest): Journey {
        val outboundBikeSegment = bikeRouteRepository.createSegment(
            startPlace = request.startPlace,
            destinationStation = request.startStation
        )
        val railSegment = railRouteRepository.createSegment(
            startStation = request.startStation,
            destinationStation = request.destinationStation
        )
        val inboundBikeSegment = bikeRouteRepository.createSegment(
            startStation = request.destinationStation,
            destinationPlace = request.destinationPlace
        )

        return Journey(
            title = "${request.startPlace.name} to ${request.destinationPlace.name}",
            segments = listOf(outboundBikeSegment, railSegment, inboundBikeSegment)
        )
    }
}
