package com.automation.US05.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 10;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    // ---- wait helpers -------------------------------------------------------

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForUrlContains(String fragment) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.urlContains(fragment));
    }

    // ---- interaction helpers ------------------------------------------------

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    /** JavaScript click – bypasses disabled/covered-element issues. */
    protected void jsClick(By locator) {
        WebElement el = waitForPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void type(By locator, String text) {
        WebElement el = waitForVisibility(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText().trim();
    }

    protected String getAttr(By locator, String attr) {
        return waitForVisibility(locator).getAttribute(attr);
    }

    protected boolean isDisplayed(By locator) {
        try { return waitForVisibility(locator).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    protected boolean isEnabled(By locator) {
        try { return waitForPresence(locator).isEnabled(); }
        catch (Exception e) { return false; }
    }

    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }

    protected String currentUrl() {
        return driver.getCurrentUrl();
    }
}
