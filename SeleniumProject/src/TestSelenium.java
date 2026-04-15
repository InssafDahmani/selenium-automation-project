import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TestSelenium {
    static WebDriver driver;
    static WebDriverWait wait;
    
    public static void main(String[] args) {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            loginEmptyPasswordTest();
        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
    
    public static void loginEmptyPasswordTest() throws Exception {
        // Navigate to login page
        driver.get("http://advantageonlineshopping.com/");
        Thread.sleep(5000);
        
        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
        Thread.sleep(2000);
        
        // Click profile icon in header
        var profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("hrefUserIcon")));
        profileIcon.click();
        Thread.sleep(3000);
        
        // Click sign in button to open login form
        var signInBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sign_in_btn")));
        Thread.sleep(1000);
        signInBtn.click();
        Thread.sleep(3000);
        
        // Enter username
        var usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.clear();
        usernameField.sendKeys("AyaHm");
        System.out.println("✓ Username entered: AyaHm");

        // Keep password empty on purpose
        var passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.clear();
        passwordField.sendKeys(Keys.TAB);
        
        // Click SIGN IN to trigger required-field validation
        Thread.sleep(1500);
        var loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sign_in_btn")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        System.out.println("✓ Login button clicked with empty password");
        Thread.sleep(3000);
        
        // Verify exact error message
        var errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(text(),'Password field is required')]")
        ));
        String errorMsg = errorElement.getText().trim();

        if (!"Password field is required".equals(errorMsg)) {
            throw new RuntimeException("Unexpected validation message: " + errorMsg);
        }

        System.out.println("✓ Error message displayed: " + errorMsg);
        System.out.println("\n=== TEST PASSED ===");
    }
}
