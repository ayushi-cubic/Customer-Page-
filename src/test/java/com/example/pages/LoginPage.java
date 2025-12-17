package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Login Page
 * Handles all interactions with the login page
 */
public class LoginPage {

    private final WebDriver driver;
    private final WaitHelper waitHelper;

    // Locators
    private final By emailField = By.id("USEREMAILID");
    private final By passwordField = By.id("USERPASSWORD");
    private final By signInButton = By.id("submitbtn");

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    /**
     * Enter email in the email field
     * @param email Email to enter
     */
    public void enterEmail(String email) {
        waitHelper.waitAndSendKeys(emailField, email);
    }

    /**
     * Enter password in the password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        waitHelper.waitAndSendKeys(passwordField, password);
    }

    /**
     * Click on Sign In button
     */
    public void clickSignIn() {
        waitHelper.waitAndClick(signInButton);
    }

    /**
     * Complete login process
     * @param email Email
     * @param password Password
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

    /**
     * Verify login page is displayed
     * @return true if login page is displayed
     */
    public boolean isLoginPageDisplayed() {
        return waitHelper.waitForElementVisible(emailField).isDisplayed();
    }
}
