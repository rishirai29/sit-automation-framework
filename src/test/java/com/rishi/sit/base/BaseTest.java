package com.rishi.sit.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
  protected AndroidDriver driver;

  @BeforeMethod
  public void setUp() throws Exception {
    new ProcessBuilder("adb", "logcat", "-c").start().waitFor();
    UiAutomator2Options options = new UiAutomator2Options()
        .setPlatformName("Android")
        .setDeviceName("Pixel_6")
        .setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    if (driver != null) driver.quit();
  }

  public AndroidDriver getDriver() {
    return driver;
  }
}