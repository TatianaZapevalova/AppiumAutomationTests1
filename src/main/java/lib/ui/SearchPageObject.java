package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "//*[@text[contains(.,'Search Wikipedia')]]",
            SEARCH_INPUT = "//*[@text[contains(.,'Search Wikipedia')]]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
            SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            CLOSE_BUTTON = "org.wikipedia:id/closeButton",
            PAGE_WEB_VIEW = "org.wikipedia:id/page_web_view";

    public SearchPageObject(AppiumDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // TEMPLATES METHODS //
    public static String getResultSearchElement(String substring)
    {
        return  SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    // TEMPLATES METHODS //

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clicking search init element", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Search cancel button is still present", 5);
    }

    public void  clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Cannot find and click search cancel button", 5);
    }


    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring" + substring, 5);
    }


    public void clickByArticleWithSubstring (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click search result with substring" + substring, 5);
        ;
        try {
            this.waitForElementAndClick(
                    By.id(CLOSE_BUTTON),
                    "Cannot find the X button", 5);
        } catch (Exception e) {
        }

        try {
            this.waitForElementAndClick(
                    By.id(PAGE_WEB_VIEW),
                    "Cannot find Got it tip overlay", 5);
        } catch (Exception e) {
        }
    }

    public void longTapOnArticleWithSubstring (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.longTapOnArticle(substring,"Cannot find and do long tap on search result with substring" + substring, 5);
    }

}