package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.example.utils.RandomDataGenerator;
import com.example.utils.WaitHelper;

/**
 * Page Object Model for Add Customer Page
 * Handles all customer form fields and interactions
 */
public class AddCustomerPage {

    private final WebDriver driver;
    private final WaitHelper waitHelper;

    // Basic Details Locators
    private final By customerNumberField = By.xpath("//*[@id='CustomerNo']");
    private final By customerNameField = By.xpath("//*[@id='Name']");
    private final By fatherNameField = By.xpath("//*[@id='FatherName']");
    private final By phoneNoField = By.xpath("//*[@id='PhoneNo']");
    private final By emailField = By.xpath("//*[@id='EmailId']");
    private final By mobileNoField = By.xpath("//*[@id='MobileNo']");
    private final By aadhaarNoField = By.xpath("//*[@id='AdhaarNo']");
    private final By addressField = By.id("AddressCust");
    private final By address2Field = By.id("AddressCust2");
    private final By remarksField = By.xpath("//*[@id='Remarks']");

    // Dropdown Locators
    private final By customerTypeDropdown = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div[2]/div[1]/div/select");
    private final By industryDropdown = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div[2]/div[2]/div/select");
    private final By segmentDropdown = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div[2]/div[3]/div/select");
    private final By businessUnitDropdown = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div[3]/div[2]/div/select");
    private final By zoneDropdown = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div[4]/div[1]/div/select");
    private final By stateDropdown = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div[4]/div[2]/div/select");
    private final By locationDropdown = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div[4]/div[3]/div/select");
    private final By addressTypeDropdown = By.xpath("/html/body/div[2]/div/main/div/div/main/div/div[2]/div/div/form/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div[5]/div/div/select");

    // Button Locators
    private final By nextButtonBasicDetails = By.xpath("//*[@id='createbasicdetailscust']");
    private final By nextButtonNPA = By.xpath("//*[@id='CustomerNPACreate']");
    private final By nextButtonCollapseThree = By.xpath("//*[@id='flush-collapseThree']/div/div[2]/button[2]");
    private final By nextButtonCollapseFour = By.xpath("//*[@id='flush-collapseFour']/div/div[2]/button[2]");
    private final By saveButton = By.xpath("//*[@id='CustomerCreateNew']");

    // Store generated data for verification
    private String generatedCustomerNumber;
    private String generatedCustomerName;

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    /**
     * Fill customer number with random data
     */
    public void fillCustomerNumber() {
        generatedCustomerNumber = RandomDataGenerator.generateCustomerNumber();
        waitHelper.waitAndSendKeys(customerNumberField, generatedCustomerNumber);
    }

    /**
     * Fill customer name with random data
     */
    public void fillCustomerName() {
        generatedCustomerName = RandomDataGenerator.generateCustomerName();
        waitHelper.waitAndSendKeys(customerNameField, generatedCustomerName);
    }

    /**
     * Fill father name with random data
     */
    public void fillFatherName() {
        String fatherName = RandomDataGenerator.generateFatherName();
        waitHelper.waitAndSendKeys(fatherNameField, fatherName);
    }

    /**
     * Fill phone number with random data
     */
    public void fillPhoneNumber() {
        String phoneNo = RandomDataGenerator.generatePhoneNumber();
        waitHelper.waitAndSendKeys(phoneNoField, phoneNo);
    }

    /**
     * Fill email with random data
     */
    public void fillEmail() {
        String email = RandomDataGenerator.generateEmail();
        waitHelper.waitAndSendKeys(emailField, email);
    }

    /**
     * Fill mobile number with random data
     */
    public void fillMobileNumber() {
        String mobileNo = RandomDataGenerator.generateMobileNumber();
        waitHelper.waitAndSendKeys(mobileNoField, mobileNo);
    }

    /**
     * Fill Aadhaar number with random data
     */
    public void fillAadhaarNumber() {
        String aadhaarNo = RandomDataGenerator.generateAadhaarNumber();
        waitHelper.waitAndSendKeys(aadhaarNoField, aadhaarNo);
    }

    /**
     * Fill address with random data
     */
    public void fillAddress() {
        String address = RandomDataGenerator.generateAddress();
        waitHelper.waitAndSendKeys(addressField, address);
    }

    /**
     * Fill second address line with random data
     */
    public void fillAddress2() {
        String address2 = RandomDataGenerator.generateSecondaryAddress();
        waitHelper.waitAndSendKeys(address2Field, address2);
    }

    /**
     * Fill remarks with random data
     */
    public void fillRemarks() {
        String remarks = RandomDataGenerator.generateRemarks();
        waitHelper.waitAndSendKeys(remarksField, remarks);
    }

    /**
     * Select random option from Customer Type dropdown
     */
    public void selectCustomerType() {
        // Wait for element and scroll into view
        WebElement dropdown = waitHelper.waitForElementPresent(customerTypeDropdown);
        waitHelper.scrollIntoView(dropdown);
        waitHelper.hardWait(500);
        
        // Click dropdown to load options
        waitHelper.waitAndClick(customerTypeDropdown);
        waitHelper.hardWait(1500);
        
        // Select random option
        dropdown = waitHelper.waitForElementClickable(customerTypeDropdown);
        selectRandomDropdownOption(dropdown);
    }

    /**
     * Select random option from Industry dropdown
     */
    public void selectIndustry() {
        // Wait for element and scroll into view
        WebElement dropdown = waitHelper.waitForElementPresent(industryDropdown);
        waitHelper.scrollIntoView(dropdown);
        waitHelper.hardWait(500);
        
        // Click dropdown to load options
        waitHelper.waitAndClick(industryDropdown);
        waitHelper.hardWait(1500);
        
        // Select random option
        dropdown = waitHelper.waitForElementClickable(industryDropdown);
        selectRandomDropdownOption(dropdown);
    }

    /**
     * Select random option from Segment dropdown
     */
    public void selectSegment() {
        // Wait for element and scroll into view
        WebElement dropdown = waitHelper.waitForElementPresent(segmentDropdown);
        waitHelper.scrollIntoView(dropdown);
        waitHelper.hardWait(500);
        
        // Click dropdown to load options
        waitHelper.waitAndClick(segmentDropdown);
        waitHelper.hardWait(1500);
        
        // Select random option
        dropdown = waitHelper.waitForElementClickable(segmentDropdown);
        selectRandomDropdownOption(dropdown);
    }

    /**
     * Select random option from Business Unit dropdown
     */
    public void selectBusinessUnit() {
        // Wait for element and scroll into view
        WebElement dropdown = waitHelper.waitForElementPresent(businessUnitDropdown);
        waitHelper.scrollIntoView(dropdown);
        waitHelper.hardWait(500);
        
        // Click dropdown to load options
        waitHelper.waitAndClick(businessUnitDropdown);
        waitHelper.hardWait(1500);
        
        // Select random option
        dropdown = waitHelper.waitForElementClickable(businessUnitDropdown);
        selectRandomDropdownOption(dropdown);
    }

    /**
     * Select random option from Zone dropdown
     */
    public void selectZone() {
        // Wait for element and scroll into view
        WebElement dropdown = waitHelper.waitForElementPresent(zoneDropdown);
        waitHelper.scrollIntoView(dropdown);
        waitHelper.hardWait(500);
        
        // Click dropdown to load options
        waitHelper.waitAndClick(zoneDropdown);
        waitHelper.hardWait(1500);
        
        // Select random option
        dropdown = waitHelper.waitForElementClickable(zoneDropdown);
        selectRandomDropdownOption(dropdown);
    }

    /**
     * Select random option from State dropdown
     */
    public void selectState() {
        // Wait for element and scroll into view
        WebElement dropdown = waitHelper.waitForElementPresent(stateDropdown);
        waitHelper.scrollIntoView(dropdown);
        waitHelper.hardWait(500);
        
        // Click dropdown to load options
        waitHelper.waitAndClick(stateDropdown);
        waitHelper.hardWait(1500);
        
        // Select random option
        dropdown = waitHelper.waitForElementClickable(stateDropdown);
        selectRandomDropdownOption(dropdown);
    }

    /**
     * Select random option from Location dropdown
     */
    public void selectLocation() {
        // Wait for element and scroll into view
        WebElement dropdown = waitHelper.waitForElementPresent(locationDropdown);
        waitHelper.scrollIntoView(dropdown);
        waitHelper.hardWait(500);
        
        // Click dropdown to load options
        waitHelper.waitAndClick(locationDropdown);
        waitHelper.hardWait(1500);
        
        // Select random option
        dropdown = waitHelper.waitForElementClickable(locationDropdown);
        selectRandomDropdownOption(dropdown);
    }

    /**
     * Select random option from Address Type dropdown
     */
    public void selectAddressType() {
        // Wait for element and scroll into view
        WebElement dropdown = waitHelper.waitForElementPresent(addressTypeDropdown);
        waitHelper.scrollIntoView(dropdown);
        waitHelper.hardWait(800);
        
        // Try JavaScript click first for better reliability
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", dropdown);
        waitHelper.hardWait(2000);
        
        // Select random option
        dropdown = waitHelper.waitForElementClickable(addressTypeDropdown);
        selectRandomDropdownOption(dropdown);
    }

    /**
     * Helper method to select random option from dropdown
     * @param dropdown WebElement of dropdown
     */
    private void selectRandomDropdownOption(WebElement dropdown) {
        Select select = new Select(dropdown);
        int optionsCount = select.getOptions().size();
        if (optionsCount > 1) { // Exclude first option if it's placeholder
            int randomIndex = RandomDataGenerator.generateRandomDropdownIndex(optionsCount - 1);
            select.selectByIndex(randomIndex);
        }
    }

    /**
     * Click Next button on Basic Details section
     */
    public void clickNextBasicDetails() {
        waitHelper.waitAndClick(nextButtonBasicDetails);
    }

    /**
     * Click Next button on NPA section
     */
    public void clickNextNPA() {
        waitHelper.waitAndClick(nextButtonNPA);
    }

    /**
     * Click Next button on Collapse Three section
     */
    public void clickNextCollapseThree() {
        waitHelper.waitAndClick(nextButtonCollapseThree);
    }

    /**
     * Click Next button on Collapse Four section
     */
    public void clickNextCollapseFour() {
        waitHelper.waitAndClick(nextButtonCollapseFour);
    }

    /**
     * Click Save button
     */
    public void clickSave() {
        waitHelper.waitAndClick(saveButton);
    }

    /**
     * Fill all basic details with random data
     */
    public void fillBasicDetails() {
        fillCustomerNumber();
        fillCustomerName();
        fillFatherName();
        selectCustomerType();
        selectIndustry();
        selectSegment();
    }

    /**
     * Fill all contact details with random data
     */
    public void fillContactDetails() {
        fillPhoneNumber();
        fillEmail();
        selectBusinessUnit();
        fillMobileNumber();
        fillAadhaarNumber();
    }

    /**
     * Fill all location details with random data
     */
    public void fillLocationDetails() {
        selectZone();
        selectState();
        selectLocation();
    }

    /**
     * Fill all address details with random data
     */
    public void fillAddressDetails() {
        selectAddressType();
        fillAddress();
        fillAddress2();
    }

    /**
     * Verify Add Customer form is displayed
     * @return true if form is displayed
     */
    public boolean isAddCustomerFormDisplayed() {
        return waitHelper.waitForElementVisible(customerNumberField).isDisplayed();
    }

    /**
     * Get generated customer number for verification
     * @return Customer number
     */
    public String getGeneratedCustomerNumber() {
        return generatedCustomerNumber;
    }

    /**
     * Get generated customer name for verification
     * @return Customer name
     */
    public String getGeneratedCustomerName() {
        return generatedCustomerName;
    }
}
