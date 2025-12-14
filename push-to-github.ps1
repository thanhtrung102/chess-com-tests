# Chess.com Tests - Push to GitHub Script
# This script automates the process of pushing your code to GitHub

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Chess.com UI Tests - GitHub Upload" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if git is initialized
if (-not (Test-Path .git)) {
    Write-Host "ERROR: Not a git repository!" -ForegroundColor Red
    Write-Host "Please run this script from the chess-com-tests directory" -ForegroundColor Yellow
    exit 1
}

# Check if credentials file exists and is ignored
if (Test-Path "src/main/resources/_config.properties") {
    $gitignoreContent = Get-Content .gitignore -Raw
    if ($gitignoreContent -match "src/main/resources/_config.properties") {
        Write-Host "[OK] Credentials file is properly ignored" -ForegroundColor Green
    } else {
        Write-Host "[WARNING] Credentials file exists but not in .gitignore!" -ForegroundColor Yellow
        Write-Host "Your credentials might be exposed!" -ForegroundColor Red
        $continue = Read-Host "Continue anyway? (y/N)"
        if ($continue -ne "y") {
            exit 1
        }
    }
}

Write-Host ""
Write-Host "Current commits:" -ForegroundColor Yellow
git log --oneline -5
Write-Host ""

# Get GitHub username
Write-Host "Enter your GitHub username:" -ForegroundColor Cyan
$username = Read-Host

# Get repository name
Write-Host ""
Write-Host "Enter repository name (default: chess-com-tests):" -ForegroundColor Cyan
$repoName = Read-Host
if ([string]::IsNullOrWhiteSpace($repoName)) {
    $repoName = "chess-com-tests"
}

# Confirm settings
Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Repository Settings:" -ForegroundColor Yellow
Write-Host "  GitHub Username: $username" -ForegroundColor White
Write-Host "  Repository Name: $repoName" -ForegroundColor White
Write-Host "  Remote URL: https://github.com/$username/$repoName.git" -ForegroundColor White
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

$confirm = Read-Host "Proceed with push? (y/N)"
if ($confirm -ne "y") {
    Write-Host "Cancelled." -ForegroundColor Yellow
    exit 0
}

# Check if remote already exists
$existingRemote = git remote get-url origin 2>$null
if ($existingRemote) {
    Write-Host ""
    Write-Host "Remote 'origin' already exists: $existingRemote" -ForegroundColor Yellow
    $updateRemote = Read-Host "Update remote URL? (y/N)"
    if ($updateRemote -eq "y") {
        git remote set-url origin "https://github.com/$username/$repoName.git"
        Write-Host "[OK] Remote URL updated" -ForegroundColor Green
    }
} else {
    # Add remote
    Write-Host ""
    Write-Host "Adding remote..." -ForegroundColor Yellow
    git remote add origin "https://github.com/$username/$repoName.git"
    Write-Host "[OK] Remote added" -ForegroundColor Green
}

# Rename branch to main
Write-Host ""
Write-Host "Renaming branch to 'main'..." -ForegroundColor Yellow
git branch -M main
Write-Host "[OK] Branch renamed to 'main'" -ForegroundColor Green

# Push to GitHub
Write-Host ""
Write-Host "Pushing to GitHub..." -ForegroundColor Yellow
Write-Host "You may be prompted for your GitHub credentials..." -ForegroundColor Cyan
Write-Host ""

git push -u origin main

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "SUCCESS! Code pushed to GitHub!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "Your repository is now available at:" -ForegroundColor Cyan
    Write-Host "https://github.com/$username/$repoName" -ForegroundColor White
    Write-Host ""
    Write-Host "Next steps:" -ForegroundColor Yellow
    Write-Host "1. Visit https://github.com/$username/$repoName" -ForegroundColor White
    Write-Host "2. Add a description and topics to your repository" -ForegroundColor White
    Write-Host "3. Share the link with others!" -ForegroundColor White
    Write-Host ""
} else {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "ERROR: Push failed!" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
    Write-Host ""
    Write-Host "Common issues:" -ForegroundColor Yellow
    Write-Host "1. Repository doesn't exist on GitHub yet" -ForegroundColor White
    Write-Host "   - Create it at: https://github.com/new" -ForegroundColor White
    Write-Host "   - Repository name: $repoName" -ForegroundColor White
    Write-Host "   - Don't initialize with README (we already have one)" -ForegroundColor White
    Write-Host ""
    Write-Host "2. Authentication failed" -ForegroundColor White
    Write-Host "   - Use a Personal Access Token instead of password" -ForegroundColor White
    Write-Host "   - Create at: https://github.com/settings/tokens" -ForegroundColor White
    Write-Host ""
    Write-Host "3. Remote URL incorrect" -ForegroundColor White
    Write-Host "   - Check repository exists" -ForegroundColor White
    Write-Host "   - Verify username: $username" -ForegroundColor White
    Write-Host ""
}
