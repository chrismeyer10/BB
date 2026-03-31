package com.example.bahnandbike.feature.search.ui

import android.Manifest
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bahnandbike.feature.search.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Renders the map-first route planning screen with a sliding route panel.
@Composable
fun SearchScreen(model: SearchUiModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val locationProvider = remember(context) { DeviceLocationProvider(context = context) }
    val coroutineScope = rememberCoroutineScope()
    var mapLocation by remember {
        mutableStateOf(
            MapLocationUiState(
                latitude = model.fallbackLatitude,
                longitude = model.fallbackLongitude,
                label = context.getString(R.string.map_preview_location)
            )
        )
    }
    var isPanelExpanded by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLocationPermissionLauncher(
        context = context,
        model = model,
        locationProvider = locationProvider,
        coroutineScope = coroutineScope,
        onLocationReady = { location -> mapLocation = location }
    )

    LaunchedEffect(locationProvider) {
        if (locationProvider.hasLocationPermission()) {
            mapLocation = locationProvider.loadMapLocation(model = model)
        } else {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        SearchMapBackground(
            mapLocation = mapLocation,
            modifier = Modifier.fillMaxSize()
        )
        SearchMapHeader(
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        )
        if (!model.hasMapsApiKey) {
            MissingMapsKeyBanner(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .statusBarsPadding()
                    .padding(top = 116.dp, start = 20.dp, end = 20.dp)
            )
        }
        SearchRoutePanel(
            model = model,
            mapLocation = mapLocation,
            isExpanded = isPanelExpanded,
            onToggle = { isPanelExpanded = !isPanelExpanded },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

// Renders a clear warning when the Google Maps API key is not configured locally.
@Composable
fun MissingMapsKeyBanner(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.92f)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(R.string.map_missing_key_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                text = stringResource(R.string.map_missing_key_body),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}

// Requests location permissions and loads map state when the user responds.
@Composable
fun rememberLocationPermissionLauncher(
    context: Context,
    model: SearchUiModel,
    locationProvider: DeviceLocationProvider,
    coroutineScope: CoroutineScope,
    onLocationReady: (MapLocationUiState) -> Unit
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestMultiplePermissions()
) {
    val hasPermission = it.values.any { granted -> granted }

    if (!hasPermission) {
        onLocationReady(
            MapLocationUiState(
                latitude = model.fallbackLatitude,
                longitude = model.fallbackLongitude,
                label = context.getString(R.string.map_permission_location)
            )
        )
        return@rememberLauncherForActivityResult
    }

    coroutineScope.launch {
        onLocationReady(locationProvider.loadMapLocation(model = model))
    }
}

// Renders the dark map background and centers it on the active position.
@Composable
fun SearchMapBackground(mapLocation: MapLocationUiState, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(mapLocation.latitude, mapLocation.longitude),
            13f
        )
    }
    val mapProperties = remember {
        MapProperties(
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                context,
                R.raw.map_style_dark
            )
        )
    }

    LaunchedEffect(mapLocation) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
            LatLng(mapLocation.latitude, mapLocation.longitude),
            13f
        )
    }

    GoogleMap(
        modifier = modifier,
        properties = mapProperties,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(
                position = LatLng(mapLocation.latitude, mapLocation.longitude)
            ),
            title = mapLocation.label
        )
    }

    Box(
        modifier = modifier
            .background(Color(0x33000000))
    )
}

// Renders the text block that floats above the map.
@Composable
fun SearchMapHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = stringResource(R.string.search_map_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = stringResource(R.string.search_map_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// Renders the sliding bottom route setup card.
@Composable
fun SearchRoutePanel(
    model: SearchUiModel,
    mapLocation: MapLocationUiState,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val panelHeight by animateDpAsState(
        targetValue = if (isExpanded) 420.dp else 220.dp,
        label = "route-panel-height"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(panelHeight),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f),
        tonalElevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            SearchRoutePanelHandle(isExpanded = isExpanded, onToggle = onToggle)
            SearchRoutePanelHeader()
            SearchRouteInfoCard(
                label = stringResource(R.string.route_panel_location_label),
                value = mapLocation.label
            )
            SearchRouteInfoCard(
                label = stringResource(R.string.route_panel_start_label),
                value = model.startName
            )
            SearchRouteInfoCard(
                label = stringResource(R.string.route_panel_destination_label),
                value = model.destinationName
            )
            SearchRouteInfoCard(
                label = stringResource(R.string.route_panel_bike_out_label),
                value = model.startStationName
            )
            SearchRouteInfoCard(
                label = stringResource(R.string.route_panel_bike_in_label),
                value = model.destinationStationName
            )
            Button(
                onClick = onToggle,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.route_panel_action))
            }
        }
    }
}

// Renders the compact handle that toggles the bottom panel state.
@Composable
fun SearchRoutePanelHandle(isExpanded: Boolean, onToggle: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .height(5.dp)
                .fillMaxWidth(0.18f)
                .background(
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(100.dp)
                )
        )
        Button(onClick = onToggle) {
            Text(
                text = stringResource(
                    if (isExpanded) {
                        R.string.route_panel_collapse
                    } else {
                        R.string.route_panel_expand
                    }
                )
            )
        }
    }
}

// Renders the route panel title and usage hint.
@Composable
fun SearchRoutePanelHeader() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = stringResource(R.string.route_panel_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = stringResource(R.string.route_panel_hint),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// Renders one route summary item inside the sliding card.
@Composable
fun SearchRouteInfoCard(label: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
