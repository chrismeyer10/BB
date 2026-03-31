package com.example.bahnandbike

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bahnandbike.core.model.Journey
import com.example.bahnandbike.feature.journey.ui.JourneyScreen
import com.example.bahnandbike.feature.search.ui.SearchScreen
import com.example.bahnandbike.feature.search.ui.SearchUiModel
import com.example.bahnandbike.feature.settings.ui.SettingsScreen

// Renders the top-level shell that connects the first project modules.
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppRoot(
    searchModel: SearchUiModel,
    journey: Journey
) {
    var activeDestination by remember { mutableStateOf(AppDestination.Search) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "BahnAndBike") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar {
                AppDestination.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = activeDestination == destination,
                        onClick = { activeDestination = destination },
                        icon = { Text(text = destination.name.take(1)) },
                        label = { Text(text = destination.name) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            AppDestinationContent(
                activeDestination = activeDestination,
                searchModel = searchModel,
                journey = journey
            )
        }
    }
}

// Renders the selected top-level destination content.
@Composable
fun AppDestinationContent(
    activeDestination: AppDestination,
    searchModel: SearchUiModel,
    journey: Journey
) {
    when (activeDestination) {
        AppDestination.Search -> SearchScreen(model = searchModel)
        AppDestination.Journey -> JourneyScreen(journey = journey)
        AppDestination.Settings -> SettingsScreen(
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}
