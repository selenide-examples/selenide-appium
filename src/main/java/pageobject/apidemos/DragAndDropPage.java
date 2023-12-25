package pageobject.apidemos;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.appium.SelenideAppium.$;

public class DragAndDropPage {
    static final By DRAG_DOT_1_SELECTOR = By.id("io.appium.android.apis:id/drag_dot_1");
    static final By DRAG_DOT_2_SELECTOR = By.id("io.appium.android.apis:id/drag_dot_2");
    static final By DRAG_TEXT_SELECTOR = By.id("io.appium.android.apis:id/drag_text");

    public static SelenideElement dragDot1Element() {
        return $(DRAG_DOT_1_SELECTOR);
    }
    public static SelenideElement dragDot2Element() {
        return $(DRAG_DOT_2_SELECTOR);
    }
    public static SelenideElement dragTextElement() {
        return $(DRAG_TEXT_SELECTOR);
    }
}
