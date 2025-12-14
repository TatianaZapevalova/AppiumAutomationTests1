package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testArticleTitleMatchesExpectedTitle() {

        String expectedTitle = "Java (programming language)";
        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithPageListItemTitle(expectedTitle);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver, wait);
        String article_title = ArticlePageObject.getArticleTitle();
        Assertions.assertEquals (expectedTitle, article_title, "The article title does not match");

    }
}
