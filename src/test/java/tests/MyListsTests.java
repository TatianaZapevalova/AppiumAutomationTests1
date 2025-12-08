package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testArticleSaveToNewListAndToExistingListThenDelete() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver, wait);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        // Добавляем статью по лонг-тапу на результат поиска, создавая новый список

        SearchPageObject.longTapOnArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver, wait);
        String name_of_folder = "TestFolder";
        ArticlePageObject.AddArticleFromDropDownMenuToTheListBeingCreated (name_of_folder);

        // Открываем список

        MyListsPageObject MyListsPageObject = new MyListsPageObject (driver, wait);
        MyListsPageObject.openListFromSnackbar();

        // Возвращаемся назад к результатам поиска, стираем там предыдущее значение и инициализируем новый поиск

        NavigationUI NavigationUI = new NavigationUI(driver, wait);
        NavigationUI.tapNavigateUpButton();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");

        // Добавляем статью по лонг-тапу на результат поиска и добавляем статью в существующий список

        SearchPageObject.longTapOnArticleWithSubstring("Automation for Apps");
        ArticlePageObject.addArticleFromDropDownMenuToTheExistingList(name_of_folder);

        // Открываем список и удаляем первую статью

        MyListsPageObject.openListFromSnackbar();
        String article_title = "Java (programming language)";
        MyListsPageObject.swipeByArticleToDelete(article_title);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_title);

        // Проверяем, что название статьи на экране самой статьи соответствует ожидаемому значению

        String second_article_title = "Appium";
        MyListsPageObject.openArticleInTheList(second_article_title);
        String article_title_on_article_page = ArticlePageObject.getArticleTitle();
        Assertions.assertEquals(second_article_title, article_title_on_article_page, "The expected title of the article and the title on the article page are different");

    }

}
