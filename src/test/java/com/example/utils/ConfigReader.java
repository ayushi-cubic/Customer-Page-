package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for reading configuration properties
 * Provides centralized access to test configuration
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
            // Use default values if file not found
            setDefaultProperties();
        }
    }

    /**
     * Set default properties if config file is not found
     */
    private static void setDefaultProperties() {
        properties.setProperty("app.url", "https://qalmsbu.cubictree.com/");
        properties.setProperty("login.email", "ayushi");
        properties.setProperty("login.password", "Legal@1234");
        properties.setProperty("otp.code", "123456");
        properties.setProperty("browser", "chrome");
        properties.setProperty("implicit.wait", "10");
        properties.setProperty("explicit.wait", "20");
        properties.setProperty("page.load.timeout", "30");
        properties.setProperty("headless.mode", "false");
    }

    /**
     * Get property value by key
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            System.err.println("Property not found: " + key);
        }
        return value;
    }

    /**
     * Get property with default value
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get application URL
     * @return Application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url", "https://qalmsbu.cubictree.com/");
    }

    /**
     * Get login email
     * @return Login email
     */
    public static String getLoginEmail() {
        return getProperty("login.email", "ayushi");
    }

    /**
     * Get login password
     * @return Login password
     */
    public static String getLoginPassword() {
        return getProperty("login.password", "Legal@1234");
    }

    /**
     * Get OTP code
     * @return OTP code
     */
    public static String getOtpCode() {
        return getProperty("otp.code", "123456");
    }

    /**
     * Get browser name
     * @return Browser name
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Get implicit wait timeout
     * @return Implicit wait in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    /**
     * Get explicit wait timeout
     * @return Explicit wait in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "20"));
    }

    /**
     * Get page load timeout
     * @return Page load timeout in seconds
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "30"));
    }

    /**
     * Check if headless mode is enabled
     * @return true if headless mode is enabled
     */
    public static boolean isHeadlessMode() {
        return Boolean.parseBoolean(getProperty("headless.mode", "false"));
    }

    /**
     * Get screenshot directory
     * @return Screenshot directory path
     */
    public static String getScreenshotDirectory() {
        return getProperty("screenshot.directory", "test-output/screenshots/");
    }

    /**
     * Get report directory
     * @return Report directory path
     */
    public static String getReportDirectory() {
        return getProperty("report.directory", "test-output/cucumber-reports/");
    }
}
