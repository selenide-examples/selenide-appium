package org.selenide.examples.appium;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.$;

/**
 * Copied from <a href="https://github.com/appium/sample-code/blob/master/sample-code/examples/java/junit/src/test/java/com/saucelabs/appium/AndroidDragAndDrop.java">AndroidDragAndDrop.java</a>
 * and modified to use Selenide framework.
 */
public class AndroidDragAndDropTest extends AbstractApiDemosTest {
  @Test
  void dragAndDrop() {
    $(By.xpath(".//*[@text='Views']")).click();
    $(By.xpath(".//*[@text='Drag and Drop']")).click();

    SelenideElement draggable = $(By.id("io.appium.android.apis:id/drag_dot_1")).shouldBe(visible);
    SelenideElement target = $(By.id("io.appium.android.apis:id/drag_dot_2")).shouldBe(visible);
    By dragText = By.id("io.appium.android.apis:id/drag_text");

    $(dragText).shouldHave(exactText(""));

    draggable.dragAndDrop(to(target));

    $(dragText)
        .shouldBe(visible)
        .shouldHave(text("Dot"), text("DraggableDot"));
  }
}