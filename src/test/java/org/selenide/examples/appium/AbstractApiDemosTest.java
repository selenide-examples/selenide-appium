package org.selenide.examples.appium;

import com.codeborne.selenide.Configuration;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class AbstractApiDemosTest {
  @Before
  public void setUp() {
    close();
    Configuration.startMaximized = false;
    Configuration.browserSize = null;
    Configuration.browser = AndroidDriverWithDemos.class.getName();
    open("/");
  }

  @SuppressWarnings("unchecked")
  protected AndroidDriver<AndroidElement> driver() {
    return (AndroidDriver<AndroidElement>) getWebDriver();
  }
}
