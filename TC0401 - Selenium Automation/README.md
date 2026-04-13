# TC0401 - Selenium Test Automation

Automated test case **TC0401: Delete a single product from cart** for the Advantage Online Shopping website.

## ✅ Test Case Status
- **TC0401**: Delete a single product from cart - **AUTOMATED & PASSING**

## Quick Start

### Prerequisites
1. **Java JDK 11 or higher** - [Download here](https://adoptium.net/)
2. **Maven 3.6+** - [Download here](https://maven.apache.org/download.cgi)
3. **Google Chrome browser** (latest version)
4. **Git** (for cloning)

### Setup & Run (3 Steps)

```bash
# 1. Clone the repository
git clone <your-repo-url>
cd "TC0405 - Selenium Automation"

# 2. Install dependencies (Maven will auto-download everything)
mvn clean install

# 3. Run the test
mvn test
```

That's it! The test will:
- ✅ Auto-download ChromeDriver (via WebDriverManager)
- ✅ Open Chrome browser
- ✅ Execute the test automation
- ✅ Capture screenshots
- ✅ Generate HTML report in `test-output/`

## Project Structure

```
selenium-automation/
├── src/
│   ├── main/java/com/advantageonline/
│   │   ├── pages/              # Page Object Model classes
│   │   │   ├── BasePage.java
│   │   │   ├── HomePage.java
│   │   │   ├── CategoryPage.java
│   │   │   ├── ProductDetailsPage.java
│   │   │   └── ShoppingCartPage.java
│   │   └── utils/              # Utility classes
│   │       ├── DriverFactory.java
│   │       ├── ScreenshotUtil.java
│   │       ├── ExtentManager.java
│   │       └── ExtentTestManager.java
│   └── test/java/com/advantageonline/
│       ├── tests/              # Test classes
│       │   ├── BaseTest.java
│       │   └── US04_DeleteProductFromCartTest.java
│       ├── listeners/          # TestNG listeners
│       │   └── TestListener.java
│       └── resources/
│           └── testng.xml      # TestNG configuration
├── screenshots/                # Auto-generated screenshots
├── test-output/               # Test reports
├── pom.xml                    # Maven dependencies
└── README.md
```

## Test Cases Implemented

### TC0401: Delete a single product from cart
- **Precondition**: Cart contains one single product
- **Steps**:
  1. Navigate to cart (https://advantageonlineshopping.com/#/shoppingCart)
  2. Click on "REMOVE"
- **Expected Result**: Cart displays "Your shopping cart is empty"
- **Status**: ✅ AUTOMATED

## Technologies Used

- **Java 11**
- **Selenium WebDriver 4.18.1**
- **TestNG 7.9.0**
- **Extent Reports 5.1.1**
- **WebDriverManager 5.6.3** (automatic driver management)
- **Maven** (build tool)

## Prerequisites

1. **Java JDK 11 or higher** installed
2. **Maven 3.6+** installed
3. **Google Chrome browser** (or Firefox/Edge)
4. Internet connection to download dependencies

## Setup Instructions

1. **Clone or extract the project**

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify setup**
   ```bash
   mvn test -Dtest=US04_DeleteProductFromCartTest#testAddProductToCart
   ```

## Running Tests

### Run all tests via Maven
```bash
mvn clean test
```

### Run specific test class
```bash
mvn test -Dtest=US04_DeleteProductFromCartTest
```

### Run specific test method
```bash
mvn test -Dtest=US04_DeleteProductFromCartTest#testTC0401_DeleteSingleProductFromCart
```

### Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run with different browser
Edit `src/test/resources/testng.xml` and change the browser parameter:
```xml
<parameter name="browser" value="firefox"/>  <!-- or edge -->
```

## Features

### ✅ Automatic Screenshot Capture
- Screenshots captured on **test success** and **test failure**
- Stored in `screenshots/` directory with timestamps
- Automatically attached to Extent Reports

### ✅ Detailed HTML Reports
- Generated using Extent Reports
- Located in `test-output/` directory
- Includes:
  - Test execution summary
  - Pass/Fail status with screenshots
  - System information
  - Execution timestamps
  - Exception details for failures

### ✅ Page Object Model (POM)
- Maintainable and reusable page classes
- Separation of test logic and page interactions
- Explicit waits for robust element handling

### ✅ Best Practices
- No `Thread.sleep()` (uses WebDriverWait)
- Proper exception handling
- Robust locator strategies (CSS > XPath)
- Automatic WebDriver management via WebDriverManager

## Test Execution Flow

### TC0401 Execution Steps:

1. **Precondition Setup** (Automated):
   - Navigate to home page
   - Click on Speakers category
   - Select "Bose Soundlink Bluetooth Speaker III"
   - Click "Add to Cart"
   - Verify cart count = 1

2. **Test Steps** (Automated):
   - Navigate to shopping cart
   - Verify cart contains one product
   - Click "REMOVE" button
   - Verify cart is empty
   - Verify message: "Your shopping cart is empty"
   - Verify cart count = 0

3. **Screenshot Capture**:
   - Screenshot taken after each major step
   - Final screenshot on test pass/fail

4. **Report Generation**:
   - Extent Report with detailed logs
   - Screenshots embedded in report

## Viewing Test Reports

After test execution:

1. **Extent Report**: 
   - Open `test-output/TestReport_[timestamp].html` in a browser
   - View detailed test execution with screenshots

2. **TestNG Report**: 
   - Open `test-output/index.html` in a browser

3. **Console Output**: 
   - View real-time test execution in terminal
   - Shows pass/fail status for each test

## Screenshots Location

All screenshots are saved in: `screenshots/`

Naming convention:
- **Pass**: `TestName_PASS_yyyyMMdd_HHmmss.png`
- **Fail**: `TestName_FAIL_yyyyMMdd_HHmmss.png`

## Troubleshooting

### ✅ Verify Your Setup
```bash
# Check Java version (must be 11+)
java -version

# Check Maven version (must be 3.6+)
mvn -version

# Check Chrome is installed
# Windows: Open Chrome and go to chrome://version/
```

### Common Issues

**❌ "JAVA_HOME not set"**
```bash
# Windows: Add to System Environment Variables
JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.11.9-hotspot
Path=%JAVA_HOME%\bin
```

**❌ "Maven command not found"**
- Download Maven and add `bin` folder to System PATH
- Restart terminal after adding to PATH

**❌ "ChromeDriver version mismatch"**
- WebDriverManager handles this automatically
- If issues persist, update Chrome browser to latest version

**❌ Test fails**
1. Check screenshot in `screenshots/` folder
2. Review Extent Report in `test-output/TestReport_[timestamp].html`
3. Ensure internet connection is stable
4. Verify website https://advantageonlineshopping.com is accessible

### Browser driver issues
- ✅ No manual driver download needed
- ✅ WebDriverManager auto-downloads correct ChromeDriver version
- ✅ Supports Chrome, Firefox, Edge

### Maven dependency issues
```bash
mvn dependency:purge-local-repository
mvn clean install -U
```

## CI/CD Integration

### GitHub Actions Example
```yaml
name: Run Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn clean test
```

## Viewing Test Results

After running tests:

### 1. **Extent HTML Report** (Recommended)
```bash
# Open in browser
start test-output/TestReport_[timestamp].html  # Windows
open test-output/TestReport_[timestamp].html   # Mac
xdg-open test-output/TestReport_[timestamp].html  # Linux
```

### 2. **Screenshots**
- Location: `screenshots/`
- **PASS**: `TestName_PASS_[timestamp].png`
- **FAIL**: `TestName_FAIL_[timestamp].png`

### 3. **Console Output**
- Real-time test execution status
- Pass/Fail summary at the end

## Support

For issues or questions:
1. Check the troubleshooting section above
2. Review the Extent Report for detailed error logs
3. Check screenshots for visual debugging

---

**Framework Version**: 1.0  
**Last Updated**: April 2026  
**Test Status**: ✅ All tests passing
