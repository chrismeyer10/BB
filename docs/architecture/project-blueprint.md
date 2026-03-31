# BahnAndBike Project Blueprint

## Goal

Build a large Android app that combines bike routing and rail routing into one understandable journey.

The user should be able to:
- choose a start location
- choose a destination
- define where the bike leg should start and end
- let the system calculate the rail leg between the chosen stations
- receive one composed journey made of multiple segments

## Core Product Model

The central domain object should be a `Journey`.

A `Journey` is built from small `JourneySegment` units:
- `BikeSegment`
- `RailSegment`
- later: `WalkSegment`
- later: `TransferSegment`

This keeps the app extensible because new segment types can be added without rewriting the whole routing flow.

## Architecture Principles

- Keep every module focused on one responsibility.
- Keep every class small and easy to scan.
- Keep every function short and purpose-specific.
- Put a short comment above every class and every function.
- Prefer composition over large utility classes.
- Move business rules into the domain layer.
- Keep UI free from routing logic.
- Keep API DTOs out of the UI and domain layers.
- Design for replacement of data sources.

## Initial Module Structure

Start with this structure:

- `app`
  Android entry point, navigation host, app wiring
- `core:common`
  shared constants, result wrappers, dispatchers, base contracts
- `core:model`
  shared domain-safe models used across modules
- `core:ui`
  reusable UI primitives, screen containers, spacing, state renderers
- `core:designsystem`
  theme, colors, typography, cards, buttons, navigation elements
- `core:testing`
  fake data builders, test helpers, test dispatchers
- `domain:routing`
  use cases and routing rules
- `data:rail`
  rail APIs, DTOs, mappers, repositories
- `data:bike`
  bike routing APIs, DTOs, mappers, repositories
- `data:places`
  geocoding, station lookup, autocomplete
- `data:user`
  preferences, favorites, recent searches
- `feature:search`
  start, destination, station and bike-leg input flow
- `feature:journey`
  composed route results and segment details
- `feature:favorites`
  saved searches and favorite journeys
- `feature:settings`
  user options and defaults

## Package Rules

Inside each feature or data module, use the same internal structure:

- `api`
- `mapper`
- `model`
- `repository`
- `usecase`
- `ui`
- `state`

Do not mix these responsibilities in one package.

## Development Style Rules

- One file should usually contain one main class, interface, or sealed type.
- A class should ideally fit on one screen without scrolling too much.
- A function should do one thing and read top-to-bottom.
- If a function needs a long explanation, split it.
- Comments must explain intent, not obvious syntax.
- Public models must use explicit names instead of abbreviations.

## First Domain Slice

Start with a manual journey builder before smart automation.

Phase 1 journey input:
- user enters start location
- user enters destination
- user selects bike start point
- user selects bike end point
- app selects start station
- app selects end station
- app calculates bike + rail + bike

This allows the routing model to be validated before advanced auto-selection logic is added.

## Core Domain Contracts

Create these contracts early:

- `PlaceRepository`
- `BikeRouteRepository`
- `RailRouteRepository`
- `JourneyComposer`
- `JourneyScoringService`

Keep them in domain-facing shapes so providers can change later.

## Feature Delivery Order

1. Foundation
   Create modules, navigation shell, design system, shared models, result handling.
2. Search Flow
   Add place search, station search, and input validation.
3. Routing MVP
   Add manual bike and rail composition.
4. Journey Results
   Add result screen with segment breakdown.
5. User Persistence
   Add favorites, recents, and preferences.
6. Smart Suggestions
   Add recommended stations and better route ranking.
7. Realtime
   Add delays, changes, outage handling, re-routing.

## Agent Working Model

Use one master agent and multiple specialist agents.

- Master agent
  reads all project skills first and defines the active constraints
- Architecture agent
  guards module boundaries and naming
- Feature agent
  implements one feature at a time without crossing layers
- Domain agent
  owns routing rules and journey composition
- Data agent
  owns API integration and repository wiring
- UI agent
  owns screens, components, and app navigation
- Quality agent
  owns tests, lint, and regression checks

## Definition of Done

Work is only complete when:
- module boundaries are respected
- comments exist above each class and function
- classes and functions stay compact
- naming is clear
- feature logic is testable
- build passes
- the change does not make later extension harder
