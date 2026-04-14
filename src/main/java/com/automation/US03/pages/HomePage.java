package com.automation.US03.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final String BASE_URL = "https://advantageonlineshopping.com";

    private final By headphonesCategory = By.xpath("//span[normalize-space()='HEADPHONES']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openSite() {
        driver.get(BASE_URL);
    }

    public CategoryPage clickHeadphonesCategory() {
        WebElement categoryCard = wait.until(ExpectedConditions.elementToBeClickable(headphonesCategory));
        categoryCard.click();
        return new CategoryPage(driver, wait);
    }
}