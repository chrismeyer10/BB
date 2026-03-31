package com.example.bahnandbike

import com.example.bahnandbike.core.model.Journey
import com.example.bahnandbike.core.model.RouteRequest
import com.example.bahnandbike.data.bike.repository.FakeBikeRouteRepository
import com.example.bahnandbike.data.places.repository.StaticPlaceCatalog
import com.example.bahnandbike.data.rail.repository.FakeRailRouteRepository
import com.example.bahnandbike.domain.routing.composer.DefaultJourneyComposer
import com.example.bahnandbike.domain.routing.usecase.BuildJourneyPreviewUseCase
import com.example.bahnandbike.feature.search.ui.SearchUiModel

// Builds the static first-phase app state that connects the new modules.
object AppStateFactory {

    // Creates the preview route request used by the app shell.
    fun createRouteRequest(): RouteRequest {
        return RouteRequest(
            startPlace = StaticPlaceCatalog.startPlace(),
            destinationPlace = StaticPlaceCatalog.destinationPlace(),
            startStation = StaticPlaceCatalog.startStation(),
            destinationStation = StaticPlaceCatalog.destinationStation()
        )
    }

    // Creates the search screen model from the current route request.
    fun createSearchModel(request: RouteRequest): SearchUiModel {
        return SearchUiModel(
            startName = request.startPlace.name,
            destinationName = request.destinationPlace.name,
            startStationName = request.startStation.name,
            destinationStationName = request.destinationStation.name,
            fallbackLatitude = request.startPlace.latitude,
            fallbackLongitude = request.startPlace.longitude,
            hasMapsApiKey = BuildConfig.HAS_MAPS_API_KEY
        )
    }

    // Creates the journey preview from the current data and domain modules.
    fun createJourney(request: RouteRequest): Journey {
        val useCase = BuildJourneyPreviewUseCase(
            journeyComposer = DefaultJourneyComposer(
                bikeRouteRepository = FakeBikeRouteRepository(),
                railRouteRepository = FakeRailRouteRepository()
            )
        )

        return useCase.execute(request)
    }
}
