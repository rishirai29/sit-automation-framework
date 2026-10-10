package com.rishi.sit.listeners;

import com.rishi.sit.base.BaseTest;
import java.io.File;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class EvidenceListener implements ITestListener {
  private static final String RUN_ID = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
  private static final Path DIR = Paths.get("evidence", RUN_ID);
  private static final List<String> ROWS = new ArrayList<>();

  @Override
  public void onStart(ITestContext c) {
    try { Files.createDirectories(DIR); } catch (Exception e) { throw new RuntimeException(e); }
  }

  @Override public void onTestSuccess(ITestResult r) { record(r, "PASS"); }

  @Override
  public void onTestFailure(ITestResult r) {
    record(r, "FAIL");
    writeDefect(r);
  }

  @Override public void onTestSkipped(ITestResult r) { record(r, "BLOCKED"); }

  @Override
  public void onFinish(ITestContext c) {
    try {
      Files.write(DIR.resolve("execution_summary.csv"), (String.join("\n", ROWS) + "\n").getBytes(),
          StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    } catch (Exception e) { e.printStackTrace(); }
  }

  private String safe(ITestResult r) {
    return r.getMethod().getMethodName() + "_" + Math.abs(Arrays.hashCode(r.getParameters()));
  }

  private void record(ITestResult r, String status) {
    if (ROWS.isEmpty()) ROWS.add("test,status,duration_ms,time");
    String n = safe(r);
    if (r.getInstance() instanceof BaseTest bt && bt.getDriver() != null) {
      try {
        File shot = bt.getDriver().getScreenshotAs(OutputType.FILE);
        Files.copy(shot.toPath(), DIR.resolve(n + "_" + status + ".png"), StandardCopyOption.REPLACE_EXISTING);
        new ProcessBuilder("adb", "logcat", "-d")
            .redirectOutput(DIR.resolve(n + "_logcat.txt").toFile()).start().waitFor();
      } catch (Exception e) { e.printStackTrace(); }
    }
    ROWS.add(n + "," + status + "," + (r.getEndMillis() - r.getStartMillis()) + "," + new Date());
  }

  private void writeDefect(ITestResult r) {
    String n = safe(r);
    String md = "# Defect Draft: " + n + "\n\n| Field | Value |\n|---|---|\n"
        + "| Title | " + r.getMethod().getMethodName() + " failed |\n"
        + "| Severity | TBD |\n"
        + "| Environment | Android emulator Pixel_6 (API 34), Appium 3, UiAutomator2 |\n"
        + "| Run ID | " + RUN_ID + " |\n"
        + "| Test data | " + Arrays.toString(r.getParameters()) + " |\n"
        + "| Evidence | " + n + "_FAIL.png, " + n + "_logcat.txt |\n\n"
        + "## Actual result\n" + r.getThrowable() + "\n\n"
        + "## Expected result\nTBD\n\n## Steps to reproduce\n1. TBD\n";
    try { Files.writeString(DIR.resolve(n + "_DEFECT.md"), md); } catch (Exception e) { e.printStackTrace(); }
  }
}