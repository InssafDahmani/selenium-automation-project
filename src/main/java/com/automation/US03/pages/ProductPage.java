package com.automation.US03.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By addToCartButton = By.name("save_to_cart");
    private final By cartButton = By.id("shoppingCartLink");

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickAddToCart() {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addButton.click();
    }

    public CartPage clickCartIcon() {
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cart.click();
        return new CartPage(driver, wait);
    }
}