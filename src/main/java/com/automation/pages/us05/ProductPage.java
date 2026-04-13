package com.automation.pages.us05;

import com.automation.base.us05.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductPage extends BasePage {

    private static final String BASE_URL = "https://advantageonlineshopping.com/#/product/";

    private static final By INCREMENT_BTN = By.cssSelector("div.plus");
    private static final By DECREMENT_BTN = By.cssSelector("div.minus");
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

    public int getCurrentQuantity() {
        List<WebElement> inputs = driver.findElements(QTY_INPUT);
        for (WebElement input : inputs) {
            try {
                String value = input.getAttribute("value");
                if (value != null && value.matches("\\d+")) {
                    return Integer.parseInt(value);
                }
            } catch (Exception ignored) {
            }
        }

        for (WebElement input : driver.findElements(By.cssSelector("input"))) {
            try {
                String value = input.getAttribute("value");
                if (value != null && value.matches("[1-9][0-9]*")) {
                    return Integer.parseInt(value);
                }
            } catch (Exception ignored) {
            }
        }

        String url = driver.getCurrentUrl();
        Matcher matcher = Pattern.compile("[?&]quantity=(\\d+)").matcher(url);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new RuntimeException("getCurrentQuantity: could not locate quantity on page. URL=" + url);
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

    public String getPageUrl() {
        return currentUrl();
    }
}