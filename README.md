# Mobile and API SIT Automation Framework

End-to-end test automation for an Android app journey and API-to-database data validation, with automatic evidence capture and defect drafting.

## Tech stack
Java, TestNG, Appium (UiAutomator2), RestAssured, SQLite (JDBC), Maven

## What it does
- Runs progression and regression journeys on an Android emulator (Pixel_6, API 34) against the public ApiDemos app
- Validates API user records (JSONPlaceholder) against expected records seeded from SQL
- Uses SQL seed data to drive the mobile journeys, with no manual data setup
- Captures screenshots, device logs and an execution summary CSV for every run
- Drafts a defect report with environment, test data and evidence links for every failure

## Project structure
- `src/test/java/.../base`: driver setup
- `src/test/java/.../pages`: page objects
- `src/test/java/.../mobile` and `api`: test cases
- `src/test/java/.../listeners`: evidence and defect listener
- `testdata/seed.sql`: seed data for the database
- `docs/`: SIT test plan and traceability matrix
- `evidence/`: sample runs (one clean, one failed with defect drafts)

## Prerequisites
- JDK 17 or later, Maven, Node.js
- Android Studio with an API 34 emulator named Pixel_6
- Appium 3 with the uiautomator2 driver (`npm install -g appium` then `appium driver install uiautomator2`)
- `ApiDemos-debug.apk` placed in the `apps/` folder

## How to run
1. Start the Android emulator.
2. In a separate terminal, run `appium` and leave it open.
3. Run everything: `mvn clean test`
4. Run API and database checks only: `mvn clean test "-Dsuite=testng-api.xml"`

## Test cases
| ID | Description | Type |
|---|---|---|
| TC-API-01 | API user record matches the expected database record (3 data sets) | API |
| TC-MOB-01 | Preference dependency journey with a database-seeded SSID (2 data sets) | Mobile |
| TC-MOB-02 | Views screen is reachable | Mobile |

See `docs/SIT_Test_Plan.md` and `docs/Traceability_Matrix.csv`.

## Evidence
Each run creates a folder under `evidence/` with:
- `execution_summary.csv` (test, status, duration, time)
- A screenshot and device log for each mobile execution
- A `_DEFECT.md` draft for each failure

Sample runs included:
- Clean run: 6 of 6 passed
- Failed run: the Wi-Fi dialog assertion was deliberately altered to demonstrate automatic evidence capture and defect drafting

## Note
The mobile suite runs on a local emulator. The API and database suite (`testng-api.xml`) runs without a device.