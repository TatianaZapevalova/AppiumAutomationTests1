package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationUI extends MainPageObject {

    public static final String
            NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public NavigationUI (AppiumDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    // Тап по кнопке "Назад" в навигационной панели

    public void  tapNavigateUpButton()
    {
        this.waitForElementAndClick(By.xpath(NAVIGATE_UP_BUTTON),"Cannot find and click navigate up button", 10);
    }

}