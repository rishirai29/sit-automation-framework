package com.rishi.sit.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class PreferencePage {
  private final AndroidDriver d;

  public PreferencePage(AndroidDriver d) {
    this.d = d;
  }

  public PreferencePage enableWifi() {
    d.findElement(By.id("android:id/checkbox")).click();
    return this;
  }

  public PreferencePage openWifiDialog() {
    d.findElement(AppiumBy.xpath("//android.widget.TextView[@text='WiFi settings']")).click();
    return this;
  }

  public String dialogTitle() {
    return d.findElement(By.id("android:id/alertTitle")).getText();
  }

  public PreferencePage enterSsid(String ssid) {
    d.findElement(By.id("android:id/edit")).sendKeys(ssid);
    return this;
  }

  public void confirm() {
    d.findElement(By.id("android:id/button1")).click();
  }
}