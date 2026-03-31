package com.example.bahnandbike.feature.journey.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bahnandbike.core.model.Journey
import com.example.bahnandbike.core.model.JourneySegment

// Renders the composed journey and its individual route segments.
@Composable
fun JourneyScreen(journey: Journey, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        JourneyHeader(title = journey.title)
        journey.segments.forEach { segment ->
            JourneySegmentCard(segment = segment)
        }
    }
}

// Renders the journey screen header for the active route.
@Composable
fun JourneyHeader(title: String) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "Journey Result",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// Renders one segment of the composed bike-and-rail route.
@Composable
fun JourneySegmentCard(segment: JourneySegment) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = segment.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = buildSegmentMeta(segment = segment),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Builds the short meta text for one segment card.
fun buildSegmentMeta(segment: JourneySegment): String {
    return when (segment) {
        is JourneySegment.BikeSegment -> {
            "${segment.durationMinutes} min • ${segment.distanceKilometers} km bike"
        }
        is JourneySegment.RailSegment -> {
            "${segment.durationMinutes} min • ${segment.lineName}"
        }
    }
}
