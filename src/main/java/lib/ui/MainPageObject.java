package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageObject {

    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public MainPageObject(AppiumDriver driver, WebDriverWait wait)
    {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent (By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }


    public WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void assertElementsCountMoreThanOneById(String id, String errorMessage, long timeoutInSeconds) {
        By locator = By.id(id);
        List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .withMessage(errorMessage + "\n")
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

        Assertions.assertTrue(elements.size() > 1, errorMessage + " Expected more than one element, but found " + elements.size());
    }

    public void assertEachElementContainsQuery(By by, String word, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");

        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

        for (WebElement result : searchResults) {
            String title = result.getText();
            assertTrue(title.contains(word), errorMessage);
        }
    }

    public void longTapOnArticle(String expectedArticle, String errorMessage, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");

        WebElement articleElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text, '" + expectedArticle + "')]")
        ));

        Point location = articleElement.getLocation();
        Dimension size = articleElement.getSize();

        int x = location.getX() + size.getWidth() / 2;
        int y = location.getY() + size.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        //создаем движение пальца
        longPress.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), x, y));
        //палец нажимает экран
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.ordinal()));
        //пауза
        longPress.addAction(new Pause(finger, Duration.ofSeconds(2)));
        //палец отпускает экран
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.ordinal()));

        driver.perform(Arrays.asList(longPress));
    }

    public void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        // Создаём "палец"
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        // Нажатие в начале (правый край элемента)
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), right_x, middle_y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Задержка (имитация удержания)
        swipe.addAction(new Pause(finger, Duration.ofMillis(200)));

        // Перемещение к левому краю
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), left_x, middle_y));

        // Отпускаем палец
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Выполняем жест
        driver.perform(Arrays.asList(swipe));
    }

    public WebElement waitForElementAndClear (By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent (by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String actualText = element.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError(errorMessage + " Expected: '" + expectedText +
                    "', but was: '" + actualText + "'");
        }
    }

    public void assertElementHasText (By by, String errorMessage) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String actualText = element.getText();

        Assertions.assertTrue(
                actualText != null && !actualText.trim().isEmpty(),
                errorMessage + " Element has no text."
        );
    }

    private String getElementAttribute (By by, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);

    }


}