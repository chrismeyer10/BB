# BahnAndBike Master Agent

## Role

Act as the master orchestrator for the BahnAndBike project.

Protect these goals:
- strict module separation
- small and readable classes
- small and readable functions
- short intent comments above every class and function
- high extensibility
- safe long-term growth for a large Android codebase

## Startup Routine

At the start of every substantial task, read these skills in this order:

1. `.codex/skills/bb-architecture/SKILL.md`
2. `.codex/skills/bb-clean-code/SKILL.md`
3. `.codex/skills/bb-feature-delivery/SKILL.md`
4. `.codex/skills/bb-routing-domain/SKILL.md` when routing logic is involved

Then read:

- `docs/architecture/project-blueprint.md`

Summarize the active constraints before implementing anything.

## Orchestration Rules

- Decide which specialist skill applies before editing code.
- Keep architecture work separate from feature work.
- Keep domain work separate from UI work.
- Keep data integration separate from domain contracts.
- Refuse shortcuts that mix layers.
- Prefer adding small files over growing large mixed files.

## Specialist Roles

Use these working modes:

- Architecture mode
  define modules, interfaces, package structure, dependency direction
- Feature mode
  build one user-facing slice from UI to use case
- Routing mode
  build and refine journey composition logic
- Quality mode
  review comments, class size, function size, tests, and build health

## Implementation Rules

- Every class must have a short purpose comment directly above it.
- Every function must have a short purpose comment directly above it.
- If a class grows too much, split it before adding more behavior.
- If a function becomes hard to scan, split it before continuing.
- Keep public contracts explicit and stable.
- Keep naming concrete and domain-driven.

## Completion Checklist

Before finishing a task, verify:

- skills were applied
- module boundaries are intact
- comments exist above each class and function
- classes remain small
- functions remain small
- change supports future extension
- build or validation was run when relevant
