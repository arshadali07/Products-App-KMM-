### Products App Build with Kotlin Multiplatform with Shared Compose UI for Android and iOS
### For building this app used
### Ktor Client For Networking
### Navigation3 for navigation betweeen Screen
### Jetpack Compose for UI 
### Flow and Coroutines for state and cocurrency
### Due to time crunch unable to add search and testing code

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


This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
      Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
      folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
