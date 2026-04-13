# Quick Start Guide for Team Members

## 🚀 Running the Tests (3 Simple Steps)

### Prerequisites Check
```powershell
# Run this script to verify your setup
.\verify-setup.ps1
```

### Step 1: Clone the Repository
```powershell
git clone <repository-url>
cd <repository-name>
cd "TC0405 - Selenium Automation"
```

### Step 2: Install Dependencies (First Time Only)
```powershell
mvn clean install
```
This will:
- Download all required libraries
- Auto-download ChromeDriver
- Compile the project

**⏱ Takes 1-2 minutes the first time** (downloads ~50MB of dependencies)

### Step 3: Run the Tests
```powershell
mvn test
```

That's it! The test will:
- ✅ Open Chrome browser automatically
- ✅ Execute automated test
- ✅ Capture screenshots
- ✅ Generate HTML report

---

## 📊 Viewing Results

### HTML Report (Recommended)
```powershell
# Windows
start test-output\TestReport_*.html

# Mac/Linux
open test-output/TestReport_*.html
```

### Screenshots
Located in: `screenshots/`
- `TestName_PASS_[timestamp].png` - Successful test
- `TestName_FAIL_[timestamp].png` - Failed test

---

## 🔧 Common Commands

### Run all tests
```powershell
mvn test
```

### Run specific test
```powershell
mvn test -Dtest=US04_DeleteProductFromCartTest#testTC0401_DeleteSingleProductFromCart
```

### Clean and rebuild
```powershell
mvn clean install
```

### Skip tests (compile only)
```powershell
mvn install -DskipTests
```

---

## ❓ Troubleshooting

### "Java not found"
1. Install Java 11+ from https://adoptium.net/
2. Add to PATH: `JAVA_HOME=C:\Program Files\...\jdk-17...`
3. Restart terminal

### "Maven not found"
1. Install Maven from https://maven.apache.org/download.cgi
2. Add Maven `bin` to PATH
3. Restart terminal

### "ChromeDriver version mismatch"
- Update Chrome browser to latest version
- WebDriverManager will auto-download matching driver

### Test fails
1. Check screenshot in `screenshots/` folder
2. View detailed report in `test-output/TestReport_*.html`
3. Ensure internet connection is stable
4. Verify https://advantageonlineshopping.com is accessible

---

## 💻 IDE Setup (Optional)

### VS Code
1. Install Extension: "Extension Pack for Java"
2. Install Extension: "Test Runner for Java"
3. Open project folder
4. Right-click test → "Run Test"

### IntelliJ IDEA
1. Open project (IntelliJ will auto-import Maven)
2. Right-click test → "Run"

### Eclipse
1. Import → Existing Maven Projects
2. Select project folder
3. Right-click test → "Run As" → "TestNG Test"

---

## 📝 Project Structure

```
TC0405 - Selenium Automation/
├── src/
│   ├── main/java/com/advantageonline/
│   │   ├── pages/              # Page Object Model
│   │   │   ├── HomePage.java
│   │   │   ├── CategoryPage.java
│   │   │   ├── ProductDetailsPage.java
│   │   │   └── ShoppingCartPage.java
│   │   └── utils/              # Utilities
│   │       ├── DriverFactory.java
│   │       ├── ScreenshotUtil.java
│   │       └── ExtentManager.java
│   └── test/java/com/advantageonline/
│       ├── tests/              # Test Cases
│       │   └── US04_DeleteProductFromCartTest.java
│       ├── listeners/          # TestNG Listeners
│       │   └── TestListener.java
│       └── resources/
│           └── testng.xml      # TestNG Configuration
├── pom.xml                     # Maven Dependencies
├── README.md                   # Full Documentation
├── GIT_PUSH_INSTRUCTIONS.md    # Git Guide
└── verify-setup.ps1            # Setup Checker
```

---

## ✅ What's Automated

**TC0401: Delete a single product from cart**

**Preconditions (Automated):**
1. Navigate to Advantage Online Shopping
2. Select Speakers category
3. Click on "Bose Soundlink Bluetooth Speaker III"
4. Click "Add to Cart"
5. Verify cart count = 1

**Test Steps (Automated):**
1. Navigate to shopping cart
2. Click "REMOVE" button
3. Verify cart displays "Your shopping cart is empty"
4. Verify cart count = 0

**Evidence Captured:**
- ✅ Screenshot on test success
- ✅ Screenshot on test failure
- ✅ HTML report with embedded screenshots
- ✅ Console logs

---

## 🎯 Expected Results

### Successful Test Run:
```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Execution Time:
~25-35 seconds

### Test Report:
Opens automatically showing:
- ✅ Test passed
- 📸 Screenshot evidence
- 📊 System information
- ⏱ Execution timeline

---

## 🆘 Need Help?

1. **Check setup**: Run `.\verify-setup.ps1`
2. **View report**: `test-output/TestReport_*.html`
3. **Check screenshot**: `screenshots/` folder
4. **Read full docs**: `README.md`

Happy Testing! 🎉
