package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyListsPageObject extends MainPageObject {

    private static final String
            VIEW_LIST_BUTTON = "//*[@resource-id='org.wikipedia:id/snackbar_action' and @text='View list']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}'] ",
            GOT_IT_BUTTON = "//*[@resource-id='org.wikipedia:id/buttonView' and @text='Got it']",
            CLOSE_BUTTON = "org.wikipedia:id/closeButton",
            PAGE_WEB_VIEW = "org.wikipedia:id/page_web_view";

    public MyListsPageObject (AppiumDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    // TEMPLATES METHODS //

    public static String getSavedArticleXpathByTitle(String article_title) {
        return  ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    // TEMPLATES METHODS //


    public void openListFromSnackbar() {
        this.waitForElementAndClick(
                By.xpath(VIEW_LIST_BUTTON), "Cannot view the list", 5);

        try {
            this.waitForElementAndClick(
                    By.xpath(GOT_IT_BUTTON),
                    "Cannot find Got it tip overlay", 5);
        } catch (Exception e) {
        }
    }

    public void waitForArticleToAppearByTitle (String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find saved article by title" + article_title, 10);
    }


    public void waitForArticleToDisappearByTitle (String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Saved article still present with title" + article_title, 10);
    }


    public void swipeByArticleToDelete (String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft
                (By.xpath(article_xpath), "The article is not in the list");
        this.waitForArticleToDisappearByTitle(article_title);
    }


    public void openArticleInTheList(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(By.xpath(article_xpath), "Cannot find and click article: " + article_title, 5);

        try {
            this.waitForElementAndClick(By.id(CLOSE_BUTTON), "Cannot find the X button", 5);
        } catch (Exception e) {
        }

        try {
            this.waitForElementAndClick(By.id(PAGE_WEB_VIEW), "Cannot find Got it message", 5);
        } catch (Exception e) {
        }
    }

}
