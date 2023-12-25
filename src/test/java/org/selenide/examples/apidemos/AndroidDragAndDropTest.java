package org.selenide.examples.apidemos;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.events.WebDriverListener;
import pageobject.apidemos.DragAndDropPage;
import pageobject.apidemos.HomePage;
import pageobject.apidemos.ViewsPage;
import webdriverprovider.AndroidDriverWithDemos;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

/**
 * Copied from <a href="https://github.com/appium/sample-code/blob/master/sample-code/examples/java/junit/src/test/java/com/saucelabs/appium/AndroidDragAndDrop.java">AndroidDragAndDrop.java</a>
 * and modified to use Selenide framework.
 */
public class AndroidDragAndDropTest {
  @BeforeEach
  void setUp() {
    closeWebDriver();
    Configuration.browserSize = null;
    Configuration.browser = AndroidDriverWithDemos.class.getName();
    WebDriverRunner.addListener(new WebDriverListener() {
      @Override
      public void beforeAnyCall(Object target, Method method, Object[] args) {
        WebDriverListener.super.beforeAnyCall(target, method, args);
      }
    });

    open();
  }
  @Test
  void dragAndDrop() {
    HomePage.viewsElement().click();
    ViewsPage.dragAndDropElement().click();

    DragAndDropPage
      .dragTextElement()
        .shouldHave(exactText(""));

    DragAndDropPage
      .dragDot1Element()
        .dragAndDrop(
          to(DragAndDropPage.dragDot2Element()));

    DragAndDropPage
      .dragTextElement()
        .shouldBe(visible)
        .shouldHave(text("Dot"), text("DraggableDot"));
  }
}