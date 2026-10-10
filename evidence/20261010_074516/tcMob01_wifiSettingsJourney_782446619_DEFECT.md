# Defect Draft: tcMob01_wifiSettingsJourney_782446619

| Field | Value |
|---|---|
| Title | tcMob01_wifiSettingsJourney failed |
| Severity | Medium |
| Environment | Android emulator Pixel_6 (API 34), Appium 3, UiAutomator2 |
| Run ID | 20261010_074516 |
| Test data | [QA_WiFi_01] |
| Evidence | tcMob01_wifiSettingsJourney_782446619_FAIL.png, tcMob01_wifiSettingsJourney_782446619_logcat.txt |

## Actual result
java.lang.AssertionError: expected [WiFi setting] but found [WiFi settings]

## Expected result
The preference dialog title reads "WiFi settings".

## Steps to reproduce
1. Launch ApiDemos on the Pixel_6 emulator (API 34).
2. Open Preference > 3. Preference dependencies.
3. Tick the checkbox, then tap "WiFi settings".
4. Compare the dialog title with the expected text.
