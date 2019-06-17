package org.selenide.examples.appium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class AbstractApiDemosTest {
  @Before
  public void setUp() {
    close();
    Configuration.browser = AndroidDriverWithDemos.class.getName();
    WebDriverRunner.setWebDriver(new AndroidDriverWithDemos().createDriver(new DesiredCapabilities()));
  }

  @After
  public void tearDown() {
    getWebDriver().quit();
  }

  @SuppressWarnings("unchecked")
  protected AndroidDriver<AndroidElement> driver() {
    return (AndroidDriver<AndroidElement>) getWebDriver();
  }
}
