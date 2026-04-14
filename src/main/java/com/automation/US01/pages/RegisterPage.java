package com.automation.US01.pages;
import com.automation.US01.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends BasePage {

    // ─── Locators ──────────────────────────────
    @FindBy(xpath = "//input[@name='usernameRegisterPage']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name='emailRegisterPage']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='passwordRegisterPage']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='confirm_passwordRegisterPage']")
    private WebElement confirmPasswordField;


    @FindBy(xpath = "//input[@name='i_agree']")
    private WebElement agreeCheckbox;

    @FindBy(xpath = "//button[@id='register_btn']")
    private WebElement registerButton;

    @FindBy(xpath = "//label[contains(text(), 'already exists') or contains(text(), 'exist')]")
    private WebElement usernameErrorMsg;

    // ─── Constructeur ──────────────────────────
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // ─── Remplissage des champs ─────────────────
    public void enterUsername(String username) {
        waitForElement(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void checkAgree() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", agreeCheckbox);
        } catch (Exception e) {
            agreeCheckbox.click();
        }
    }

    public void clickRegister() {
        waitForClickable(registerButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);
    }

    // ─── Méthode globale ───────────────────────
    public void fillRegistrationForm(String username, String email,
                                     String password)
                                      {
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        checkAgree();
    }

    // ─── Vérifications ─────────────────────────
    public boolean isUsernameErrorDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement error = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(text(),'User name already exists')]")
            )
        );

        return error.isDisplayed();
    }

    public String getUsernameErrorMessage() {
        return driver.findElement(org.openqa.selenium.By.xpath("//label[contains(text(),'User name already exists')]")).getText();
    }

    public boolean isStillOnRegisterPage() {
        return driver.getCurrentUrl().contains("register");
    }
}