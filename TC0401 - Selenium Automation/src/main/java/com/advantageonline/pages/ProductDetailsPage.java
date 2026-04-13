package com.advantageonline.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object for Product Details Page
 */
public class ProductDetailsPage extends BasePage {
    
    @FindBy(css = "button[name='save_to_cart']")
    private WebElement addToCartButton;
    
    @FindBy(css = ".roboto-regular.productName")
    private WebElement productName;
    
    @FindBy(css = ".roboto-thin.screen768.ng-binding")
    private WebElement productPrice;
    
    @FindBy(css = "input[name='quantity']")
    private WebElement quantityField;
    
    @FindBy(xpath = "//a[@id='shoppingCartLink']/child::span[1]")
    private WebElement cartCount;
    
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        // Scroll to make sure Add to Cart button is visible
        scrollToAddToCartButton();
    }
    
    /**
     * Scroll to make the Add to Cart button visible
     */
    private void scrollToAddToCartButton() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("button[name='save_to_cart']")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(500); // Brief pause for scroll to complete
        } catch (Exception e) {
            // Scroll failed, continue anyway
        }
    }
    
    /**
     * Click Add to Cart button
     */
    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        // Wait for cart count to update
        try {
            Thread.sleep(1000); // Allow time for cart to update
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get product name
     * @return Product name text
     */
    public String getProductName() {
        try {
            return productName.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Get product price
     * @return Product price text
     */
    public String getProductPrice() {
        try {
            return productPrice.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Set product quantity
     * @param quantity Quantity to set
     */
    public void setQuantity(int quantity) {
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
    }
    
    /**
     * Get cart item count
     * @return Cart count as string
     */
    public String getCartCount() {
        try {
            // Wait for cart count to be visible and have non-empty text
            wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(cartCount),
                driver -> {
                    String text = cartCount.getText().trim();
                    return !text.isEmpty() && !text.equals("0");
                }
            ));
            return cartCount.getText().trim();
        } catch (Exception e) {
            // If wait times out, return whatever text is there
            String text = cartCount.getText().trim();
            return text.isEmpty() ? "0" : text;
        }
    }
    
    /**
     * Navigate to shopping cart
     * @return ShoppingCartPage instance
     */
    public ShoppingCartPage navigateToCart() {
        driver.get("https://advantageonlineshopping.com/#/shoppingCart");
        return new ShoppingCartPage(driver);
    }
    
    /**
     * Verify product details page is displayed
     * @return true if page is loaded
     */
    public boolean isProductDetailsPageDisplayed() {
        try {
            return addToCartButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
