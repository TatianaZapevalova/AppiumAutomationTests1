package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {

    @Test

    public void testSearchFieldHasPlaceholderText() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.assertSearchInputHasPlaceholder();

    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertSearchResultsMoreThanOne();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
        SearchPageObject.waitForSearchResultsToDisappear();

    }

    @Test
    public void testEachSearchResultContainsQuery() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertEachResultContains("Java");
    }

}
