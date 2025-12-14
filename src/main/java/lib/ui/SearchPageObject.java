package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
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
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
            CLOSE_BUTTON = "org.wikipedia:id/closeButton",
            PAGE_WEB_VIEW = "org.wikipedia:id/page_web_view",
            NAME_OF_FOUND_ARTICLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{NAME_OF_PAGE_LIST_ITEM}']",
            SEARCH_RESULT_TITLE = "org.wikipedia:id/page_list_item_title",
            SEARCH_PLACEHOLDER = "Search Wikipedia";

    public SearchPageObject(AppiumDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // TEMPLATES METHODS //
    public static String getResultSearchElement(String substring)
    {
        return  SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    // Метод для получения названия найденной статьи с заданным name of page list item
    public static String getPageListItemTitle (String nameOfPageListItem)
    {
        return  NAME_OF_FOUND_ARTICLE_TPL.replace("{NAME_OF_PAGE_LIST_ITEM}", nameOfPageListItem);
    }
    // TEMPLATES METHODS //

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.id(SEARCH_INPUT), "Cannot find search input after clicking search init element", 5);
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
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring" + substring, 5);
    }


    public void clickByArticleWithSubstring (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click search result with substring" + substring, 5);

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
        String searchResultXpath = getResultSearchElement(substring);
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

    //Проверяет, что строка поиска содержит конкретный текст (плейсхолдер)
    public void assertSearchInputHasPlaceholder() {
        this.assertElementHasText(By.id(SEARCH_INPUT), SEARCH_PLACEHOLDER, "Element text does not match expected value");
    }

    // Проверяет, что результаты поиска исчезли
    public void waitForSearchResultsToDisappear() {
        this.waitForElementNotPresent(
                By.id(SEARCH_RESULT_TITLE),
                "Search results still present after cancel",
                5
        );
    }

    public void longTapOnArticleWithSubstring (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.longTapOnArticle(substring,"Cannot find and do long tap on search result with substring" + substring, 5);
    }

    public void clickByArticleWithPageListItemTitle (String nameOfPageListItem) {
        String page_list_item_xpath = getPageListItemTitle(nameOfPageListItem);
        this.waitForElementAndClick(By.xpath(page_list_item_xpath),"Cannot find and click search result with substring" + nameOfPageListItem, 5);
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

}