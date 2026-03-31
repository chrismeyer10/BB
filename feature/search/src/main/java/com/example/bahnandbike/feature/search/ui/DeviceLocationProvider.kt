package com.example.bahnandbike.feature.search.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.bahnandbike.feature.search.R
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.function.Consumer
import kotlin.coroutines.resume

// Loads the device map position and falls back to the last known location when needed.
class DeviceLocationProvider(
    private val context: Context
) {

    // Reports whether the app can read device location.
    fun hasLocationPermission(): Boolean {
        return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) ||
            hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    // Returns the best map position for the current device state.
    suspend fun loadMapLocation(model: SearchUiModel): MapLocationUiState {
        if (!hasLocationPermission()) {
            return fallbackLocation(
                model = model,
                label = context.getString(R.string.map_permission_location)
            )
        }

        val locationManager = context.getSystemService(LocationManager::class.java)
        val currentLocation = loadCurrentLocation(locationManager = locationManager)

        if (currentLocation != null) {
            return currentLocation.toUiState(
                label = context.getString(R.string.map_live_location)
            )
        }

        val lastKnownLocation = loadLastKnownLocation(locationManager = locationManager)

        if (lastKnownLocation != null) {
            return lastKnownLocation.toUiState(
                label = context.getString(R.string.map_last_location)
            )
        }

        return fallbackLocation(
            model = model,
            label = context.getString(R.string.map_preview_location)
        )
    }

    // Loads a fresh device location when location services are enabled.
    @SuppressLint("MissingPermission")
    private suspend fun loadCurrentLocation(locationManager: LocationManager): Location? {
        if (!locationManager.isLocationEnabled) {
            return null
        }

        val provider = selectCurrentProvider(locationManager = locationManager) ?: return null

        return suspendCancellableCoroutine { continuation ->
            locationManager.getCurrentLocation(
                provider,
                null,
                context.mainExecutor,
                Consumer { location ->
                    if (continuation.isActive) {
                        continuation.resume(location)
                    }
                }
            )
        }
    }

    // Loads the most useful last known location from the device providers.
    @SuppressLint("MissingPermission")
    private fun loadLastKnownLocation(locationManager: LocationManager): Location? {
        val providerNames = listOf(
            LocationManager.GPS_PROVIDER,
            LocationManager.NETWORK_PROVIDER,
            LocationManager.PASSIVE_PROVIDER
        )

        return providerNames
            .filter(locationManager.allProviders::contains)
            .mapNotNull(locationManager::getLastKnownLocation)
            .maxByOrNull(Location::getTime)
    }

    // Selects the best provider for a fresh location request.
    private fun selectCurrentProvider(locationManager: LocationManager): String? {
        return when {
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> {
                LocationManager.GPS_PROVIDER
            }
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> {
                LocationManager.NETWORK_PROVIDER
            }
            else -> null
        }
    }

    // Builds a preview map location when no device location can be used.
    private fun fallbackLocation(model: SearchUiModel, label: String): MapLocationUiState {
        return MapLocationUiState(
            latitude = model.fallbackLatitude,
            longitude = model.fallbackLongitude,
            label = label
        )
    }

    // Reports whether a single Android permission is granted.
    private fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}

// Converts an Android location into the map UI state used by the screen.
private fun Location.toUiState(label: String): MapLocationUiState {
    return MapLocationUiState(
        latitude = latitude,
        longitude = longitude,
        label = label
    )
}
