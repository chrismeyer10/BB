---
name: bb-feature-delivery
description: Implement BahnAndBike features in a repeatable way. Use when adding a new screen, feature flow, UI state, ViewModel, repository call, or test so work is delivered with consistent layering and predictable file structure.
---

# BahnAndBike Feature Delivery

Read `docs/architecture/project-blueprint.md` before starting a new feature.

## Build Features In This Order

1. Define the user action and expected result.
2. Define the domain model involved.
3. Define or reuse the use case.
4. Define repository contracts if data is needed.
5. Define UI state.
6. Build ViewModel logic.
7. Build screen components.
8. Add tests.

## Standard Feature Shape

For each feature, prefer this file set:

- `...Screen.kt`
- `...ViewModel.kt`
- `...UiState.kt`
- `...Action.kt`
- `...UseCase.kt`
- `...Repository.kt`
- `...Mapper.kt` when crossing layers

Only create files that are actually needed.

## UI Rules

- Keep screens focused on rendering and event forwarding.
- Keep ViewModels responsible for state transitions.
- Keep business rules in use cases.
- Keep design primitives in core modules, not duplicated per feature.

## Delivery Rules

- Make the smallest working slice first.
- Validate build health before moving on.
- Avoid speculative abstractions.
- Create extension points only where growth is already expected.

## For Large Features

Split work into:

- input flow
- domain logic
- data integration
- results rendering
- persistence
- tests
