package org.selenide.examples.appium;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.codeborne.selenide.Condition.text;
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
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
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
    public void closeAppiumDriver() throws Exception {
        close();
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        scrollToViews();
        $(By.xpath(".//*[@text='Views']")).click();
        $(By.xpath(".//*[@text='Drag and Drop']")).click();
        
        new TouchAction(driver)
                .press($(By.id("io.appium.android.apis:id/drag_dot_1")))
                .waitAction(1000)
                .perform();

        new TouchAction(driver)
                .moveTo($(By.id("io.appium.android.apis:id/drag_dot_2")))
                .release()
                .waitAction(1000)
                .perform();
        
        $(By.id("io.appium.android.apis:id/drag_result_text")).shouldHave(text("Dropped!"));
    }

    /**
     * make "Views" visible even on small screen
     */
    private void scrollToViews() {
        driver.swipe(50, driver.manage().window().getSize().getHeight() - 10, 50, 10, 100);
    }
}