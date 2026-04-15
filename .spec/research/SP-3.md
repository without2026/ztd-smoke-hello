# SP-3 Research — CI validation (assembleDebug + unit test)

## Goal
CI must prove: (a) the Android module compiles (`./gradlew :app:assembleDebug`), (b) unit tests pass (`./gradlew :app:testDebugUnitTest`). This is the Phase 7 "validate" gate for the smoke.

## Decisions
- **Platform**: GitHub Actions, `ubuntu-latest`. Matches platform repo template `android-build.yml` (already landed in PR 3c-1 Phase B).
- **JDK**: Temurin 17 via `actions/setup-java@v4` — required by AGP 8.5.
- **Gradle caching**: `gradle/actions/setup-gradle@v3` (official) — handles wrapper validation + dependency cache.
- **Steps**:
  1. checkout
  2. setup-java (temurin/17)
  3. setup-gradle
  4. `./gradlew :app:assembleDebug --no-daemon`
  5. `./gradlew :app:testDebugUnitTest --no-daemon`
  6. upload test report on failure
- **No signing, no release, no emulator**: smoke only.

## Routing
Platform repo's `android-build.yml` is the intended reusable workflow. For this sandbox smoke, either call it via `workflow_call` or inline the steps directly (inline is simpler for a one-off smoke; platform-repo routing is validated separately in PR 3c-1).

## Risks
- Wrapper not committed → CI fails. Mitigated by SP-1 decision to include `gradlew`.
- Robolectric first-run downloads Android SDK jars; cache via setup-gradle covers this after first green run.

## Blast radius
Local to `.github/workflows/`. No production surface.
