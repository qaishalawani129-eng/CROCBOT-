# Croc Bot (FAST MODE) — Android prototype

This project contains the Android Accessibility/gesture-control layer for the TikTok crocodile emoji game.

## Important
The game is rendered graphically, so AccessibilityService alone cannot see which crocodiles are safe.
This prototype therefore contains the gesture engine and a deliberately simple alternating controller.
It is NOT a finished autonomous vision bot.

## Build
Open this folder in Android Studio, let Gradle sync, then Build > Build APK(s).

## Activate
1. Install the APK.
2. Open Croc Bot.
3. Tap OPEN ACCESSIBILITY SETTINGS.
4. Enable Croc Bot.
5. Open TikTok and launch the crocodile emoji game.
6. Return to Croc Bot and press START FAST MODE.
7. Press STOP to stop automation.

## Next upgrade
For true autonomous play, add a MediaProjection screen-capture pipeline and an image classifier/heuristic that identifies:
- player position
- normal crocodiles
- skeleton crocodiles
- current vertical jump phase

Then replace `direction` in `onAccessibilityEvent()` with the predicted landing direction.
