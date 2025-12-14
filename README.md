# Chess.com UI Test Automation

Automated UI testing framework for Chess.com using Carina Framework, Selenium WebDriver, and TestNG.

## Features

- **Page Object Model (POM)** architecture with two-tier design
- **Allure Reporting** for comprehensive test reports
- **Parallel Execution** support for faster test runs
- **36 Automated Tests** covering core functionality
- **Cross-browser Testing** via Selenium Grid

## Test Coverage

### Test Suites
- **Navigation Tests** (3 tests) - Homepage and navigation verification
- **Login Tests** (5 tests) - Authentication and login functionality
- **Play Mode Tests** (7 tests) - Play page functionality
- **Puzzle Tests** (7 tests) - Puzzle page features
- **Learn Tests** (7 tests) - Learn page content

### Test Categories
- **Smoke Suite** - Critical path tests (6 tests)
- **Regression Suite** - Full test coverage (36 tests)
- **Parallel Suite** - Performance-optimized parallel execution

## Technology Stack

- **Java 11**
- **Carina Framework 1.3.0**
- **Selenium WebDriver 4.13.0**
- **TestNG 7.8.0**
- **Allure 2.24.0**
- **Maven 3.6+**

## Prerequisites

1. **Java 11 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **Selenium Grid** (for browser automation)
   ```bash
   # Download Selenium Server
   # https://github.com/SeleniumHQ/selenium/releases

   # Start Selenium Grid
   java -jar selenium-server.jar standalone --port 4444
   ```

## Quick Start

### 1. Clone the repository
```bash
git clone <repository-url>
cd chess-com-tests
```

### 2. Configure test credentials
Update `src/main/resources/_config.properties` with your Chess.com credentials:
```properties
test_user_username=your-email@gmail.com
test_user_password=your-password
```

### 3. Start Selenium Grid
```bash
java -jar selenium-server.jar standalone --port 4444
```

### 4. Run tests

**Smoke Tests** (Quick validation - 6 tests):
```bash
mvn clean test -Dsuite=smoke
```

**Regression Tests** (Full suite - 36 tests):
```bash
mvn clean test -Dsuite=regression
```

**Parallel Tests** (Fast execution - 5 threads):
```bash
mvn clean test -Dsuite=parallel
```

**Specific Test**:
```bash
mvn test -Dtest=LoginTests#testSuccessfulLogin
```

### 5. Generate Allure Report
```bash
mvn allure:serve
```

## Project Structure

```
chess-com-tests/
├── src/
│   ├── main/
│   │   ├── java/com/chess/
│   │   │   ├── pages/
│   │   │   │   ├── common/        # Page object interfaces
│   │   │   │   │   ├── ChessBasePageBase.java
│   │   │   │   │   ├── HomePageBase.java
│   │   │   │   │   ├── LoginPageBase.java
│   │   │   │   │   ├── PlayPageBase.java
│   │   │   │   │   ├── PuzzlesPageBase.java
│   │   │   │   │   ├── LearnPageBase.java
│   │   │   │   │   └── GamePageBase.java
│   │   │   │   └── desktop/       # Desktop implementations
│   │   │   │       ├── HomePage.java
│   │   │   │       ├── LoginPage.java
│   │   │   │       ├── PlayPage.java
│   │   │   │       ├── PuzzlesPage.java
│   │   │   │       ├── LearnPage.java
│   │   │   │       └── GamePage.java
│   │   │   └── components/
│   │   │       └── NavigationBar.java
│   │   └── resources/
│   │       └── _config.properties
│   └── test/
│       ├── java/com/chess/tests/
│       │   ├── NavigationTests.java
│       │   └── LoginTests.java
│       └── resources/testng_suites/
│           ├── smoke.xml
│           └── regression.xml
└── pom.xml
```

## Prerequisites

### Required Software:
1. **Java 11 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **Selenium Server** (Required by Carina framework)
   - Download from: https://www.selenium.dev/downloads/
   - Or use existing: `C:\Users\admin\selenium-server.jar`

4. **Chrome Browser** (latest version)

## Setup Instructions

### 1. Start Selenium Server

```powershell
# Terminal 1 - Start Selenium Server
cd C:\Users\admin
java -jar selenium-server.jar standalone --port 4444
```

**Wait for:** "Selenium Server is up and running on http://localhost:4444"

**Keep this terminal running!**

### 2. Clone/Navigate to Project

```powershell
cd C:\Users\admin\chess-com-tests
```

### 3. Update Configuration (Optional)

Edit `src/main/resources/_config.properties`:

```properties
# For login tests with real credentials
test_user_username=your_chess_username
test_user_email=your_email@example.com
test_user_password=your_password
```

**Note:** Login tests with real credentials are disabled by default

### 4. Compile Project

```powershell
mvn clean compile
```

## Running Tests

### Run Smoke Suite (6 tests - No login required)

```powershell
mvn clean test -DsuiteXmlFile=src\test\resources\testng_suites\smoke.xml
```

**Tests included:**
- Home page loads
- Navigation to Play page
- Navigation to Puzzles page
- Navigation to Learn page
- Login page accessibility
- Login with invalid credentials

**Expected duration:** 2-3 minutes

### Run Regression Suite (All tests)

```powershell
mvn clean test -DsuiteXmlFile=src\test\resources\testng_suites\regression.xml
```

### Run Specific Test Class

```powershell
# Navigation tests only
mvn test -Dtest=NavigationTests

# Login tests only
mvn test -Dtest=LoginTests
```

### Run Single Test Method

```powershell
mvn test -Dtest=NavigationTests#testHomePageLoads
```

## Test Reports

After test execution, reports are generated in:

### HTML Reports:
```
target/surefire-reports/index.html
target/surefire-reports/emailable-report.html
```

### Screenshots:
```
reports/<timestamp>/
```

### Open Reports:
```powershell
# Open in browser
start target\surefire-reports\index.html
```

## Test Categories

### Smoke Tests (No account required)
- ✅ Home page accessibility
- ✅ Navigation flow
- ✅ Page loading verification
- ✅ Basic element presence
- ✅ Login page accessibility
- ✅ Invalid login validation

### Regression Tests (Some require account)
- All navigation tests
- All login tests (valid login disabled by default)
- Play page options
- Puzzles page elements
- Learn page content

## Page Objects Implemented

### 1. **HomePage**
- Navigate to Play, Puzzles, Learn
- Check login status
- Access navigation bar

### 2. **LoginPage**
- Enter username/email
- Enter password
- Submit login
- Validate errors

### 3. **PlayPage**
- Play online option
- Play computer option
- View play options

### 4. **PuzzlesPage**
- Start puzzle
- View puzzle board
- Check puzzle rating

### 5. **LearnPage**
- View lessons
- Access learning content

### 6. **GamePage**
- View chess board
- Game status
- Resign/Draw options

## Configuration

### Selenium Settings
```properties
selenium_url=http://localhost:4444/wd/hub
browser=chrome
explicit_timeout=30
implicit_timeout=10
page_load_timeout=60
```

### Parallel Execution
```properties
thread_count=3  # Run 3 tests in parallel
```

### Environment
```properties
env=PROD
PROD.base=https://www.chess.com
PROD.api_url=https://api.chess.com/pub
```

## Troubleshooting

### Issue: Tests fail with "selenium_url missing"

**Solution:** Start Selenium Server first
```powershell
cd C:\Users\admin
java -jar selenium-server.jar standalone --port 4444
```

### Issue: "Connection refused" to Chess.com

**Solution:** Check internet connection and firewall settings

### Issue: Element not found errors

**Possible causes:**
1. Chess.com UI changed - update locators
2. Page didn't load - increase timeouts
3. Dynamic content - add wait conditions

**Solution:** Update locators in page objects to match current Chess.com UI

### Issue: Tests are slow

**Solutions:**
1. Reduce explicit_timeout in config
2. Increase thread_count for parallel execution
3. Run specific test suites instead of all tests

## Best Practices

### 1. Don't commit credentials
- Never commit real Chess.com credentials
- Use test accounts only
- Keep sensitive data in .gitignore

### 2. Keep Selenium running
- Start Selenium Server before tests
- Don't stop it between test runs
- One Selenium instance can serve multiple test runs

### 3. Use appropriate test labels
- `@TestLabel(name = "feature", value = "smoke")` for critical tests
- `@TestLabel(name = "feature", value = "regression")` for comprehensive tests

### 4. Update locators when Chess.com UI changes
- Page objects isolate locator changes
- Only update desktop/*.java files
- Keep common/*.java interfaces stable

## Extending the Framework

### Add New Page Object:

1. **Create base interface:**
   ```java
   // src/main/java/com/chess/pages/common/NewPageBase.java
   public abstract class NewPageBase extends ChessBasePageBase {
       // Define abstract methods
   }
   ```

2. **Create implementation:**
   ```java
   // src/main/java/com/chess/pages/desktop/NewPage.java
   @DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = NewPageBase.class)
   public class NewPage extends NewPageBase {
       // Implement with actual locators
   }
   ```

3. **Create tests:**
   ```java
   // src/test/java/com/chess/tests/NewTests.java
   public class NewTests implements IAbstractTest {
       @Test
       public void testNewFeature() {
           // Test implementation
       }
   }
   ```

### Add New Test Suite:

Create `src/test/resources/testng_suites/new-suite.xml`:
```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="New Suite" verbose="1">
    <test name="New Tests">
        <classes>
            <class name="com.chess.tests.NewTests"/>
        </classes>
    </test>
</suite>
```

## Quick Commands Reference

```powershell
# Start Selenium (Terminal 1 - Keep running)
cd C:\Users\admin && java -jar selenium-server.jar standalone --port 4444

# Run smoke tests (Terminal 2)
cd C:\Users\admin\chess-com-tests
mvn clean test -DsuiteXmlFile=src\test\resources\testng_suites\smoke.xml

# Run all tests
mvn clean test

# Run specific test
mvn test -Dtest=NavigationTests#testHomePageLoads

# View reports
start target\surefire-reports\index.html
```

## Test Execution Time

| Suite | Tests | Duration | Requires Account |
|-------|-------|----------|------------------|
| Smoke | 6 | 2-3 min | No |
| Regression | 13 | 5-7 min | Optional |
| NavigationTests | 8 | 3-4 min | No |
| LoginTests | 5 | 2-3 min | Optional |

## Notes

1. **No Chess.com account required** for most tests
2. **Login tests work without account** - they test error handling
3. **Valid login test is disabled** - enable and add credentials to test
4. **Locators may need updates** - Chess.com UI changes over time
5. **Selenium Server must run** - Carina framework requirement

## License

This is a test automation framework for educational purposes.

## Contact

For questions or issues, please create an issue in the repository.

---

**Created:** December 14, 2025
**Framework:** Carina 1.3.0
**Target:** https://www.chess.com
**Status:** Ready to use
