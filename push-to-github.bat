@echo off
REM Chess.com Tests - Push to GitHub Script (Batch version)
REM This script automates the process of pushing your code to GitHub

echo ========================================
echo Chess.com UI Tests - GitHub Upload
echo ========================================
echo.

REM Check if git is initialized
if not exist .git (
    echo ERROR: Not a git repository!
    echo Please run this script from the chess-com-tests directory
    exit /b 1
)

echo Current commits:
git log --oneline -5
echo.

REM Get GitHub username
set /p username="Enter your GitHub username: "

REM Get repository name
set /p repoName="Enter repository name (default: chess-com-tests): "
if "%repoName%"=="" set repoName=chess-com-tests

echo.
echo ========================================
echo Repository Settings:
echo   GitHub Username: %username%
echo   Repository Name: %repoName%
echo   Remote URL: https://github.com/%username%/%repoName%.git
echo ========================================
echo.

set /p confirm="Proceed with push? (y/N): "
if /i not "%confirm%"=="y" (
    echo Cancelled.
    exit /b 0
)

REM Add remote
echo.
echo Adding remote...
git remote add origin https://github.com/%username%/%repoName%.git 2>nul
if errorlevel 1 (
    echo Remote already exists, updating...
    git remote set-url origin https://github.com/%username%/%repoName%.git
)
echo [OK] Remote configured

REM Rename branch to main
echo.
echo Renaming branch to 'main'...
git branch -M main
echo [OK] Branch renamed to 'main'

REM Push to GitHub
echo.
echo Pushing to GitHub...
echo You may be prompted for your GitHub credentials...
echo.

git push -u origin main

if errorlevel 0 (
    echo.
    echo ========================================
    echo SUCCESS! Code pushed to GitHub!
    echo ========================================
    echo.
    echo Your repository is now available at:
    echo https://github.com/%username%/%repoName%
    echo.
    echo Next steps:
    echo 1. Visit https://github.com/%username%/%repoName%
    echo 2. Add a description and topics to your repository
    echo 3. Share the link with others!
    echo.
) else (
    echo.
    echo ========================================
    echo ERROR: Push failed!
    echo ========================================
    echo.
    echo Common issues:
    echo 1. Repository doesn't exist on GitHub yet
    echo    - Create it at: https://github.com/new
    echo    - Repository name: %repoName%
    echo.
    echo 2. Authentication failed
    echo    - Use a Personal Access Token
    echo    - Create at: https://github.com/settings/tokens
    echo.
)

pause
