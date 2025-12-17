# Customer Management Test Automation Framework

A comprehensive Selenium test automation framework using Cucumber BDD, JUnit 5, and Page Object Model pattern.

## Framework Features

- **Selenium 4**: Latest Selenium WebDriver
- **JUnit 5**: Modern testing framework
- **Cucumber BDD**: Behavior-driven development with Gherkin syntax
- **Page Object Model**: Clean separation of concerns
- **WebDriverManager**: Automatic driver management
- **Explicit Waits**: Robust synchronization
- **Random Test Data**: JavaFaker and Apache Commons for realistic data
- **Screenshot on Failure**: Automatic screenshot capture
- **Multiple Browser Support**: Chrome, Firefox, Edge

## Project Structure

```
demo/
├── src/
│   ├── main/java/com/example/
│   └── test/
│       ├── java/com/example/
│       │   ├── pages/                      # Page Object classes
│       │   │   ├── LoginPage.java
│       │   │   ├── OtpPage.java
│       │   │   ├── CustomerNavigationPage.java
│       │   │   └── AddCustomerPage.java
│       │   ├── stepdefinitions/            # Step definitions and hooks
│       │   │   ├── AddCustomerStepDefinitions.java
│       │   │   └── Hooks.java
│       │   ├── runner/                     # Test runner
│       │   │   └── TestRunner.java
│       │   └── utils/                      # Utility classes
│       │       ├── RandomDataGenerator.java
│       │       └── WaitHelper.java
│       └── resources/
│           └── features/                   # Cucumber feature files
│               └── AddCustomer.feature
├── test-output/                           # Test reports and screenshots
│   ├── cucumber-reports/
│   └── screenshots/
└── pom.xml
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser installed

## Dependencies

- Selenium WebDriver 4.25.0
- WebDriverManager 5.9.2
- Cucumber 7.18.1
- JUnit 5.10.3
- JavaFaker 1.0.2
- Apache Commons Lang3 3.14.0

## Installation

1. Clone the repository
2. Navigate to project directory
3. Install dependencies:
   ```bash
   mvn clean install -DskipTests
   ```

## Running Tests

### Run all tests (Chrome - default):
```bash
mvn clean test
```

### Run with specific browser:
```bash
# Firefox
mvn clean test -Dbrowser=firefox

# Edge
mvn clean test -Dbrowser=edge

# Chrome (explicit)
mvn clean test -Dbrowser=chrome
```

### Run specific feature:
```bash
mvn clean test -Dcucumber.features="src/test/resources/features/AddCustomer.feature"
```

### Run with specific tag:
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

## Test Scenario

The framework tests the complete customer creation workflow:

1. **Login**: Navigate to application and authenticate
2. **OTP Verification**: Enter OTP code
3. **Navigation**: Navigate through menu hierarchy
4. **Form Filling**: Fill customer form with random data
5. **Submission**: Submit and verify customer creation

## Page Objects

### LoginPage
- Handles login credentials
- Methods: `enterEmail()`, `enterPassword()`, `clickSignIn()`

### OtpPage
- Manages OTP verification
- Methods: `enterOtp()`, `clickSubmit()`

### CustomerNavigationPage
- Handles menu navigation
- Methods: `clickBorrowersMenu()`, `clickCustomerSubmenu()`, `clickAddNewCustomerButton()`

### AddCustomerPage
- Manages customer form interactions
- Methods for each field and section
- Random data generation integrated

## Utilities

### RandomDataGenerator
Generates realistic test data:
- Customer numbers
- Names and contact information
- Phone/mobile numbers
- Email addresses
- Aadhaar numbers
- Addresses and remarks

### WaitHelper
Provides robust synchronization:
- Explicit waits for various conditions
- Element visibility/clickability
- Custom conditions
- JavaScript operations
- Page load detection

## Reports

After test execution, reports are generated in:

1. **HTML Report**: `test-output/cucumber-reports/cucumber.html`
2. **JSON Report**: `test-output/cucumber-reports/cucumber.json`
3. **XML Report**: `test-output/cucumber-reports/cucumber.xml`
4. **Screenshots**: `test-output/screenshots/` (on failure)

## Configuration

### Browser Selection
Set via system property:
```bash
-Dbrowser=chrome|firefox|edge
```

### Timeouts
Configured in `WaitHelper.java`:
- Default explicit wait: 20 seconds
- Implicit wait: 10 seconds (Hooks)
- Page load timeout: 30 seconds (Hooks)

### Headless Mode
Uncomment in `Hooks.java`:
```java
// chromeOptions.addArguments("--headless");
```

## Best Practices Implemented

1. **Page Object Model**: Clean separation of page logic
2. **Explicit Waits**: No Thread.sleep() in main code
3. **DRY Principle**: Reusable utility methods
4. **Random Test Data**: Avoid data conflicts
5. **Screenshot on Failure**: Easy debugging
6. **Proper Logging**: Console output for key actions
7. **Exception Handling**: Graceful failure management
8. **Browser Agnostic**: Multi-browser support
9. **BDD Approach**: Business-readable scenarios
10. **Clean Architecture**: Well-organized package structure

## Troubleshooting

### WebDriver Issues
- Ensure browser is installed
- WebDriverManager handles driver download automatically
- Check firewall/proxy settings if download fails

### Test Failures
- Check screenshots in `test-output/screenshots/`
- Review console output for error messages
- Verify application is accessible

### Timeout Issues
- Increase wait times in `WaitHelper.java`
- Check network connectivity
- Verify element locators are correct

## Extending the Framework

### Adding New Tests
1. Create feature file in `src/test/resources/features/`
2. Create corresponding page objects in `pages/`
3. Implement step definitions in `stepdefinitions/`
4. Run tests

### Adding New Pages
1. Create new class in `pages/` package
2. Extend with WebDriver and WaitHelper
3. Define locators as private final variables
4. Implement action methods

### Custom Wait Conditions
Add to `WaitHelper.java`:
```java
public boolean customWait(By locator) {
    return wait.until(/* your condition */);
}
```

## License

This project is created for testing purposes.

## Contact

For questions or issues, please refer to project documentation.
