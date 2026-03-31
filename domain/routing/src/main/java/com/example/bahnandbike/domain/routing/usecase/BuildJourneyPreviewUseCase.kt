package com.example.bahnandbike.domain.routing.usecase

import com.example.bahnandbike.core.model.Journey
import com.example.bahnandbike.core.model.RouteRequest
import com.example.bahnandbike.domain.routing.composer.JourneyComposer

// Builds the current preview journey for the app shell.
class BuildJourneyPreviewUseCase(
    private val journeyComposer: JourneyComposer
) {
    // Returns the composed journey for the active request.
    fun execute(request: RouteRequest): Journey {
        return journeyComposer.compose(request)
    }
}
