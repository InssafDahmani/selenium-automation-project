package com.automation.pages.us05;

import com.automation.base.us05.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    private static final String CART_URL = "https://advantageonlineshopping.com/#/shoppingCart";

    private static final By CART_ROWS = By.cssSelector("#shoppingCart tr.ng-scope:not(.lastProduct)");
    private static final By PRODUCT_NAME = By.cssSelector("label.productName");
    private static final By QTY_LABEL = By.cssSelector("td.quantityMobile label.ng-binding");
    private static final By PRICE_LABEL = By.cssSelector("p.price.ng-binding");
    private static final By EDIT_LINK = By.cssSelector("a.edit");
    private static final By PLUS_BUTTON = By.cssSelector("div.plus");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCart() {
        navigateTo(CART_URL);
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(d -> !d.findElements(CART_ROWS).isEmpty());
    }

    public boolean isCartNotEmpty() {
        return !driver.findElements(CART_ROWS).isEmpty();
    }

    private WebElement getRow(int index) {
        List<WebElement> rows = driver.findElements(CART_ROWS);
        if (index >= rows.size()) {
            throw new IndexOutOfBoundsException(
                "Row index " + index + " out of bounds (rows found: " + rows.size() + ")");
        }
        return rows.get(index);
    }

    public String getProductName(int rowIndex) {
        return getRow(rowIndex).findElement(PRODUCT_NAME).getText().trim();
    }

    public int getProductQuantity(int rowIndex) {
        String raw = getRow(rowIndex).findElement(QTY_LABEL).getText().trim();
        return Integer.parseInt(raw.replaceAll("[^0-9]", ""));
    }

    public double getProductPrice(int rowIndex) {
        String raw = getRow(rowIndex).findElement(PRICE_LABEL).getText().trim();
        String cleaned = raw.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleaned);
    }

    public void clickEditLink(int rowIndex) {
        WebElement editLink = getRow(rowIndex).findElement(EDIT_LINK);
        editLink.click();
        waitForClickable(PLUS_BUTTON);
    }

    public void waitForCartPageAfterUpdate() {
        waitForUrlContains("shoppingCart");
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(d -> !d.findElements(CART_ROWS).isEmpty());
    }

    public String getPageUrl() {
        return currentUrl();
    }
}