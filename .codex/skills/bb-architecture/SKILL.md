---
name: bb-architecture
description: Define and protect the architecture for the BahnAndBike app. Use when creating modules, packages, interfaces, domain models, repository contracts, navigation boundaries, or any structural change that affects extensibility, maintainability, or separation of concerns.
---

# BahnAndBike Architecture

Read `docs/architecture/project-blueprint.md` before making structural decisions.

## Follow These Rules

- Keep modules narrow and responsibility-driven.
- Keep Android framework code out of domain modules.
- Keep DTOs in data modules only.
- Keep repositories behind interfaces when business logic depends on them.
- Create new modules only when they reduce coupling.
- Prefer stable contracts over direct feature-to-feature calls.

## Apply This Module Order

Build dependencies inward:

- `app` depends on feature and core modules
- feature modules depend on domain and core modules
- data modules depend on core modules and expose implementations
- domain modules depend only on core modules
- core modules depend on nothing app-specific

## Apply These File Rules

- Create one main type per file.
- Avoid god files and mixed responsibilities.
- Use package names that mirror responsibilities.
- Keep helper code close to the feature that owns it.

## Apply These Design Rules

- Build journeys from segment types instead of one large route object.
- Create small use cases with one clear outcome.
- Add mapper classes when crossing layer boundaries.
- Prefer immutable UI state.
- Make replacement of APIs possible without rewriting feature logic.

## Before Finishing

- Check whether the change made any module too broad.
- Check whether any class now holds unrelated responsibilities.
- Check whether a smaller contract would express the dependency better.
