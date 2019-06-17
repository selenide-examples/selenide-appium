package org.selenide.examples.appium;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

/**
 * Thanks to [Maryna Kolesnik](https://github.com/mkpythonanywhereblog) for this example!
 * Her original sample can be found [here](https://gist.github.com/mkpythonanywhereblog/d1fb3dca2e66146f519f) 
 */
public class CalculatorTest {

    @Before
    public void setUp() {
        Configuration.browser = AndroidDriverWithCalculator.class.getName();
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
