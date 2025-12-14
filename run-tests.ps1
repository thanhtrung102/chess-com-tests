# Chess.com UI Test Execution Script (PowerShell)
# This script provides convenient commands for running different test suites

param(
    [Parameter(Mandatory=$false)]
    [ValidateSet('smoke', 'regression', 'full', 'parallel')]
    [string]$Suite = 'smoke',

    [Parameter(Mandatory=$false)]
    [switch]$GenerateReport,

    [Parameter(Mandatory=$false)]
    [switch]$OpenReport
)

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Chess.com UI Test Automation" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if Selenium Server is running
Write-Host "Checking Selenium Server..." -ForegroundColor Yellow
$seleniumRunning = Test-NetConnection -ComputerName localhost -Port 4444 -WarningAction SilentlyContinue -ErrorAction SilentlyContinue
if (-not $seleniumRunning.TcpTestSucceeded) {
    Write-Host "WARNING: Selenium Server is not running on port 4444!" -ForegroundColor Red
    Write-Host "Please start Selenium Server first:" -ForegroundColor Yellow
    Write-Host "  java -jar C:\Users\admin\selenium-server.jar standalone --port 4444" -ForegroundColor White
    Write-Host ""
    $continue = Read-Host "Continue anyway? (y/n)"
    if ($continue -ne 'y') {
        exit 1
    }
} else {
    Write-Host "Selenium Server is running on port 4444" -ForegroundColor Green
}

Write-Host ""
Write-Host "Running Test Suite: $Suite" -ForegroundColor Cyan
Write-Host ""

# Build the Maven command
$mavenCommand = "mvn clean test"

# Add suite parameter
switch ($Suite) {
    'smoke' {
        $mavenCommand += " ""-Dsuite=smoke"""
        Write-Host "Suite: Smoke Tests (6 tests, ~1 minute)" -ForegroundColor White
    }
    'regression' {
        $mavenCommand += " ""-Dsuite=regression"""
        Write-Host "Suite: Regression Tests (30+ tests, ~3-5 minutes)" -ForegroundColor White
    }
    'full' {
        $mavenCommand += " ""-Dsuite=full"""
        Write-Host "Suite: Full Test Suite (30+ tests, parallel, ~2-3 minutes)" -ForegroundColor White
    }
    'parallel' {
        $mavenCommand += " ""-Dsuite=parallel"""
        Write-Host "Suite: Parallel Execution (30+ tests, optimized, ~2 minutes)" -ForegroundColor White
    }
}

Write-Host ""
Write-Host "Executing: $mavenCommand" -ForegroundColor Gray
Write-Host ""

# Execute tests
Invoke-Expression $mavenCommand
$testExitCode = $LASTEXITCODE

Write-Host ""
if ($testExitCode -eq 0) {
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "TESTS PASSED" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
} else {
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "TESTS FAILED" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
}

# Generate Allure report if requested
if ($GenerateReport) {
    Write-Host ""
    Write-Host "Generating Allure Report..." -ForegroundColor Cyan
    mvn allure:report

    if ($OpenReport) {
        Write-Host "Opening Allure Report in browser..." -ForegroundColor Cyan
        mvn allure:serve
    } else {
        Write-Host ""
        Write-Host "Allure Report generated at: target\allure-report\index.html" -ForegroundColor Green
        Write-Host "To view the report, run: mvn allure:serve" -ForegroundColor Yellow
    }
}

Write-Host ""
Write-Host "Test Reports Location:" -ForegroundColor Cyan
Write-Host "  Surefire Reports: target\surefire-reports\index.html" -ForegroundColor White
Write-Host "  Allure Results:   target\allure-results\" -ForegroundColor White
Write-Host "  Allure Report:    target\allure-report\index.html" -ForegroundColor White
Write-Host ""

exit $testExitCode
