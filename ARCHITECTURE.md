# Test Automation Framework Architecture

## Framework Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         TEST RUNNER                             │
│                      (TestRunner.java)                          │
│                    JUnit 5 + Cucumber                           │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                      CUCUMBER FEATURE                           │
│                   (AddCustomer.feature)                         │
│              Gherkin Syntax - BDD Scenarios                     │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                    STEP DEFINITIONS                             │
│            (AddCustomerStepDefinitions.java)                    │
│           Maps Gherkin steps to Java methods                    │
└─────┬────────────────────────────────────────────────┬──────────┘
      │                                                 │
      │                                                 │
┌─────▼─────────────────────┐               ┌──────────▼──────────┐
│        HOOKS              │               │   PAGE OBJECTS      │
│     (Hooks.java)          │               │                     │
│                           │               │  LoginPage.java     │
│  @Before: Setup Browser   │               │  OtpPage.java       │
│  @After: Teardown         │               │  CustomerNav...     │
│  Screenshot on Failure    │               │  AddCustomer...     │
└─────┬─────────────────────┘               └──────────┬──────────┘
      │                                                 │
      │                                                 │
      ▼                                                 ▼
┌─────────────────────┐                   ┌────────────────────────┐
│  WebDriverManager   │                   │   UTILITY CLASSES      │
│   Browser Setup     │                   │                        │
│  Chrome/Firefox/Edge│                   │  WaitHelper.java       │
└──────────┬──────────┘                   │  RandomDataGenerator   │
           │                               │  ConfigReader.java     │
           ▼                               └────────────┬───────────┘
    ┌──────────────┐                                   │
    │   WebDriver  │◄──────────────────────────────────┘
    │   Instance   │
    └──────┬───────┘
           │
           ▼
    ┌──────────────────────┐
    │   WEB APPLICATION    │
    │  Browser Automation  │
    └──────────────────────┘
```

## Data Flow Diagram

```
┌──────────────┐
│ Test Runner  │
│   Starts     │
└──────┬───────┘
       │
       ▼
┌──────────────────┐
│  Initialize      │
│  WebDriver       │◄─────────── config.properties
│  (Hooks @Before) │
└──────┬───────────┘
       │
       ▼
┌───────────────────┐
│  Read Feature     │
│  File             │
└──────┬────────────┘
       │
       ▼
┌─────────────────────┐
│  Execute Step       │
│  Definition         │
└──────┬──────────────┘
       │
       ▼
┌──────────────────────┐           ┌───────────────────┐
│  Call Page Object    │──────────►│ Generate Random   │
│  Method              │           │ Test Data         │
└──────┬───────────────┘           └───────────────────┘
       │
       ▼
┌──────────────────────┐           ┌───────────────────┐
│  Wait for Element    │──────────►│  WaitHelper       │
│  (Explicit Wait)     │           │  Methods          │
└──────┬───────────────┘           └───────────────────┘
       │
       ▼
┌──────────────────────┐
│  Interact with       │
│  Web Element         │
│  (Selenium)          │
└──────┬───────────────┘
       │
       ▼
┌──────────────────────┐
│  Verify Result       │
│  (Assertions)        │
└──────┬───────────────┘
       │
       ▼
┌──────────────────────┐           ┌───────────────────┐
│  Test Completes      │           │  If Failed:       │
│  (Hooks @After)      │──────────►│  Take Screenshot  │
└──────┬───────────────┘           └───────────────────┘
       │
       ▼
┌──────────────────────┐
│  Close Browser       │
│  Generate Reports    │
└──────────────────────┘
```

## Test Execution Flow

```
START
  │
  ├─► [1] TestRunner.java executes
  │
  ├─► [2] Hooks @Before
  │     ├─ Initialize WebDriver
  │     ├─ Setup Browser Options
  │     ├─ Maximize Window
  │     └─ Set Timeouts
  │
  ├─► [3] Load AddCustomer.feature
  │     └─ Parse Gherkin syntax
  │
  ├─► [4] Execute Background
  │     └─ Open Application URL
  │
  ├─► [5] Execute Scenario Steps
  │     │
  │     ├─► Login Steps
  │     │     ├─ Enter email
  │     │     ├─ Enter password
  │     │     └─ Click Sign In
  │     │
  │     ├─► OTP Steps
  │     │     ├─ Enter OTP
  │     │     └─ Click Submit
  │     │
  │     ├─► Navigation Steps
  │     │     ├─ Click Borrowers
  │     │     ├─ Click Customer
  │     │     └─ Click Add New
  │     │
  │     └─► Form Filling Steps
  │           ├─ Fill Basic Details (Random Data)
  │           ├─ Fill Contact Details (Random Data)
  │           ├─ Fill Location Details (Random Data)
  │           ├─ Fill Address Details (Random Data)
  │           ├─ Click Multiple Next Buttons
  │           ├─ Enter Remarks
  │           └─ Click Save
  │
  ├─► [6] Verify Success
  │     └─ Check confirmation/success message
  │
  ├─► [7] Hooks @After
  │     ├─ Check if test failed
  │     │   └─ Yes? → Capture Screenshot
  │     ├─ Close Browser
  │     └─ Clean up resources
  │
  └─► [8] Generate Reports
        ├─ HTML Report
        ├─ JSON Report
        └─ XML Report
  │
END
```

## Component Interaction

```
┌────────────────────────────────────────────────────────────┐
│                     TEST LAYER                             │
│  ┌──────────────┐  ┌──────────────┐  ┌─────────────┐     │
│  │ Feature File │  │ Step Defs    │  │   Hooks     │     │
│  │   (.feature) │→ │ (.java)      │  │  (@Before)  │     │
│  └──────────────┘  └──────┬───────┘  └──────┬──────┘     │
└────────────────────────────┼──────────────────┼───────────┘
                             │                  │
┌────────────────────────────┼──────────────────┼───────────┐
│                     PAGE LAYER                 │           │
│  ┌──────────────┐  ┌──────▼───────┐  ┌────────▼─────┐    │
│  │  LoginPage   │  │   OtpPage    │  │CustomerNav   │    │
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘    │
│  ┌──────▼────────────────────────────────────▼───────┐    │
│  │            AddCustomerPage                        │    │
│  └───────────────────────┬───────────────────────────┘    │
└──────────────────────────┼────────────────────────────────┘
                           │
┌──────────────────────────┼────────────────────────────────┐
│                   UTILITY LAYER              │             │
│  ┌──────────────┐  ┌────▼───────┐  ┌────────────────┐    │
│  │ WaitHelper   │  │RandomData  │  │ ConfigReader   │    │
│  │ (Waits)      │  │Generator   │  │ (Properties)   │    │
│  └──────┬───────┘  └────┬───────┘  └────────┬───────┘    │
└─────────┼───────────────┼──────────────────────┼──────────┘
          │               │                      │
┌─────────┼───────────────┼──────────────────────┼──────────┐
│         │          SELENIUM LAYER              │           │
│  ┌──────▼──────────────────────────────────────▼──────┐   │
│  │              WebDriver Instance                     │   │
│  │         (Chrome/Firefox/Edge Driver)                │   │
│  └─────────────────────┬───────────────────────────────┘   │
└────────────────────────┼─────────────────────────────────┘
                         │
                         ▼
              ┌──────────────────┐
              │  WEB APPLICATION │
              │    (Browser)     │
              └──────────────────┘
```

## Page Object Pattern

```
Page Object Class Structure:
┌─────────────────────────────┐
│      LoginPage.java         │
├─────────────────────────────┤
│  Fields:                    │
│  - driver                   │
│  - waitHelper               │
│  - locators (By objects)    │
├─────────────────────────────┤
│  Constructor:               │
│  - Initialize driver        │
│  - Initialize waitHelper    │
├─────────────────────────────┤
│  Methods:                   │
│  - enterEmail()             │
│  - enterPassword()          │
│  - clickSignIn()            │
│  - login() [composite]      │
│  - isLoginPageDisplayed()   │
└─────────────────────────────┘
```

## Wait Strategy

```
Element Interaction Flow:

  Find Element
      │
      ▼
  Wait for Condition ◄────────┐
      │                       │
      ├─ Visible?             │
      ├─ Clickable?           │
      ├─ Present?             │
      │                       │
      ▼                       │
  Condition Met?              │
      │                       │
  ┌───┴───┐                   │
  │       │                   │
 YES     NO ─── Retry ────────┘
  │           (Polling)
  │           (Max 20s)
  │
  ▼
Interact with Element
  │
  ├─ Click
  ├─ SendKeys
  ├─ GetText
  └─ etc.
```

## Random Data Generation Flow

```
  Test Needs Data
       │
       ▼
  RandomDataGenerator
       │
       ├─► Customer Number
       │     └─ "CUST" + 8 random alphanumeric
       │
       ├─► Customer Name
       │     └─ JavaFaker: fullName()
       │
       ├─► Mobile Number
       │     └─ (7|8|9) + 9 random digits
       │
       ├─► Email
       │     └─ JavaFaker: emailAddress()
       │
       ├─► Aadhaar
       │     └─ 16 random digits
       │
       ├─► Address
       │     └─ JavaFaker: streetAddress()
       │
       └─► Dropdown Index
             └─ Random between 1 and max options
```

## Report Generation Flow

```
  Test Execution
       │
       ├─► Cucumber Engine
       │     │
       │     ├─ Captures step results
       │     ├─ Captures screenshots (on failure)
       │     └─ Builds report data
       │
       ▼
  Report Plugins
       │
       ├─► HTML Plugin
       │     └─► cucumber.html
       │           ├─ Embedded screenshots
       │           ├─ Step-by-step details
       │           └─ Execution timeline
       │
       ├─► JSON Plugin
       │     └─► cucumber.json
       │           └─ Machine-readable format
       │
       └─► XML Plugin
             └─► cucumber.xml
                   └─ JUnit compatible format
```

## Technology Stack

```
┌──────────────────────────────────────┐
│         Programming Language         │
│             Java 17+                 │
└──────────────────┬───────────────────┘
                   │
┌──────────────────▼───────────────────┐
│         Build Tool                   │
│           Maven 3.6+                 │
└──────────────────┬───────────────────┘
                   │
┌──────────────────▼───────────────────┐
│         Test Framework               │
│      JUnit 5 + Cucumber BDD          │
└──────────────────┬───────────────────┘
                   │
┌──────────────────▼───────────────────┐
│      Automation Framework            │
│        Selenium 4.25.0               │
└──────────────────┬───────────────────┘
                   │
┌──────────────────▼───────────────────┐
│       Driver Management              │
│     WebDriverManager 5.9.2           │
└──────────────────┬───────────────────┘
                   │
┌──────────────────▼───────────────────┐
│       Test Data Generation           │
│  JavaFaker 1.0.2 + Commons Lang3     │
└──────────────────────────────────────┘
```

## File Dependencies

```
TestRunner.java
    │
    ├─► depends on ──► Cucumber annotations
    ├─► references ──► features/ directory
    └─► references ──► stepdefinitions/ package

AddCustomerStepDefinitions.java
    │
    ├─► depends on ──► Page Objects
    ├─► depends on ──► Hooks (for WebDriver)
    └─► uses ───────► Cucumber annotations

Hooks.java
    │
    ├─► depends on ──► WebDriverManager
    ├─► depends on ──► Selenium WebDriver
    └─► creates ────► WebDriver instance

Page Objects
    │
    ├─► depend on ──► WaitHelper
    ├─► depend on ──► RandomDataGenerator
    └─► use ────────► Selenium WebDriver

WaitHelper.java
    │
    └─► depends on ──► Selenium WebDriver

RandomDataGenerator.java
    │
    ├─► depends on ──► JavaFaker
    └─► depends on ──► Apache Commons Lang3

ConfigReader.java
    │
    └─► reads ──────► config.properties
```

---

This architecture follows:
- ✅ **Separation of Concerns**: Each layer has specific responsibility
- ✅ **Modularity**: Components are independent and reusable
- ✅ **Maintainability**: Easy to update and extend
- ✅ **Scalability**: Can handle more tests/pages easily
- ✅ **Best Practices**: Industry-standard patterns
