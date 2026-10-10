package com.rishi.sit.mobile;

import com.rishi.sit.base.BaseTest;
import com.rishi.sit.pages.HomePage;
import com.rishi.sit.pages.PreferencePage;
import com.rishi.sit.utils.DbUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WifiSettingsJourneyTest extends BaseTest {

  @DataProvider(name = "wifi")
  public Object[][] wifi() throws Exception {
    DbUtil.seed();
    return new Object[][]{
        {DbUtil.getUser(1).get("wifi_name")},
        {DbUtil.getUser(2).get("wifi_name")}
    };
  }

  @Test(dataProvider = "wifi", description = "TC-MOB-01 Preference dependency journey with DB-seeded SSID")
  public void tcMob01_wifiSettingsJourney(String ssid) {
    PreferencePage p = new HomePage(driver).openPreferenceDependencies().enableWifi().openWifiDialog();
    Assert.assertEquals(p.dialogTitle(), "WiFi settings");
    p.enterSsid(ssid).confirm();
  }

  @Test(description = "TC-MOB-02 Views list is reachable")
  public void tcMob02_viewsNavigation() {
    Assert.assertTrue(new HomePage(driver).openViewsAndSeeButtons());
  }
}