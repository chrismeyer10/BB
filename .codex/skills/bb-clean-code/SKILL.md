---
name: bb-clean-code
description: Enforce the BahnAndBike project code style. Use when writing or reviewing Kotlin code so classes and functions stay short, responsibilities stay isolated, and every class and function gets a short intent comment above it.
---

# BahnAndBike Clean Code

## Follow These Rules

- Put a short comment above every class.
- Put a short comment above every function.
- Keep comments focused on intent and responsibility.
- Keep classes as small as possible.
- Keep functions as short as possible.
- Split long functions early instead of adding inline complexity.
- Prefer explicit names over shortened names.
- Avoid utility dumping grounds.

## Comment Pattern

Use comments in this style:

```kotlin
// Coordinates journey results for the current search request.
class BuildJourneyUseCase

// Converts API station data into a domain-safe station model.
fun toStation(...)
```

Do not write comments that only restate syntax.

## Function Rules

- One function should do one job.
- Push branching into small private helpers when needed.
- Avoid deep nesting.
- Return early when it improves readability.
- Keep parameter lists short.

## Class Rules

- One class should own one reason to change.
- Move formatting, mapping, validation, and orchestration into separate types when they grow.
- Prefer constructor injection for dependencies.
- Keep state ownership obvious.

## Review Checklist

- Can this file be understood quickly?
- Can any function be split without harming clarity?
- Does each comment explain purpose?
- Is any class doing work that belongs to another layer?
