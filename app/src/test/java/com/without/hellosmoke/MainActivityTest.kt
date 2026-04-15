package com.without.hellosmoke

import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Unit tests for [MainActivity].
 *
 * Spec coverage:
 *  - HELLO-001 (ui_interaction): hello_text TextView shows "Hello"
 *  - HELLO-002 (lifecycle): onCreate completes; hello_text is non-null
 *  - HELLO-INV-001 (invariant): string resource hello_message == "Hello" exactly
 */
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    // spec: HELLO-002
    @Test
    fun onCreate_inflatesLayoutAndFindsHelloTextView() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        val textView = activity.findViewById<TextView>(R.id.hello_text)
        assertNotNull("hello_text must be present after onCreate", textView)
    }

    // spec: HELLO-001
    @Test
    fun helloText_displaysHelloString() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        val textView = activity.findViewById<TextView>(R.id.hello_text)
        assertEquals("Hello", textView.text.toString())
    }

    // spec: HELLO-INV-001
    @Test
    fun helloMessageStringResource_isExactlyHello() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        val value = activity.getString(R.string.hello_message)
        assertEquals("Hello", value)
    }
}
