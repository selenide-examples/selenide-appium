package org.selenide.examples.appium;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;

/**
 * Copied from https://github.com/appium/sample-code/blob/master/sample-code/examples/java/junit/src/test/java/com/saucelabs/appium/AndroidContactsTest.java
 * and modified to use Selenide framework.
 */
public class AndroidContactsTest {

    private AndroidDriver driver;

    @Before
    public void setUpAppium() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
//        File appDir = new File(classpathRoot, "../../../apps/ContactManager");
//        File app = new File(appDir, "ContactManager.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
//        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.android.contactmanager");
        capabilities.setCapability("appActivity", ".ContactManager");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void closeAppiumWebdriver() {
        close();
    }

    @Test
    public void addContact() {
        WebElement el = $(By.xpath(".//*[@text='Add Contact']"));
        el.click();
        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(2).sendKeys("Some@example.com");


//        driver.swipe(100, 500, 100, 100, 2);
//        Causes the following error in Appium logs:
        /*
        info: [BOOTSTRAP] [debug] Got command action: swipe
        info: Responding to client with error: {"status":13,"value":{"message":"An unknown server-side error occurred while processing the command.","origValue":"Y coordinate (500.0 is outside of screen height: 432.0"},"sessionId":"716f334c-31ac-438b-91f2-b214b70910c0"}
        POST /wd/hub/session/716f334c-31ac-438b-91f2-b214b70910c0/touch/perform 500 6ms - 251b
        info: [BOOTSTRAP] [info] Returning result: {"value":"Y coordinate (500.0 is outside of screen height: 432.0","status":13}
        */


//        driver.findElementByXPath(".//*[@text='Save']").click();
//        Causes "Application unexpectedly stopped" error in Android emulator
    }
}