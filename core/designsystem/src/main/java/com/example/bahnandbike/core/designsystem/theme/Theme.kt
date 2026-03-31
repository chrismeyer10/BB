package com.example.bahnandbike.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = NightPrimary,
    secondary = NightSecondary,
    tertiary = NightAccent,
    background = NightBackground,
    surface = NightSurface,
    surfaceVariant = NightSurfaceMuted,
    onPrimary = NightBackground,
    onSecondary = NightBackground,
    onTertiary = NightBackground,
    onBackground = NightOnSurface,
    onSurface = NightOnSurface,
    onSurfaceVariant = NightOnSurfaceMuted,
    outline = NightOutline
)

// Applies the shared BahnAndBike Material theme to app content.
@Composable
fun BahnAndBikeTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = BahnAndBikeTypography,
        content = content
    )
}
