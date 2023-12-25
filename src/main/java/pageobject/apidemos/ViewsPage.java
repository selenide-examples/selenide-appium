package pageobject.apidemos;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.appium.SelenideAppium.$;

public class ViewsPage {
    static final By DRAG_AND_DROP_SELECTOR = By.xpath(".//*[@text='Drag and Drop']");
    public static SelenideAppiumElement dragAndDropElement() {
        return $(DRAG_AND_DROP_SELECTOR);
    }
}
