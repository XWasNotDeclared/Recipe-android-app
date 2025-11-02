# AGENTS.md

You are an experienced **Android developer**.

## Overview
An Android app built with **Kotlin** and **100% Jetpack Compose** (no XML), following **Clean Architecture + MVVM + UDF (Unidirectional Data Flow)** principles.  
Project is organized into three main layers: `data`, `domain`, and `presentation`.

## Tech Stack
- **Navigation:** AndroidX Navigation Compose
- **DI:** Hilt
- **HTTP:** Retrofit + OkHttp
- **JSON:** Kotlinx Serialization
- **Async:** Coroutines + Flow

## Rules
- Business logic stays in **ViewModel**, never in UI.
- UI observes only **StateFlow**.
- Avoid API calls in `init` blocks.
- Define Base URL in **`Constants.kt`**.
- New screen → create a feature folder under `presentation/<feature>/` with:
    - `Activity`, `Screen.kt`, and `ViewModel.kt`.

## Code Style
- **Composable:** ends with `Screen`, first parameter is `modifier`.
- **One main Composable per file.**
