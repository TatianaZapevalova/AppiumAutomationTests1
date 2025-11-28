package tests;

import lib.AssertionsHelper;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchFieldPlaceholderText() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        AssertionsHelper AssertionsHelper = new AssertionsHelper(driver, wait);
        AssertionsHelper.assertElementHasExactText(By.id("org.wikipedia:id/search_src_text"),"Search Wikipedia");

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
