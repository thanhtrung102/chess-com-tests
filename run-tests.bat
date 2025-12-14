@echo off
REM Chess.com UI Test Execution Script (Windows Batch)
REM Usage: run-tests.bat [smoke|regression|full|parallel]

setlocal

set SUITE=%1
if "%SUITE%"=="" set SUITE=smoke

echo ========================================
echo Chess.com UI Test Automation
echo ========================================
echo.

REM Check if Selenium Server is running
echo Checking Selenium Server...
netstat -an | find ":4444" >nul
if errorlevel 1 (
    echo WARNING: Selenium Server may not be running on port 4444!
    echo Please start Selenium Server first:
    echo   java -jar C:\Users\admin\selenium-server.jar standalone --port 4444
    echo.
    set /p CONTINUE="Continue anyway? (y/n): "
    if /i not "%CONTINUE%"=="y" exit /b 1
) else (
    echo Selenium Server is running on port 4444
)

echo.
echo Running Test Suite: %SUITE%
echo.

REM Run tests based on suite
if "%SUITE%"=="smoke" (
    echo Suite: Smoke Tests (6 tests, ~1 minute)
    mvn clean test "-Dsuite=smoke"
) else if "%SUITE%"=="regression" (
    echo Suite: Regression Tests (30+ tests, ~3-5 minutes)
    mvn clean test "-Dsuite=regression"
) else if "%SUITE%"=="full" (
    echo Suite: Full Test Suite (30+ tests, parallel, ~2-3 minutes)
    mvn clean test "-Dsuite=full"
) else if "%SUITE%"=="parallel" (
    echo Suite: Parallel Execution (30+ tests, optimized, ~2 minutes)
    mvn clean test "-Dsuite=parallel"
) else (
    echo Invalid suite: %SUITE%
    echo Valid options: smoke, regression, full, parallel
    exit /b 1
)

echo.
if errorlevel 1 (
    echo ========================================
    echo TESTS FAILED
    echo ========================================
) else (
    echo ========================================
    echo TESTS PASSED
    echo ========================================
)

echo.
echo Test Reports Location:
echo   Surefire Reports: target\surefire-reports\index.html
echo   Allure Results:   target\allure-results\
echo   Allure Report:    Run 'mvn allure:report' to generate
echo.
echo To view Allure report: mvn allure:serve
echo.

endlocal
