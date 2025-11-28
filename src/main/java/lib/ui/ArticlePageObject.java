package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.HasContext;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Set;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "//android.widget.TextView[@heading='true']",
            SAVE_BUTTON = "//*[@resource-id='org.wikipedia:id/title' and @text='Save']",
            ADD_TO_LIST_BUTTON = "//*[@resource-id='org.wikipedia:id/snackbar_action' and @text='Add to list']",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            My_LIST_OK_BUTTON = "android:id/button1",
            NAME_OF_EXISTING_FOLDER_TPL = "//*[@resource-id='org.wikipedia:id/item_title' and @text='{NAME_OF_FOLDER}']";

    public ArticlePageObject (AppiumDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Метод, который ожидает появление заголовка статьи на экране:

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title", 10);
    }

    // Метод, в котором будем получать название статьи:
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    // TEMPLATES METHODS //
    public static String getNameOfExistingFolder(String name_of_folder)
    {
        return NAME_OF_EXISTING_FOLDER_TPL.replace("{NAME_OF_FOLDER}", name_of_folder);
    }
// TEMPLATES METHODS //


    // Метод, который добавляет статью по лонг тапу на результат поиска и выбору опции в выпадающем меню в новый список

    public void AddArticleFromDropDownMenuToTheListBeingCreated (String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON), "Cannot find Save button", 5);

        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON), "Cannot find option to add article to the list", 5);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT), name_of_folder, "Cannot put text into articles folder input", 5);

        this.waitForElementAndClick(
                By.id(My_LIST_OK_BUTTON), "Cannot press OK", 5);
    }


// Метод, который добавляет статью по лонг тапу на результат поиска и выбору опции в выпадающем меню в СУЩЕСТВУЮЩИЙ список

    public void addArticleFromDropDownMenuToTheExistingList (String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON), "Cannot find Save button", 5);

        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON), "Cannot find option to add article to the list", 5);

        this.clickOnExistingFolder (name_of_folder);
    }

    public void clickOnExistingFolder (String name_of_folder)
    {
        String name_of_folder_xpath = getNameOfExistingFolder(name_of_folder);
        this.waitForElementAndClick(By.xpath(name_of_folder_xpath),"Cannot find and click on folder with name" + name_of_folder, 5);
    }





}