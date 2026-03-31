package com.example.bahnandbike.data.places.repository

import com.example.bahnandbike.core.model.Place
import com.example.bahnandbike.core.model.Station

// Exposes fixed preview places and stations for the first project phase.
object StaticPlaceCatalog {

    // Returns the preview start place shown in the app shell.
    fun startPlace(): Place {
        return Place(
            id = "home",
            name = "Home",
            latitude = 52.52,
            longitude = 13.405
        )
    }

    // Returns the preview destination place shown in the app shell.
    fun destinationPlace(): Place {
        return Place(
            id = "office",
            name = "Office",
            latitude = 52.508,
            longitude = 13.377
        )
    }

    // Returns the preview departure station shown in the app shell.
    fun startStation(): Station {
        return Station(
            id = "berlin-hbf",
            name = "Berlin Hbf",
            city = "Berlin"
        )
    }

    // Returns the preview arrival station shown in the app shell.
    fun destinationStation(): Station {
        return Station(
            id = "potsdam-hbf",
            name = "Potsdam Hbf",
            city = "Potsdam"
        )
    }
}
