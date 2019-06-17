package org.selenide.examples.appium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Thanks to [Maryna Kolesnik](https://github.com/mkpythonanywhereblog) for this example!
 * Her original sample can be found [here](https://gist.github.com/mkpythonanywhereblog/d1fb3dca2e66146f519f) 
 */
public class CalculatorTest {

    @Before
    public void setUp() {
        close();
        Configuration.browser = AndroidDriverWithCalculator.class.getName();
        WebDriverRunner.setWebDriver(new AndroidDriverWithCalculator().createDriver(new DesiredCapabilities()));
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
    }

    @Test
    public void calculator(){
        $(By.id("digit_2")).click();
        $(By.id("op_add")).click();
        $(By.id("digit_4")).click();
        $(By.id("eq")).click();
        $(By.id("result")).shouldHave(text("6"));
    }
}
