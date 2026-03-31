# BahnAndBike

BahnAndBike is an Android application for planning combined bicycle and rail journeys in one seamless flow.

The goal of the app is to help users define a start location, a destination, and the bike portions of a trip, while the train connection between suitable stations is calculated as part of the overall route. Instead of treating cycling and rail travel as separate tasks, the app is designed to present them as one connected journey.

## Purpose

Many travel apps are optimized either for public transport or for cycling. BahnAndBike is being built to bridge that gap.

The product vision is to support users who:
- want to ride by bike to a station
- continue the trip by train
- finish the final part of the journey by bike again
- view the whole route in a clear and practical way

The long-term focus is on delivering an understandable multimodal routing experience with room for later expansion into smarter station suggestions, preferences, realtime rail information, and personalized route handling.

## Current Status

This project is actively under development.

At the current stage, the app already includes:
- a modular Android project architecture prepared for long-term growth
- a dark mode focused design direction
- a map-first planning screen concept
- a sliding route panel for route setup
- an initial domain model for combined bike and rail journey segments

The application is not yet feature-complete. Core planning flows, live data integration, and full routing intelligence are still in progress.

## Planned Capabilities

The product is being built toward these capabilities:
- choose a start location and destination
- define the bike section before boarding the train
- define the bike section after leaving the train
- calculate the rail segment between the selected stations
- combine all legs into one route result
- show the trip on a map with clear route context
- support future extensions such as favorites, preferences, and realtime rail updates

## Product Direction

The app is intended to evolve into a large, maintainable Android codebase with clearly separated responsibilities.

Important architectural goals include:
- strict separation between app, feature, domain, data, and shared core modules
- small and readable classes and functions
- extensibility for future route logic and provider integrations
- a structure that supports ongoing iteration without requiring broad rewrites

## Technology

The current project is based on:
- Kotlin
- Android Studio / Gradle
- Jetpack Compose
- a modular multi-module project structure

## Development Note

Because the project is still in development, parts of the user experience, routing logic, and technical integration are intentionally in a staged rollout state. The repository reflects both the current implementation and the architectural groundwork for the larger product vision.

## Requirements

- Android Studio
- Android SDK
- JDK 17

## Local Setup

1. Open the project in Android Studio.
2. Let Gradle sync the project dependencies.
3. Add your local configuration where required, including the Google Maps API key in `local.properties`:

```properties
MAPS_API_KEY=your_google_maps_api_key
```

4. Run the app on an emulator or physical Android device.

## Build

Use the Gradle wrapper to build the project:

```powershell
.\gradlew.bat assembleDebug
```
