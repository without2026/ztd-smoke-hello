package com.without.hellosmoke

import android.app.Activity
import android.os.Bundle

/**
 * Single-screen Hello activity.
 *
 * // spec: HELLO-001 — displays TextView with text "Hello" on first launch
 * // spec: HELLO-002 — onCreate inflates content view without throwing
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
