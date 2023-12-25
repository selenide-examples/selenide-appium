package pageobject.apidemos;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.appium.SelenideAppium.$;


public class HomePage {
    static final By VIEWS_SELECTOR = By.xpath(".//*[@text='Views']");

    public static SelenideAppiumElement viewsElement() {
        return $(VIEWS_SELECTOR);
    }
}
