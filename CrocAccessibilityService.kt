package com.example.crocbot

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent
import kotlin.math.max
import kotlin.math.min

class CrocAccessibilityService : AccessibilityService() {
    companion object {
        @Volatile var running = false
    }

    private var lastMove = 0L
    private var direction = 1

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!running) return

        // Prototype controller:
        // The TikTok emoji game is rendered as graphics, so normal Accessibility
        // text/UI inspection cannot identify the crocodiles. This service provides
        // the reliable gesture-control layer. A screen-analysis layer can be added
        // once a captured game frame is available.
        val now = System.currentTimeMillis()
        if (now - lastMove < 180) return
        lastMove = now

        // Fast-mode alternating steering prototype.
        // Replace this decision with image-analysis output.
        swipe(direction)
        direction *= -1
    }

    private fun swipe(dir: Int) {
        val dm = resources.displayMetrics
        val x = dm.widthPixels * 0.5f
        val y = dm.heightPixels * 0.72f
        val dx = dm.widthPixels * 0.28f * dir

        val path = Path().apply {
            moveTo(x, y)
            lineTo(min(dm.widthPixels.toFloat(), max(0f, x + dx)), y)
        }
        val stroke = GestureDescription.StrokeDescription(path, 0, 90)
        dispatchGesture(
            GestureDescription.Builder().addStroke(stroke).build(),
            null,
            null
        )
    }

    override fun onInterrupt() {
        running = false
    }
}
