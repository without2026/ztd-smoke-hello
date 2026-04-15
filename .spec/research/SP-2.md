# SP-2 Research — Unit test for MainActivity (HELLO-002, HELLO-INV-001)

## Goal
`MainActivityTest.kt` under `app/src/test/java/com/without/hellosmoke/` that:
1. Creates MainActivity with null savedInstanceState, asserts onCreate does not throw and `findViewById(R.id.hello_text)` is non-null (HELLO-002).
2. Asserts the TextView's text equals "Hello" (HELLO-001 behavior + HELLO-INV-001 invariant).

## Decisions
- **Framework**: Robolectric 4.12 on JVM (`testImplementation`). Faster than instrumentation, runs in GitHub Actions without an emulator — critical for smoke CI time budget.
- Alternative considered: `androidx.test.core:core-ktx` + `ActivityScenario` with Robolectric runner. Works but `Robolectric.buildActivity(MainActivity::class.java).setup()` is the lowest-ceremony option for a single Activity test.
- **Assertions**: JUnit 4 (Robolectric's supported runner). `org.junit.Assert` — no AssertJ/Truth to keep deps minimal.
- **testOptions**: `unitTests.isIncludeAndroidResources = true` so string resources resolve in Robolectric.

## Test shape
```kotlin
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
  @Test fun onCreate_showsHello() {
    val activity = Robolectric.buildActivity(MainActivity::class.java).setup().get()
    val tv = activity.findViewById<TextView>(R.id.hello_text)
    assertNotNull(tv)
    assertEquals("Hello", tv.text.toString())
  }
}
```

## Risks
- Robolectric SDK level: pin via `@Config(sdk = [33])` or rely on `robolectric.properties` to avoid "SDK 34 not supported" drift.

## Blast radius
Local to `app/src/test/`. No production code impact.
