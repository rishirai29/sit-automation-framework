package com.rishi.sit.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class HomePage {
  private final AndroidDriver d;

  public HomePage(AndroidDriver d) {
    this.d = d;
  }

  public PreferencePage openPreferenceDependencies() {
    d.findElement(AppiumBy.accessibilityId("Preference")).click();
    d.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
    return new PreferencePage(d);
  }

  public boolean openViewsAndSeeButtons() {
    d.findElement(AppiumBy.accessibilityId("Views")).click();
    return d.findElement(AppiumBy.accessibilityId("Buttons")).isDisplayed();
  }
}