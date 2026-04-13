# Setup Verification Script
# Run this script to verify your environment is ready to run tests

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Selenium Test Automation - Setup Verification" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

$allGood = $true

# Check Java
Write-Host "Checking Java..." -NoNewline
try {
    $javaVersion = java -version 2>&1 | Select-String "version" | ForEach-Object { $_ -replace '.*version "(.+?)".*', '$1' }
    if ($javaVersion) {
        Write-Host " ✓ Installed: $javaVersion" -ForegroundColor Green
        $majorVersion = [int]($javaVersion -split '\.')[0]
        if ($majorVersion -lt 11) {
            Write-Host "  ⚠ Warning: Java 11+ recommended, you have version $majorVersion" -ForegroundColor Yellow
        }
    }
} catch {
    Write-Host " ✗ NOT FOUND" -ForegroundColor Red
    Write-Host "  → Download from: https://adoptium.net/" -ForegroundColor Yellow
    $allGood = $false
}

# Check Maven
Write-Host "Checking Maven..." -NoNewline
try {
    $mavenVersion = mvn -version 2>&1 | Select-String "Apache Maven" | ForEach-Object { $_ -replace 'Apache Maven (.+?) \(.*', '$1' }
    if ($mavenVersion) {
        Write-Host " ✓ Installed: $mavenVersion" -ForegroundColor Green
    }
} catch {
    Write-Host " ✗ NOT FOUND" -ForegroundColor Red
    Write-Host "  → Download from: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    $allGood = $false
}

# Check Chrome
Write-Host "Checking Chrome..." -NoNewline
$chromePath = "C:\Program Files\Google\Chrome\Application\chrome.exe"
$chromePathX86 = "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
if (Test-Path $chromePath) {
    Write-Host " ✓ Installed" -ForegroundColor Green
} elseif (Test-Path $chromePathX86) {
    Write-Host " ✓ Installed" -ForegroundColor Green
} else {
    Write-Host " ⚠ Not found in default location" -ForegroundColor Yellow
    Write-Host "  → Ensure Chrome browser is installed" -ForegroundColor Yellow
}

# Check Git
Write-Host "Checking Git..." -NoNewline
try {
    $gitVersion = git --version 2>&1 | ForEach-Object { $_ -replace 'git version (.+)', '$1' }
    if ($gitVersion) {
        Write-Host " ✓ Installed: $gitVersion" -ForegroundColor Green
    }
} catch {
    Write-Host " ✗ NOT FOUND (Optional)" -ForegroundColor Yellow
    Write-Host "  → Needed only for version control" -ForegroundColor Yellow
}

# Check pom.xml exists
Write-Host "Checking project files..." -NoNewline
if (Test-Path "pom.xml") {
    Write-Host " ✓ pom.xml found" -ForegroundColor Green
} else {
    Write-Host " ✗ pom.xml NOT FOUND" -ForegroundColor Red
    Write-Host "  → Run this script from project root directory" -ForegroundColor Yellow
    $allGood = $false
}

# Check src structure
if (Test-Path "src\test\java") {
    Write-Host "Project structure..." -NoNewline
    Write-Host " ✓ Valid" -ForegroundColor Green
} else {
    Write-Host "Project structure..." -NoNewline
    Write-Host " ✗ Invalid" -ForegroundColor Red
    $allGood = $false
}

Write-Host "`n========================================" -ForegroundColor Cyan

if ($allGood) {
    Write-Host "✓ ALL CHECKS PASSED!" -ForegroundColor Green
    Write-Host "`nYou can now run tests with:" -ForegroundColor Cyan
    Write-Host "  mvn test" -ForegroundColor White
} else {
    Write-Host "✗ SETUP INCOMPLETE" -ForegroundColor Red
    Write-Host "`nPlease install missing components above." -ForegroundColor Yellow
}

Write-Host "========================================`n" -ForegroundColor Cyan

# Test Maven dependencies
Write-Host "Testing Maven setup (this may take a minute)..." -ForegroundColor Cyan
try {
    $result = mvn dependency:resolve -q 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✓ Maven dependencies OK" -ForegroundColor Green
    } else {
        Write-Host "⚠ Run 'mvn clean install' to download dependencies" -ForegroundColor Yellow
    }
} catch {
    Write-Host "⚠ Could not verify Maven dependencies" -ForegroundColor Yellow
}

Write-Host "`nSetup verification complete!`n" -ForegroundColor Cyan
