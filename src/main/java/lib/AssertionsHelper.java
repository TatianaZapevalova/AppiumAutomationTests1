package lib;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssertionsHelper extends MainPageObject {

    private static final String
            SEARCH_RESULT_TITLE = "org.wikipedia:id/page_list_item_title",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title']//*[@text='{SUBSTRING}']";


    public AssertionsHelper(AppiumDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // TEMPLATE METHOD
    private static String getSearchResultBySubstring(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    // Проверяет, что на странице найдено более одного результата

    public void assertSearchResultsMoreThanOne() {
        this.assertElementsCountMoreThanOneById(
                SEARCH_RESULT_TITLE,
                "Expected more than one search result, but found one or none.",
                10
        );
    }

    // Проверяет, что каждый найденный результат содержит указанное слово

    public void assertEachResultContains(String word) {
        this.assertEachElementContainsQuery(
                By.id(SEARCH_RESULT_TITLE),
                word,
                "Not all search results contain the word: " + word,
                10
        );
    }

    // Проверяет, что хотя бы один результат содержит конкретную подстроку

    public void assertResultWithSubstringPresent(String substring) {
        String searchResultXpath = getSearchResultBySubstring(substring);
        WebElement element = this.waitForElementPresent(
                By.xpath(searchResultXpath),
                "Cannot find search result containing substring: " + substring,
                10
        );
        Assertions.assertTrue(element.isDisplayed(), "Search result with substring is not displayed");
    }

    //Проверяет, что конкретный элемент содержит ожидаемый текст

    public void assertElementHasExactText(By by, String expectedText) {
        this.assertElementHasText(by, expectedText, "Element text does not match expected value");
    }

    // Проверяет, что конкретный элемент не пустой
    public void assertElementHasAnyText(By by) {
        this.assertElementHasText(by, "Element has no text");
    }
}