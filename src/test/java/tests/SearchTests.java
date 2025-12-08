package tests;

import lib.AssertionsHelper;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {

    private static final By SEARCH_INPUT = By.id("org.wikipedia:id/search_src_text");
    private static final String SEARCH_PLACEHOLDER = "Search Wikipedia";

    @Test

    public void testSearchFieldHasPlaceholderText() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        AssertionsHelper AssertionsHelper = new AssertionsHelper(driver, wait);
        AssertionsHelper.assertElementHasExactText(SEARCH_INPUT, SEARCH_PLACEHOLDER);

    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        AssertionsHelper AssertionsHelper = new AssertionsHelper(driver, wait);
        AssertionsHelper.assertSearchResultsMoreThanOne();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
        AssertionsHelper.waitForSearchResultsToDisappear();

    }

    @Test
    public void testEachSearchResultContainsQuery() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        AssertionsHelper AssertionsHelper = new AssertionsHelper(driver, wait);
        AssertionsHelper.assertEachResultContains("Java");
    }

}
