# Shalatku

A beginner Jetpack Compose app that shows daily prayer times, lets the user track
which prayers are done, and includes a simple tasbih counter. Built for the
Mobile Application Development course (Meeting 9: State Management and User Interaction,
extended in Meeting 12: Android Architecture, MVVM and ViewModel).

## Screenshots

| Home | Tracker | Tasbih |
|---|---|---|
| ![Home screen](screenshots/tampilanAwalAplikasi.png) | ![Tracker screen](screenshots/tampilanPrayerTracker.png) | ![Tasbih screen](screenshots/tampilanTasbih.png) |

### Rotation test (state survives rotation)

| Screen | Portrait | Landscape |
|---|---|---|
| Tracker | ![Tracker portrait](screenshots/Portrait_Tracker.png) | ![Tracker landscape](screenshots/Landscape_Tracker.png) |
| Tasbih | ![Tasbih portrait](screenshots/Portrait_Tasbih.png) | ![Tasbih landscape](screenshots/Landscape_Tasbih.png) |

## Features

- **Home:** shows today's five prayer times (Subuh, Dzuhur, Ashar, Maghrib, Isya) as cards.
- **Tracker:** a checklist where the user ticks each prayer, with a progress text such as
  "2 of 5 prayers done today", and a **Clear all** button.
- **Tasbih:** a counter with a +1 button and a Reset button. The user can choose a round
  size of 33, 99 or 100. Reaching the round size starts a new round and adds one to
  "Rounds completed".
- **Bottom navigation bar** to switch between the three screens.
- Screens can be scrolled, so every button stays reachable in landscape mode.

The prayer times are fixed sample data (`samplePrayers` in `Prayer.kt`), not real-time data.

## Architecture (MVVM, simplified)

| Layer | Job | In Shalatku |
|---|---|---|
| Model | what the data is | `Prayer`, `samplePrayers` |
| ViewModel | holds UI state and applies the app rules | `SalatKuViewModel` |
| View | draws state and reports taps | `HomeScreen`, `TrackerScreen`, `TasbihScreen`, `ShalatkuApp` |

`SalatKuViewModel` owns the tasbih count, the completed rounds, the round size and the
ticked prayers. The rules live there: a round ends when the count reaches the round size,
changing the round size resets the current count but keeps the completed rounds, and
**Clear all** removes every ticked prayer. The screens only receive values and lambdas,
never the ViewModel.

## State handling

- The ViewModel is obtained with `viewModel()` and is never created with a constructor.
  Because of that, its state survives screen rotation.
- The selected bottom-bar tab is UI-only state and is kept in `ShalatkuApp` with
  `rememberSaveable`.
- Since the state lives in the ViewModel, the tasbih count and the ticked prayers also stay
  when the user switches tabs.
- A ViewModel does not survive process death, so everything resets if Android kills the app.

## Project structure

```
app/src/main/java/com/example/shalatku/
├── MainActivity.kt        # entry point, bottom bar, and screen switching (ShalatkuApp)
├── SalatKuViewModel.kt    # UI state and app rules
├── Prayer.kt              # Prayer data class and sample prayer times
├── HomeScreen.kt          # Screen 1: prayer time cards
├── TrackerScreen.kt       # Screen 2: prayer checklist, progress text and Clear all
├── TasbihScreen.kt        # Screen 3: counter, round size chips, +1 and Reset
└── ui/theme/              # generated Material 3 theme
```

## Tech stack

- Kotlin
- Jetpack Compose with Material 3
- Lifecycle ViewModel for Compose
- Android Studio (Minimum SDK: API 24)

## How to run

1. Clone this repository and open it in Android Studio.
2. Wait for the Gradle sync to finish.
3. Select an emulator or a connected Android device.
4. Press **Run ▶**.

## Testing

The app was tested manually on the Android Emulator (Medium Phone API 37.0): all three
screens display correctly, navigation works, ticking prayers updates the progress text,
Clear all removes every tick, the tasbih counter increases and resets, and changing the
round size resets the count but keeps the completed rounds. After rotating the emulator,
the tasbih count and the ticked prayers stay the same.

## Author

Farrel Faza