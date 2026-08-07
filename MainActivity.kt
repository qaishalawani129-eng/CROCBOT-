package com.example.crocbot

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : android.app.Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = TextView(this).apply {
            text = "🐊 Croc Bot — FAST MODE"
            textSize = 24f
            setPadding(24, 40, 24, 24)
        }
        val info = TextView(this).apply {
            text = "1. Enable the Accessibility Service below.\n2. Open TikTok and start the emoji game.\n3. Return here and press START.\n\nEmergency stop: press STOP."
            textSize = 16f
            setPadding(24, 10, 24, 30)
        }
        val settings = Button(this).apply {
            text = "OPEN ACCESSIBILITY SETTINGS"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }
        val start = Button(this).apply {
            text = "START FAST MODE"
            setOnClickListener { CrocAccessibilityService.running = true }
        }
        val stop = Button(this).apply {
            text = "STOP"
            setOnClickListener { CrocAccessibilityService.running = false }
        }

        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(title)
            addView(info)
            addView(settings)
            addView(start)
            addView(stop)
        }
        setContentView(box)
    }
}
