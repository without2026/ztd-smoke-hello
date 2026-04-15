# SP-1 Research — Android project scaffold (MainActivity + hello_message)

## Goal
Produce a minimal Android app module that satisfies HELLO-001: MainActivity shows a TextView (id=hello_text) bound to string resource hello_message = "Hello".

## Decisions
- **Build system**: Gradle with Kotlin DSL (`build.gradle.kts`, `settings.gradle.kts`). Matches modern AGP defaults; no Groovy legacy.
- **AGP / Kotlin / minSdk**: AGP 8.5+, Kotlin 1.9+, minSdk 26 (per spec precondition), compileSdk/targetSdk 34. Java 17 toolchain.
- **UI**: Plain XML layout + `AppCompatActivity`. Compose is rejected for a smoke — adds dependency surface (compose-bom, runtime, ui, material) with no spec value.
- **Package**: `com.without.hellosmoke` (matches spec reference).
- **String resource**: `app/src/main/res/values/strings.xml` with `<string name="hello_message">Hello</string>`. No translations (enforces HELLO-INV-001).
- **Layout**: `app/src/main/res/layout/activity_main.xml` with single TextView `@+id/hello_text` referencing `@string/hello_message`.
- **Gradle wrapper**: include `gradlew`/`gradlew.bat` so CI has no Gradle version drift.

## Risks
- minSdk 26 vs default 24 — explicit per spec; must be declared in `defaultConfig`.
- Avoid material3/appcompat dependency bloat; use only `androidx.appcompat:appcompat` + `androidx.core:core-ktx`.

## Blast radius
Local to `app/` module. No cross-module or platform impact.
