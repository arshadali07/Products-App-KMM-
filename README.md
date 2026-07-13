# ExploreProducts

A Kotlin Multiplatform (KMP) application that lets users browse, search, and filter products from the [DummyJSON](https://dummyjson.com) public API. The shared UI is built entirely with Compose Multiplatform and runs on both Android and iOS from a single codebase.

---

## Business Requirements

| # | Requirement |
|---|-------------|
| 1 | Display a paginated list of products on the home screen |
| 2 | Allow users to tap a product to view its full details |
| 3 | Provide a search screen to find products by keyword |
| 4 | Allow users to filter products by category |
| 5 | Load and display product images efficiently |
| 6 | Support both Android and iOS from a shared codebase |

---

## Project Architecture

The project follows a clean **MVVM + Repository** pattern, layered across three distinct concerns:

```
composeApp/src/commonMain
├── data/
│   ├── model/          # DTOs (raw API response models)
│   ├── mapper/         # DTO → Domain mappers
│   ├── networking/     # Ktor HTTP client setup, safe-call wrappers
│   ├── KtorProductsService.kt      # Concrete API implementation
│   └── RemoteProductsRepository.kt # Repository implementation
├── domain/
│   ├── model/          # Domain models (Product, ProductsMain)
│   ├── ProductsService.kt          # API service interface
│   └── ProductsRepository.kt       # Repository interface
├── presentation/
│   ├── home/           # Product list screen (ViewModel, UiState, Events, Actions)
│   ├── details/        # Product detail screen
│   ├── search/         # Search screen
│   ├── filter/         # Category filter screen
│   └── theme/          # Custom Material3 theme, colors, shapes
├── NavigationRoute.kt  # Type-safe Navigation3 routes (sealed interface)
└── NavigationRoot.kt   # NavBackStack host / route graph
```

### Key Libraries

| Library | Version | Purpose |
|---------|---------|---------|
| Kotlin Multiplatform | 2.3.0 | Shared Kotlin code for Android + iOS |
| Compose Multiplatform | 1.10.0 | Shared UI across platforms |
| Ktor Client | 3.3.3 | Networking (OkHttp on Android, Darwin on iOS) |
| Navigation3 | 1.0.0-alpha06 | Type-safe multiplatform navigation |
| Coil 3 | 3.3.0 | Async image loading via Ktor |
| AndroidX Paging | 3.4.0-beta01 | Paginated product list |
| Kotlinx Serialization | 1.9.0 | JSON deserialization |
| AndroidX Lifecycle / ViewModel | 2.9.6 | State management |

### Data Flow

```
UI (Composable)
   ↕ Actions / Events
ViewModel  ←→  Repository  ←→  Service (Ktor)  ←→  DummyJSON API
              (interface)       (interface)
```

- **UiState** — immutable snapshot of what the screen should render.
- **Actions** — one-shot user intents sent from the UI to the ViewModel.
- **Events** — one-time side effects emitted from the ViewModel to the UI (e.g. navigate, show snackbar).

---

## Build & Run

### Prerequisites

| Tool | Version |
|------|---------|
| JDK | 11 or higher |
| Android Studio | Meerkat (2024.1+) or later |
| Kotlin Multiplatform plugin | Installed in Android Studio |
| Xcode | 15+ (for iOS) |
| CocoaPods | Not required (framework is static) |
| Gradle | 8.x (wrapper included) |

> The project targets **Android API 24–36** and **iOS arm64 / iOS Simulator arm64**.

---

### Android

#### Option 1 — Android Studio
1. Open the project root in Android Studio.
2. Wait for Gradle sync to finish.
3. Select the `composeApp` run configuration and a connected device / emulator.
4. Click **Run**.

#### Option 2 — Terminal

```bash
# Debug APK
./gradlew :composeApp:assembleDebug

# Install directly on a connected device
./gradlew :composeApp:installDebug
```

The APK will be generated at:
```
composeApp/build/outputs/apk/debug/composeApp-debug.apk
```

---

### iOS

#### Option 1 — Android Studio (KMP plugin)
1. Open the project in Android Studio.
2. Select the iOS run configuration from the toolbar.
3. Choose a simulator or physical device and click **Run**.

#### Option 2 — Xcode
1. Build the shared framework first:
   ```bash
   ./gradlew :composeApp:assembleXCFramework
   ```
2. Open `iosApp/iosApp.xcodeproj` in Xcode.
3. Select a simulator or device and press **⌘ + R**.

> **Note:** The iOS framework is compiled as a **static** framework (`isStatic = true`). No CocoaPods integration is needed.

---

## Trade-offs & Assumptions

### Trade-offs

| Area | Decision | Reason |
|------|----------|--------|
| **Paging** | `androidx.paging` used in shared `commonMain` | Library has KMP-compatible multiplatform artifact; avoids duplicating pagination logic per platform |
| **Navigation** | Navigation3 (alpha) instead of stable Navigation 2 | Navigation3 has first-class KMP + Compose Multiplatform support; Navigation 2 targets Android only |
| **iOS entry point** | Thin SwiftUI wrapper (`ContentView.swift`) delegates entirely to `ComposeUIViewController` | Keeps UI logic 100% in shared Kotlin; SwiftUI-specific features not required for this scope |
| **No local cache / offline support** | All data is fetched directly from the remote API | Reduces complexity; the DummyJSON API is fast and does not require auth |
| **No DI framework** | Dependencies are wired manually in ViewModel constructors | Scope is small enough that a DI container (Koin/Hilt) would add boilerplate without benefit |

### Assumptions

- Internet connectivity is available on the device; no offline/error-retry UI beyond basic error state handling.
- The [DummyJSON Products API](https://dummyjson.com/docs/products) remains publicly accessible without authentication.
- Pagination uses DummyJSON's `limit` / `skip` query parameters (30 items per page).
- Category filtering and search are mutually exclusive flows — search does not apply category filters simultaneously.
- Unit tests are planned but not yet implemented (tracked for Q2 2026).


### Android Screenshots
<img src="https://github.com/user-attachments/assets/4c14f4d0-77f4-4788-afd4-fbb73867fb51" width="300"/>
<img src="https://github.com/user-attachments/assets/9ab05ee2-8854-41af-9655-7426ca48ab65" width="300"/>
<img src="https://github.com/user-attachments/assets/5d268ffc-b142-4a36-9d34-6e7e6b44b8be" width="300"/>
<img src="https://github.com/user-attachments/assets/274ce6ed-9d4a-49ec-bbee-539b848b4b33" width="300"/>
<img src="https://github.com/user-attachments/assets/4b140b89-bb08-4266-b3b4-6dc05f7b6775" width="300"/>
<img src="https://github.com/user-attachments/assets/a028a755-53e9-4aa1-9261-fac43b9c1391" width="300"/>

### iOS Screenshots
<img src="https://github.com/user-attachments/assets/4cca6c79-5712-44f2-991b-94d8f7ae7a03" width="300"/>
<img src="https://github.com/user-attachments/assets/ed39307c-bf0e-4fca-9256-1bdf9f32d889" width="300"/>
<img src="https://github.com/user-attachments/assets/e8e3e0df-c3cf-410f-b058-3ca4058431fd" width="300"/>
<img src="https://github.com/user-attachments/assets/d0f894bc-8fbb-4d02-b37a-379b47d89b94" width="300"/>
<img src="https://github.com/user-attachments/assets/6fb59136-e86f-44be-b692-8b4b06565d86" width="300"/>
<img src="https://github.com/user-attachments/assets/0a77eba8-327b-4d0a-a960-cf55fcb1c6e1" width="300"/>
