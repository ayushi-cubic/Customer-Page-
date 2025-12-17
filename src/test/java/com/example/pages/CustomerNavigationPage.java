package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.utils.WaitHelper;

/**
 * Page Object Model for Customer Navigation
 * Handles navigation to Customer menu and submenu
 */
public class CustomerNavigationPage {

    private final WebDriver driver;
    private final WaitHelper waitHelper;

    // Locators
    private final By borrowersMenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[2]/a");
    private final By customerSubmenu = By.xpath("/html/body/div[3]/div/div/div/ul/li[2]/ul/li[3]/a");
    private final By addNewCustomerButton = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div[1]/div/div[1]/div[2]/div/div[3]/a");

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public CustomerNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    /**
     * Click on Borrowers menu
     */
    public void clickBorrowersMenu() {
        waitHelper.waitAndClick(borrowersMenu);
    }

    /**
     * Click on Customer submenu
     */
    public void clickCustomerSubmenu() {
        waitHelper.waitAndClick(customerSubmenu);
    }

    /**
     * Click on Add New Customer button
     */
    public void clickAddNewCustomerButton() {
        waitHelper.waitAndClick(addNewCustomerButton);
    }

    /**
     * Navigate to Add Customer page
     */
    public void navigateToAddCustomer() {
        clickBorrowersMenu();
        clickCustomerSubmenu();
        clickAddNewCustomerButton();
    }

    /**
     * Verify successful login by checking if Borrowers menu is displayed
     * @return true if logged in successfully
     */
    public boolean isLoggedInSuccessfully() {
        return waitHelper.waitForElementVisible(borrowersMenu).isDisplayed();
    }
}
