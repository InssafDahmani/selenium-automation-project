package com.automation.US03.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By beatsStudioProduct = By.xpath("//a[contains(.,'Beats Studio')]");

    public CategoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public ProductPage clickBeatsStudioProduct() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(beatsStudioProduct));
        product.click();
        return new ProductPage(driver, wait);
    }
}