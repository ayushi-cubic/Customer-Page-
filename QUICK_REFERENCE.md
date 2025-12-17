# Quick Reference Guide

## 🚀 Quick Start

### Option 1: Use Batch Script (Easiest)
```cmd
run-tests.bat
```
Follow the interactive menu.

### Option 2: Direct Maven Commands

#### Install & Run
```bash
mvn clean install -DskipTests
mvn clean test
```

## 📋 All Maven Commands

```bash
# Clean and install dependencies (first time)
mvn clean install -DskipTests

# Run all tests (Chrome - default)
mvn clean test

# Run with specific browser
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge

# Run specific feature file
mvn clean test -Dcucumber.features="src/test/resources/features/AddCustomer.feature"

# Run with tags
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="not @ignore"

# Run in parallel (if configured)
mvn clean test -Dparallel=true

# Clean everything
mvn clean

# Validate project
mvn validate

# View dependency tree
mvn dependency:tree
```

## 📁 Project Structure Quick View

```
demo/
├── src/test/
│   ├── java/com/example/
│   │   ├── pages/              # Page Objects
│   │   ├── stepdefinitions/    # Steps & Hooks
│   │   ├── runner/             # Test Runner
│   │   └── utils/              # Utilities
│   └── resources/
│       ├── features/           # .feature files
│       └── config.properties   # Configuration
├── test-output/
│   ├── cucumber-reports/       # HTML, JSON, XML reports
│   └── screenshots/            # Failure screenshots
├── pom.xml
├── README.md
└── run-tests.bat
```

## 📊 Reports Location

After test execution:
```
test-output/cucumber-reports/cucumber.html   ← Open this!
test-output/cucumber-reports/cucumber.json
test-output/cucumber-reports/cucumber.xml
test-output/screenshots/                     ← Check on failures
```

## 🔧 Configuration

Edit `src/test/resources/config.properties`:
```properties
app.url=https://qalmsbu.cubictree.com/
login.email=ayushi
login.password=Legal@1234
otp.code=123456
browser=chrome
```

## 🌐 Browser Selection

### Via Maven
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Via config.properties
```properties
browser=chrome  # Change to firefox or edge
```

## 📸 Screenshots

Captured automatically on:
- ✅ Test failure
- 📍 Location: `test-output/screenshots/`
- 📷 Format: PNG with timestamp
- 📋 Also embedded in HTML report

## 🐛 Debugging

### Check Logs
```bash
# Console output shows each step
# Look for ERROR or FAILED messages
```

### View Screenshots
```
test-output/screenshots/ScenarioName_timestamp.png
```

### Increase Wait Time
In `src/test/java/com/example/utils/WaitHelper.java`:
```java
private static final int DEFAULT_TIMEOUT = 30; // Increase from 20
```

## 🎯 Test Execution Flow

```
1. TestRunner starts
   ↓
2. Hooks @Before: Initialize browser
   ↓
3. Load feature file
   ↓
4. Execute scenario steps
   ↓
5. Fill forms with random data
   ↓
6. Navigate through pages
   ↓
7. Submit and verify
   ↓
8. Hooks @After: Screenshot (if failed), close browser
   ↓
9. Generate reports
```

## 📦 Dependencies Check

```bash
# Verify all dependencies are resolved
mvn dependency:resolve

# Update dependencies
mvn versions:display-dependency-updates
```

## 🔍 Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| Maven not found | Install Maven, add to PATH |
| Java not found | Install JDK 17+, set JAVA_HOME |
| WebDriver error | WebDriverManager auto-downloads, check internet |
| Element not found | Increase wait time, verify locators |
| Browser not opening | Update browser to latest version |
| Test failing | Check screenshot in test-output/screenshots/ |

## ✅ Pre-requisites Checklist

- [ ] Java JDK 17+ installed
- [ ] Maven 3.6+ installed
- [ ] Browser installed (Chrome/Firefox/Edge)
- [ ] Internet connection (for driver download)
- [ ] Write permission in project folder

## 🎓 File Purposes

| File | Purpose |
|------|---------|
| `AddCustomer.feature` | BDD test scenario |
| `LoginPage.java` | Login page interactions |
| `OtpPage.java` | OTP verification |
| `CustomerNavigationPage.java` | Menu navigation |
| `AddCustomerPage.java` | Customer form handling |
| `AddCustomerStepDefinitions.java` | Step implementations |
| `Hooks.java` | Setup/teardown logic |
| `TestRunner.java` | Test execution config |
| `RandomDataGenerator.java` | Test data generation |
| `WaitHelper.java` | Synchronization utilities |
| `ConfigReader.java` | Config file reader |

## 🚦 Status Indicators

After running tests:
- ✅ **Green**: All tests passed
- ❌ **Red**: Test failed (check screenshot)
- ⚠️ **Yellow**: Warning/skipped

## 📈 Reports Explained

### HTML Report
- Human-readable
- Includes screenshots
- Shows step-by-step execution
- Open: `test-output/cucumber-reports/cucumber.html`

### JSON Report
- For CI/CD integration
- Machine-readable
- Location: `test-output/cucumber-reports/cucumber.json`

### XML Report
- JUnit compatible
- For Jenkins/CI tools
- Location: `test-output/cucumber-reports/cucumber.xml`

## 🎬 First Time Setup

```bash
# 1. Open terminal in project directory
cd "C:\Users\DELL\Documents\VS code testing\Custpage\demo"

# 2. Install dependencies
mvn clean install -DskipTests

# 3. Run tests
mvn clean test

# 4. View report
start test-output\cucumber-reports\cucumber.html
```

## 💡 Tips

1. **Always run `mvn clean test`** for fresh execution
2. **Check screenshots** when tests fail
3. **Use run-tests.bat** for easy execution
4. **Keep browsers updated** to avoid compatibility issues
5. **Increase timeouts** if application is slow
6. **Run single feature** during development
7. **Use tags** to organize tests

## 📞 Need Help?

1. Check `README.md` for detailed info
2. Review `IMPLEMENTATION_GUIDE.md` for technical details
3. Check console output for error messages
4. Review screenshots for visual debugging
5. Verify all pre-requisites are met

---

**Last Updated**: December 17, 2025
**Framework Status**: ✅ Production Ready
