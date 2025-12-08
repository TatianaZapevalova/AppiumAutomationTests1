package tests;

import lib.AssertionsHelper;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCheckArticleHasTitleAndPrintTitleToConsole() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        AssertionsHelper AssertionsHelper = new AssertionsHelper(driver, wait);
        By titleLocator = By.xpath(ArticlePageObject.TITLE);
        AssertionsHelper.assertElementHasAnyText(titleLocator);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver, wait);
        String article_title = ArticlePageObject.getArticleTitle();
        System.out.println("Article title: " + article_title);

    }
}
