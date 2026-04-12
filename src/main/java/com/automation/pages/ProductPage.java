package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProductPage extends BasePage {

    private static final String BASE_URL = "https://advantageonlineshopping.com/#/product/";
    private static final By INCREMENT_BTN   = By.cssSelector("div.plus");
    private static final By DECREMENT_BTN   = By.cssSelector("div.minus");
    private static final By QTY_INPUT = By.xpath(
        "//input[contains(@ng-model,'uantity') or contains(@ng-model,'Qty')] | " +
        "//input[@type='number' and not(@disabled)] | " +
        "//div[@class='plus']/preceding-sibling::input[1] | " +
        "//div[contains(@class,'plus')]/..//input[1]"
    );
    private static final By ADD_TO_CART_BTN = By.xpath("//button[normalize-space(text())='ADD TO CART']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToProduct(int productId) {
        navigateTo(BASE_URL + productId);
        waitForClickable(INCREMENT_BTN);
    }

    public void navigateToProductWithColor(int productId, String hexColor) {
        navigateTo(BASE_URL + productId + "?color=" + hexColor + "&quantity=1");
        waitForClickable(INCREMENT_BTN);
    }

    public int getCurrentQuantity() {
        List<WebElement> inputs = driver.findElements(QTY_INPUT);
        for (WebElement input : inputs) {
            try {
                String val = input.getAttribute("value");
                if (val != null && val.matches("\\d+")) return Integer.parseInt(val);
            } catch (Exception ignored) { /* try next */ }
        }
        for (WebElement input : driver.findElements(By.cssSelector("input"))) {
            try {
                String val = input.getAttribute("value");
                if (val != null && val.matches("[1-9][0-9]*")) return Integer.parseInt(val);
            } catch (Exception ignored) { /* try next */ }
        }
        String url = driver.getCurrentUrl();
        Matcher m = Pattern.compile("[?&]quantity=(\\d+)").matcher(url);
        if (m.find()) return Integer.parseInt(m.group(1));

        throw new RuntimeException("getCurrentQuantity: could not locate quantity on page. URL=" + url);
    }

    public void setQuantity(int qty) {
        type(QTY_INPUT, String.valueOf(qty));
    }

    public void clickIncrementButton() {
        click(INCREMENT_BTN);
        waitForPresence(QTY_INPUT);
    }

    public void clickDecrementButton() {
        click(DECREMENT_BTN);
        waitForPresence(QTY_INPUT);
    }
    public void clickAddToCart() {
        try {
            click(ADD_TO_CART_BTN);
        } catch (Exception e) {
            jsClick(ADD_TO_CART_BTN);
        }
    }


    public boolean isIncrementButtonEnabled() {
        return isEnabled(INCREMENT_BTN);
    }

    public String getPageUrl() {
        return currentUrl();
    }

    public void refreshPage() {
        driver.navigate().refresh();
        waitForClickable(INCREMENT_BTN);
    }
}
