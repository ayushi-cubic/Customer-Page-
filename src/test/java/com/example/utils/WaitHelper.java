package com.example.utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class for handling explicit waits in Selenium
 * Provides reusable wait methods for common scenarios
 */
public class WaitHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 20;
    private static final int POLLING_INTERVAL = 500;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.wait.pollingEvery(Duration.ofMillis(POLLING_INTERVAL));
        this.wait.ignoring(NoSuchElementException.class);
        this.wait.ignoring(StaleElementReferenceException.class);
    }

    public WaitHelper(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        this.wait.pollingEvery(Duration.ofMillis(POLLING_INTERVAL));
        this.wait.ignoring(NoSuchElementException.class);
        this.wait.ignoring(StaleElementReferenceException.class);
    }

    /**
     * Wait for element to be visible
     * @param locator By locator
     * @return WebElement
     */
    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be visible
     * @param element WebElement
     * @return WebElement
     */
    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for element to be clickable
     * @param locator By locator
     * @return WebElement
     */
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be clickable
     * @param element WebElement
     * @return WebElement
     */
    public WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for element to be present in DOM
     * @param locator By locator
     * @return WebElement
     */
    public WebElement waitForElementPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to be invisible
     * @param locator By locator
     * @return boolean
     */
    public boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element
     * @param locator By locator
     * @param text Text to wait for
     * @return boolean
     */
    public boolean waitForTextPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait for alert to be present
     * @return Alert
     */
    public Alert waitForAlertPresent() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Wait for frame to be available and switch to it
     * @param locator By locator
     * @return WebDriver
     */
    public WebDriver waitForFrameAndSwitch(By locator) {
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    /**
     * Wait for page title to contain text
     * @param title Title text
     * @return boolean
     */
    public boolean waitForTitleContains(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Wait for URL to contain text
     * @param urlFragment URL fragment
     * @return boolean
     */
    public boolean waitForUrlContains(String urlFragment) {
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    /**
     * Wait for element attribute to contain value
     * @param locator By locator
     * @param attribute Attribute name
     * @param value Expected value
     * @return boolean
     */
    public boolean waitForAttributeContains(By locator, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    /**
     * Wait for number of elements to be more than
     * @param locator By locator
     * @param number Expected minimum number
     * @return List of elements
     */
    public java.util.List<WebElement> waitForNumberOfElementsToBeMoreThan(By locator, int number) {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
    }

    /**
     * Wait for element to be selected
     * @param locator By locator
     * @return boolean
     */
    public boolean waitForElementToBeSelected(By locator) {
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    /**
     * Wait for staleness of element
     * @param element WebElement
     * @return boolean
     */
    public boolean waitForStalenessOf(WebElement element) {
        return wait.until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Custom wait with custom expected condition
     * @param condition ExpectedCondition
     * @param <T> Return type
     * @return Result of condition
     */
    public <T> T waitForCondition(ExpectedCondition<T> condition) {
        return wait.until(condition);
    }

    /**
     * Wait and click element with retry mechanism
     * @param locator By locator
     */
    public void waitAndClick(By locator) {
        WebElement element = waitForElementClickable(locator);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Retry with JavaScript click if normal click fails
            jsClick(element);
        }
    }

    /**
     * Wait and send keys to element
     * @param locator By locator
     * @param text Text to send
     */
    public void waitAndSendKeys(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * JavaScript click
     * @param element WebElement
     */
    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Scroll element into view
     * @param element WebElement
     */
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
    }

    /**
     * Hard wait (use sparingly)
     * @param milliseconds Milliseconds to wait
     */
    public void hardWait(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Wait for page to load completely
     */
    public void waitForPageLoad() {
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return js.executeScript("return document.readyState").toString().equals("complete");
        });
    }

    /**
     * Wait for jQuery to complete (if jQuery is present)
     */
    public void waitForJQueryLoad() {
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            try {
                return (Boolean) js.executeScript("return jQuery.active == 0");
            } catch (Exception e) {
                return true; // jQuery not present
            }
        });
    }
}
