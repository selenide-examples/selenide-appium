package pageobject.calculator;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.appium.SelenideAppium.$;

public class HomePage {
    static final String DIGIT_SELECTOR_PATTERN = "digit_%d";
    static final By OP_ADD_SELECTOR = By.id("op_add");
    static final By EQUAL_SELECTOR = By.id("eq");
    static final By RESULT_SELECTOR = By.id("result");

    static By getDigitSelector(int digit) {
        return By.id(DIGIT_SELECTOR_PATTERN.formatted(digit));
    }

    public HomePage clickDigit(int digit) {
        $(getDigitSelector(digit)).click();
        return this;
    }

    public HomePage clickOpAdd() {
        $(OP_ADD_SELECTOR).click();
        return this;
    }

    public HomePage clickEq() {
        $(EQUAL_SELECTOR).click();
        return this;
    }

    public static SelenideAppiumElement resultElement() {
        return $(RESULT_SELECTOR);
    }
}
