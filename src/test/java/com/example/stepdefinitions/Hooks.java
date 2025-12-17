package com.example.stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hooks class for Cucumber test lifecycle management
 * Handles setup, teardown, and screenshot capture
 */
public class Hooks {

    private static WebDriver driver;
    private static final String BROWSER = System.getProperty("browser", "chrome").toLowerCase();
    private static final int IMPLICIT_WAIT = 10;
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    /**
     * Get WebDriver instance
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Setup method - runs before each scenario
     * Initializes WebDriver and configures browser
     */
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("========================================");

        // Initialize WebDriver based on browser property
        initializeDriver();

        // Configure WebDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        System.out.println("Browser initialized: " + BROWSER);
    }

    /**
     * Initialize WebDriver based on browser type
     */
    private void initializeDriver() {
        switch (BROWSER) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--remote-allow-origins=*");
                // Uncomment for headless mode
                // chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                // Uncomment for headless mode
                // firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-notifications");
                // Uncomment for headless mode
                // edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                System.out.println("Browser not supported. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
    }

    /**
     * Teardown method - runs after each scenario
     * Closes browser and cleans up resources
     */
    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot if scenario failed
        if (scenario.isFailed()) {
            captureScreenshot(scenario);
        }

        System.out.println("========================================");
        System.out.println("Scenario Status: " + scenario.getStatus());
        System.out.println("========================================");

        // Close browser
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Capture screenshot after each step (optional)
     * Uncomment the @AfterStep annotation to enable
     */
    // @AfterStep
    public void captureScreenshotAfterStep(Scenario scenario) {
        if (driver != null) {
            captureScreenshot(scenario);
        }
    }

    /**
     * Capture screenshot and attach to report
     * @param scenario Current scenario
     */
    private void captureScreenshot(Scenario scenario) {
        if (driver != null) {
            try {
                // Take screenshot as byte array for Cucumber report
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());

                // Save screenshot to file system
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
                File destinationFile = new File(SCREENSHOT_DIR + screenshotName);

                // Create directory if it doesn't exist
                FileUtils.forceMkdir(new File(SCREENSHOT_DIR));

                // Copy screenshot to destination
                FileUtils.copyFile(screenshotFile, destinationFile);

                System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }


}
