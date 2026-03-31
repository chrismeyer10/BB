package com.example.bahnandbike.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = SlateBlue,
    secondary = TealGreen,
    tertiary = WarmOrange,
    background = Paper,
    surface = CardWhite,
    surfaceVariant = SkyTint,
    onPrimary = CardWhite,
    onSecondary = CardWhite,
    onTertiary = CardWhite,
    onBackground = Ink,
    onSurface = Ink,
    onSurfaceVariant = SoftInk
)

private val DarkColorScheme = darkColorScheme(
    primary = SkyTint,
    secondary = MintTint,
    tertiary = CreamTint,
    background = Ink,
    surface = Ink.copy(alpha = 0.88f),
    surfaceVariant = SoftInk.copy(alpha = 0.32f),
    onPrimary = Ink,
    onSecondary = Ink,
    onTertiary = Ink,
    onBackground = CardWhite,
    onSurface = CardWhite,
    onSurfaceVariant = CardWhite.copy(alpha = 0.74f)
)

// Applies the shared BahnAndBike Material theme to app content.
@Composable
fun BahnAndBikeTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = BahnAndBikeTypography,
        content = content
    )
}
