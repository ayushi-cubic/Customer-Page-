Feature: Add New Customer
  As a system administrator
  I want to add a new customer to the system
  So that customer information is properly recorded

  Background:
    Given I open the application URL "https://qalmsbu.cubictree.com/"

  Scenario: Successfully add a new customer with all required details
    When I enter email "ayushi" in the login page
    And I enter password "Legal@123" in the login page
    And I click on Sign In button
    Then I should be redirected to OTP page
    When I enter OTP "123456"
    And I click on Submit button on OTP page
    Then I should be logged in successfully
    When I navigate to Borrowers menu
    And I navigate to Customer submenu
    And I click on Add New Customer button
    Then I should see the Add Customer form
    When I fill in the customer basic details with random data
    And I fill in the customer contact details with random data
    And I fill in the customer location details with random data
    And I fill in the customer address details with random data
    And I click on Next button on basic details section
    And I click on Next button on NPA section
    And I click on Next button on collapse three section
    And I click on Next button on collapse four section
    And I enter remarks with random text
    And I click on Save button
    Then the customer should be created successfully
