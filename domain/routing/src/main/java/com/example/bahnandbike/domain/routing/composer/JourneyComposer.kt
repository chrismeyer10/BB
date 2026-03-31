package com.example.bahnandbike.domain.routing.composer

import com.example.bahnandbike.core.model.Journey
import com.example.bahnandbike.core.model.RouteRequest

// Builds one composed journey from manual routing input.
interface JourneyComposer {
    // Creates the full journey from the current request.
    fun compose(request: RouteRequest): Journey
}
