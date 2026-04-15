import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class DebugSelectors {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("http://advantageonlineshopping.com/");
            Thread.sleep(5000);
            
            // Wait for loader
            driver.findElements(By.className("loader")).stream()
                .filter(el -> {
                    try {
                        return el.isDisplayed();
                    } catch (Exception e) {
                        return false;
                    }
                }).forEach(el -> {
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {}
                });
            
            Thread.sleep(2000);
            
            // Click profile icon
            driver.findElement(By.id("hrefUserIcon")).click();
            Thread.sleep(3000);
            
            // Click sign in
            var signIn = driver.findElement(By.id("sign_in_btn"));
            signIn.click();
            Thread.sleep(3000);
            
            // Enter username
            var username = driver.findElement(By.name("username"));
            username.sendKeys("AyaHm");
            Thread.sleep(2000);
            
            // Find all buttons in the form
            System.out.println("=== ALL BUTTONS IN PAGE ===");
            driver.findElements(By.xpath("//button")).forEach(el -> {
                try {
                    System.out.println("Button ID: " + el.getAttribute("id") + " | Text: '" + el.getText() + "' | Class: " + el.getAttribute("class") + " | Visible: " + el.isDisplayed());
                } catch (Exception e) {
                    System.out.println("Button - error getting details");
                }
            });
            
            Thread.sleep(2000);
            
        } finally {
            driver.quit();
        }
    }
}
