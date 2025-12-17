package com.example.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.example.pages.AddCustomerPage;
import com.example.pages.CustomerNavigationPage;
import com.example.pages.LoginPage;
import com.example.pages.OtpPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions for Add Customer feature
 * Maps Cucumber steps to actual implementation
 */
public class AddCustomerStepDefinitions {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final OtpPage otpPage;
    private final CustomerNavigationPage navigationPage;
    private final AddCustomerPage addCustomerPage;

    /**
     * Constructor - gets driver from Hooks
     */
    public AddCustomerStepDefinitions() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.otpPage = new OtpPage(driver);
        this.navigationPage = new CustomerNavigationPage(driver);
        this.addCustomerPage = new AddCustomerPage(driver);
    }

    @Given("I open the application URL {string}")
    public void iOpenTheApplicationURL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("I enter email {string} in the login page")
    public void iEnterEmailInTheLoginPage(String email) {
        loginPage.enterEmail(email);
    }

    @And("I enter password {string} in the login page")
    public void iEnterPasswordInTheLoginPage(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click on Sign In button")
    public void iClickOnSignInButton() {
        loginPage.clickSignIn();
    }

    @Then("I should be redirected to OTP page")
    public void iShouldBeRedirectedToOTPPage() {
        assertTrue(otpPage.isOtpPageDisplayed(), "OTP page is not displayed");
    }

    @When("I enter OTP {string}")
    public void iEnterOTP(String otp) {
        otpPage.enterOtp(otp);
    }

    @And("I click on Submit button on OTP page")
    public void iClickOnSubmitButtonOnOTPPage() {
        otpPage.clickSubmit();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        assertTrue(navigationPage.isLoggedInSuccessfully(), "Login was not successful");
    }

    @When("I navigate to Borrowers menu")
    public void iNavigateToBorrowersMenu() {
        navigationPage.clickBorrowersMenu();
    }

    @And("I navigate to Customer submenu")
    public void iNavigateToCustomerSubmenu() {
        navigationPage.clickCustomerSubmenu();
    }

    @And("I click on Add New Customer button")
    public void iClickOnAddNewCustomerButton() {
        navigationPage.clickAddNewCustomerButton();
    }

    @Then("I should see the Add Customer form")
    public void iShouldSeeTheAddCustomerForm() {
        assertTrue(addCustomerPage.isAddCustomerFormDisplayed(), "Add Customer form is not displayed");
    }

    @When("I fill in the customer basic details with random data")
    public void iFillInTheCustomerBasicDetailsWithRandomData() {
        addCustomerPage.fillBasicDetails();
    }

    @And("I fill in the customer contact details with random data")
    public void iFillInTheCustomerContactDetailsWithRandomData() {
        addCustomerPage.fillContactDetails();
    }

    @And("I fill in the customer location details with random data")
    public void iFillInTheCustomerLocationDetailsWithRandomData() {
        addCustomerPage.fillLocationDetails();
    }

    @And("I fill in the customer address details with random data")
    public void iFillInTheCustomerAddressDetailsWithRandomData() {
        addCustomerPage.fillAddressDetails();
    }

    @And("I click on Next button on basic details section")
    public void iClickOnNextButtonOnBasicDetailsSection() {
        addCustomerPage.clickNextBasicDetails();
    }

    @And("I click on Next button on NPA section")
    public void iClickOnNextButtonOnNPASection() {
        addCustomerPage.clickNextNPA();
    }

    @And("I click on Next button on collapse three section")
    public void iClickOnNextButtonOnCollapseThreeSection() {
        addCustomerPage.clickNextCollapseThree();
    }

    @And("I click on Next button on collapse four section")
    public void iClickOnNextButtonOnCollapseFourSection() {
        addCustomerPage.clickNextCollapseFour();
    }

    @And("I enter remarks with random text")
    public void iEnterRemarksWithRandomText() {
        addCustomerPage.fillRemarks();
    }

    @And("I click on Save button")
    public void iClickOnSaveButton() {
        addCustomerPage.clickSave();
    }

    @Then("the customer should be created successfully")
    public void theCustomerShouldBeCreatedSuccessfully() {
        // Wait for success message or confirmation
        // This can be enhanced based on actual success indicator on the page
        System.out.println("Customer created: " + addCustomerPage.getGeneratedCustomerNumber());
        System.out.println("Customer name: " + addCustomerPage.getGeneratedCustomerName());
    }
}
