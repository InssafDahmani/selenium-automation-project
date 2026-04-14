package com.automation.US01.pages;
import com.automation.US01.base.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // ─── Locators ──────────────────────────────
    @FindBy(xpath = "//a[@id='menuUserLink']")
    private WebElement userIcon;

    @FindBy(xpath = "//a[@class='create-new-account ng-scope']")
    private WebElement createAccountLink;

    // ─── Constructeur ──────────────────────────
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ─── Actions ───────────────────────────────
    public void openHomePage(String url) {
        driver.get(url);
    }

    public void clickUserIcon() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(org.openqa.selenium.By.className("loader")));
        waitForClickable(userIcon);
        userIcon.click();
    }

    public RegisterPage clickCreateNewAccount() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(org.openqa.selenium.By.className("loader")));
        waitForClickable(createAccountLink);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", createAccountLink);
        return new RegisterPage(driver);
    }
}