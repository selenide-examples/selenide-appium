package org.selenide.examples.appium;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.codeborne.selenide.Selenide.close;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public abstract class AbstractApiDemosTest {
  private AndroidDriver<AndroidElement> driver;

  AndroidDriver<AndroidElement> driver() {
    return driver;
  }

  @Before
  public final void runApp() throws Exception {
    File app = downloadApk();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "Android Emulator");
    capabilities.setCapability("app", app.getAbsolutePath());
    capabilities.setCapability("appPackage", "io.appium.android.apis");
    capabilities.setCapability("appActivity", ".ApiDemos");
    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    WebDriverRunner.setWebDriver(driver);
  }

  private File downloadApk() throws IOException {
    File apk = new File("build/ApiDemos-debug.apk");
    if (!apk.exists()) {
      String url = "https://github.com/appium/sample-code/blob/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk?raw=true";
      try (InputStream in = new URL(url).openStream()) {
        copyInputStreamToFile(in, apk);
      }
    }
    return apk;
  }

  @After
  public void closeAppiumDriver() {
    close();
  }
}
