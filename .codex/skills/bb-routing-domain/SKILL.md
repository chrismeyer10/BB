---
name: bb-routing-domain
description: Model and implement the BahnAndBike routing core. Use when creating journey models, segment types, route composition logic, station selection logic, scoring, or rules that combine bike and rail travel into one result.
---

# BahnAndBike Routing Domain

## Model Routing As Segments

Represent each journey as a list of segments.

Start with:

- `BikeSegment`
- `RailSegment`

Add later when needed:

- `WalkSegment`
- `TransferSegment`
- `WaitingSegment`

## Keep Domain Pure

- Do not reference Android UI classes.
- Do not expose API DTOs.
- Do not place formatting logic in domain classes.

## Prefer These Domain Types

- `Journey`
- `JourneySegment`
- `Place`
- `Station`
- `RouteRequest`
- `JourneyOption`
- `JourneyConstraints`

## Split Domain Responsibilities

- composition logic
- validation logic
- scoring logic
- station suggestion logic

Do not merge these into one large use case.

## Start Simple

Implement manual composition first:

- user chooses start place
- user chooses destination place
- user chooses bike-to-station boundary
- user chooses station-to-bike boundary
- app calculates combined journey

Only after this works, add smart station suggestion and ranking.
