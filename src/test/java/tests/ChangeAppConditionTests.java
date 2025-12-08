package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testCheckArticleTitleAfterRotate() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver, wait);
        String titleBeforeRotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        this.rotateScreenPortrait();
        String titleAfterRotation = ArticlePageObject.getArticleTitle();
        Assertions.assertEquals (titleBeforeRotation, titleAfterRotation, "Article title has been changed after rotation");

    }

}