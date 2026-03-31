package com.example.bahnandbike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bahnandbike.core.designsystem.theme.BahnAndBikeTheme

// Hosts the modular BahnAndBike app shell.
class MainActivity : ComponentActivity() {

    // Creates the Compose app shell and injects the first preview state.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val request = AppStateFactory.createRouteRequest()
        val searchModel = AppStateFactory.createSearchModel(request = request)
        val journey = AppStateFactory.createJourney(request = request)

        setContent {
            BahnAndBikeTheme {
                AppRoot(
                    searchModel = searchModel,
                    journey = journey
                )
            }
        }
    }
}
