package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for OTP Page
 * Handles OTP verification after login
 */
public class OtpPage {

    private final WebDriver driver;
    private final WaitHelper waitHelper;

    // Locators
    private final By otpField = By.id("Otp");
    private final By submitButton = By.id("submitbtn");

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public OtpPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    /**
     * Enter OTP in the OTP field
     * @param otp OTP to enter
     */
    public void enterOtp(String otp) {
        waitHelper.waitAndSendKeys(otpField, otp);
    }

    /**
     * Click on Submit button
     */
    public void clickSubmit() {
        waitHelper.waitAndClick(submitButton);
    }

    /**
     * Complete OTP verification
     * @param otp OTP code
     */
    public void verifyOtp(String otp) {
        enterOtp(otp);
        clickSubmit();
    }

    /**
     * Verify OTP page is displayed
     * @return true if OTP page is displayed
     */
    public boolean isOtpPageDisplayed() {
        return waitHelper.waitForElementVisible(otpField).isDisplayed();
    }
}
