package org.selenide.examples.calculator;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import pageobject.calculator.HomePage;
import webdriverprovider.AndroidDriverWithCalculator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static pageobject.calculator.HomePage.resultElement;

/**
 * Thanks to <a href="https://github.com/mkpythonanywhereblog">Maryna Kolesnik</a> for this example!
 * Her original sample can be found <a href="https://gist.github.com/mkpythonanywhereblog/d1fb3dca2e66146f519f">here</a>
 */
public class CalculatorTest {

  static final HomePage HOME_PAGE = new HomePage();

  @BeforeEach
  void setUp() {
    closeWebDriver();
    Configuration.browserSize = null;
    Configuration.browser = AndroidDriverWithCalculator.class.getName();
    WebDriverRunner.addListener(new WebDriverListener() {
      @Override
      public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
      }
    });

    open();
  }

  @Test
  void addTest() {
    HOME_PAGE
      .clickDigit(2)
      .clickOpAdd()
      .clickDigit(4)
      .clickEq();
    resultElement()
      .shouldHave(text("6"))
    ;
  }
}
