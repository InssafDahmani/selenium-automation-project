package com.advantageonline.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page Object for Shopping Cart Page
 */
public class ShoppingCartPage extends BasePage {
    
    @FindBy(css = "a.remove.red")
    private List<WebElement> removeButtons;
    
    @FindBy(css = "tr[ng-repeat*='cart']")
    private List<WebElement> cartProducts;
    
    @FindBy(css = ".roboto-thin")
    private WebElement emptyCartMessage;
    
    @FindBy(xpath = "//a[@id='shoppingCartLink']/child::span[1]")
    private WebElement cartCount;
    
    @FindBy(css = "button[name='check_out_btn']")
    private WebElement checkoutButton;
    
    @FindBy(xpath = "//label[@class='roboto-regular ng-scope']")
    private WebElement emptyCartLabel;
    
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to shopping cart page
     */
    public void navigateToCart() {
        navigateTo("https://advantageonlineshopping.com/#/shoppingCart");
        // Wait for page to load
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Click Remove button for the first product
     */
    public void clickRemoveFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0)));
        removeButtons.get(0).click();
        // Wait for cart to update
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Click Remove button for a specific product by index
     * @param index Index of the product (0-based)
     */
    public void clickRemoveProduct(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(index)));
        removeButtons.get(index).click();
        // Wait for cart to update
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get the number of products in cart
     * @return Number of products
     */
    public int getProductCount() {
        try {
            return cartProducts.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * Get cart item count from header
     * @return Cart count as string
     */
    public String getCartCount() {
        try {
            String count = cartCount.getText().trim();
            // If cart count is empty or hidden, return "0"
            return (count.isEmpty() || count.equals("")) ? "0" : count;
        } catch (Exception e) {
            return "0";
        }
    }
    
    /**
     * Check if cart is empty
     * @return true if cart is empty
     */
    public boolean isCartEmpty() {
        try {
            return driver.getPageSource().contains("Your shopping cart is empty") || 
                   getProductCount() == 0;
        } catch (Exception e) {
            return true;
        }
    }
    
    /**
     * Get empty cart message
     * @return Empty cart message text
     */
    public String getEmptyCartMessage() {
        try {
            if (driver.getPageSource().contains("Your shopping cart is empty")) {
                return "Your shopping cart is empty";
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Verify shopping cart page is displayed
     * @return true if shopping cart page is loaded
     */
    public boolean isShoppingCartPageDisplayed() {
        return getCurrentUrl().contains("shoppingCart");
    }
    
    /**
     * Verify cart contains products
     * @return true if cart has products
     */
    public boolean hasProducts() {
        return getProductCount() > 0;
    }
}
