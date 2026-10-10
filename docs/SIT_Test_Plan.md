# SIT Test Plan: Mobile and API Framework

## 1. Scope
End-to-end validation of:
- An Android app journey (ApiDemos: Preference > Preference dependencies > WiFi settings dialog) driven by database-seeded test data
- Navigation to the Views screen
- API user records (JSONPlaceholder `/users/{id}`) compared against expected records in the SQL database

Out of scope: performance, security and accessibility testing.

## 2. Test approach
- **Progression testing:** new journeys and data checks (TC-MOB-01, TC-MOB-02, TC-API-01)
- **Regression testing:** the full suite is rerun on every build with `mvn clean test`
- **Data-driven:** expected data comes from `testdata/seed.sql` (3 user records), so there is no manual data setup
- **Evidence-based:** every run produces its own evidence folder

## 3. Test environment
| Item | Value |
|---|---|
| Device | Android emulator Pixel_6, API 34 |
| Automation | Appium 3.8 with UiAutomator2 |
| Language and runner | Java, TestNG, Maven |
| API testing | RestAssured |
| Test data | SQLite database seeded from `testdata/seed.sql` |
| App under test | ApiDemos-debug.apk |

## 4. Test cases
| ID | Description | Data sets | Type |
|---|---|---|---|
| TC-API-01 | API user name and email match the expected database record | 3 (users 1, 2, 3) | API |
| TC-MOB-01 | Preference dependency journey with a database-seeded SSID | 2 (QA_WiFi_01, QA_WiFi_02) | Mobile |
| TC-MOB-02 | Views screen is reachable and the Buttons item is displayed | 1 | Mobile |

Total: 6 executions per run.

## 5. Entry criteria
- Emulator running and detected by `adb devices`
- Appium server running on port 4723
- `apps/ApiDemos-debug.apk` present
- Internet access for the API test

## 6. Exit criteria
- All test cases executed
- Evidence captured for each execution
- Every failure has a defect draft

## 7. Evidence and defect process
Each run creates a folder under `evidence/` named by date and time, containing:
- `execution_summary.csv` with test name, status, duration and time
- A screenshot and device log for each mobile execution
- A `_DEFECT.md` draft for each failure, with environment, test data, evidence files and actual result

A tester then completes the severity, expected result and steps to reproduce during triage.

## 8. Sample runs
- A clean run: 6 of 6 passed
- A failed run: the WiFi dialog assertion was deliberately altered to demonstrate evidence capture and defect drafting