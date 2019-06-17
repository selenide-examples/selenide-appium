package org.selenide.examples.appium;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

/**
 * Copied from https://github.com/appium/sample-code/blob/master/sample-code/examples/java/junit/src/test/java/com/saucelabs/appium/AndroidDragAndDrop.java
 * and modified to use Selenide framework.
 */
public class AndroidDragAndDrop {
  private AppiumDriver driver;

  @Before
  public void setUp() throws Exception {
    File app = downloadApk();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
    capabilities.setCapability("automationName", "Appium");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("deviceName", "Android Emulator");
    capabilities.setCapability("app", app.getAbsolutePath());
    capabilities.setCapability("appPackage", "io.appium.android.apis");
    capabilities.setCapability("appActivity", ".ApiDemos");
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

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

  @Test
  public void dragAndDrop() {
    $(By.xpath(".//*[@text='Views']")).click();
    $(By.xpath(".//*[@text='Drag and Drop']")).click();

    SelenideElement from = $(By.id("io.appium.android.apis:id/drag_dot_1")).shouldBe(visible);
    SelenideElement to = $(By.id("io.appium.android.apis:id/drag_dot_2")).shouldBe(visible);
    By dragText = By.id("io.appium.android.apis:id/drag_text");

    $(dragText).shouldHave(exactText(""));

    new TouchAction(driver)
        .longPress(ElementOption.element(from.toWebElement())) // TODO Proxy cast to RemoteWebElement
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
        .moveTo(ElementOption.element(to.toWebElement())) // TODO Proxy cast to RemoteWebElement
        .release()
        .perform();

    $(dragText)
        .shouldBe(visible)
        .shouldHave(text("Dot"), text("DraggableDot"));
  }
}