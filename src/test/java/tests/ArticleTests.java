package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void ArticleHasTitleTest() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver, wait);
        String article_title = ArticlePageObject.getArticleTitle();
        System.out.println("Article title: " + article_title);

    }
}
