# Chess.com UI Tests - Quick Start Guide

**Project:** Chess.com Web UI Test Automation
**Framework:** Carina 1.3.0 + Selenium + TestNG + Allure
**Status:** ✅ Production Ready (Phase 5 Complete)

---

## 🚀 Quick Start (3 Steps)

### Step 1: Start Selenium Server
```powershell
java -jar C:\Users\admin\selenium-server.jar standalone --port 4444
```

### Step 2: Run Tests
```powershell
# Using PowerShell script (recommended)
.\run-tests.ps1 -Suite smoke

# OR using Maven directly (remember the quotes!)
mvn clean test "-Dsuite=smoke"
```

### Step 3: View Results
```powershell
# Generate Allure report
mvn allure:serve
```

---

## 📋 Available Test Suites

| Suite | Tests | Duration | Command |
|-------|-------|----------|---------|
| **smoke** | 6 critical tests | ~1 min | `.\run-tests.ps1 -Suite smoke` |
| **regression** | 30+ tests | ~3-5 min | `.\run-tests.ps1 -Suite regression` |
| **parallel** | 30+ tests | ~2 min | `.\run-tests.ps1 -Suite parallel` |
| **full** | 30+ tests | ~2-3 min | `.\run-tests.ps1 -Suite full` |

---

## 📁 Project Files

### Core Files
- **pom.xml** - Maven configuration with Allure reporting
- **README.md** - Complete project documentation
- **IMPLEMENTATION_STATUS.md** - Full implementation details

### Phase 5 Deliverables
- **PHASE_5_COMPLETION_SUMMARY.md** - Phase 5 details
- **run-tests.ps1** - PowerShell execution script
- **run-tests.bat** - Batch execution script

### Source Code
- **src/main/java/** - Page Objects (7 pages + components)
- **src/test/java/** - Test Classes (5 classes, 37 tests)
- **src/test/resources/testng_suites/** - Test suite configurations

---

## 💡 Common Commands

### Run Tests

**PowerShell (use quotes!):**
```powershell
mvn clean test "-Dsuite=smoke"
mvn clean test "-Dsuite=regression"
mvn clean test "-Dsuite=parallel"
```

**Using Helper Script:**
```powershell
# Basic execution
.\run-tests.ps1 -Suite smoke

# With Allure report generation
.\run-tests.ps1 -Suite parallel -GenerateReport

# With report in browser
.\run-tests.ps1 -Suite parallel -GenerateReport -OpenReport
```

### Generate Reports

```powershell
# Generate Allure HTML report
mvn allure:report

# Generate and open in browser
mvn allure:serve

# View Surefire reports
start target\surefire-reports\index.html
```

### Specific Test Execution

```powershell
# Single test class
mvn test "-Dtest=PlayModeTests"

# Multiple classes
mvn test "-Dtest=PlayModeTests,PuzzleTests"

# Single test method
mvn test "-Dtest=PlayModeTests#testPlayPageAccessibility"
```

---

## 📊 Current Test Coverage

**Total Tests:** 37 (36 enabled, 1 disabled)
**Pass Rate:** 100%

**Test Distribution:**
- Navigation Tests: 8
- Login Tests: 6 (1 disabled - requires account)
- Play Mode Tests: 7
- Puzzle Tests: 9
- Learn Tests: 7

---

## 🔧 Troubleshooting

### PowerShell Command Error
```powershell
# ❌ WRONG - causes "Unknown lifecycle phase .xml" error
mvn clean test -Dsuite=smoke

# ✅ CORRECT - use quotes in PowerShell
mvn clean test "-Dsuite=smoke"
```

### Selenium Server Not Running
```powershell
# Check if running
Test-NetConnection -ComputerName localhost -Port 4444

# Start if needed
java -jar C:\Users\admin\selenium-server.jar standalone --port 4444
```

### Tests Won't Compile
```powershell
# Clean and rebuild
mvn clean compile test-compile
```

---

## 📚 Documentation

- **IMPLEMENTATION_STATUS.md** - Complete project overview
- **PHASE_5_COMPLETION_SUMMARY.md** - Phase 5 implementation details
- **README.md** - Original project documentation

---

## ✅ Project Status

**Phases Complete:** 5/7 (71%)
- ✅ Phase 1: Environment Setup
- ✅ Phase 2: Page Object Model
- ✅ Phase 3: Locator Discovery
- ✅ Phase 4: Test Implementation
- ✅ Phase 5: Execution & Reporting
- ⏳ Phase 6: CI/CD Integration (Pending)
- ⏳ Phase 7: Advanced Features (Pending)

**Current Status:** ✅ **Production Ready**

---

## 🎯 Next Steps

1. Run smoke tests to verify everything works
2. Generate your first Allure report
3. Review test results and coverage
4. Consider implementing Phase 6 (CI/CD) when ready

---

**Last Updated:** December 14, 2025
**Framework Version:** Carina 1.3.0 | Selenium 4.13.0 | TestNG 7.8.0 | Allure 2.24.0
