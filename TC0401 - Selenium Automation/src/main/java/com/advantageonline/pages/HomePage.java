package com.advantageonline.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object for Advantage Online Shopping Home Page
 */
public class HomePage extends BasePage {
    
    @FindBy(css = ".SpeakersImg")
    private WebElement speakersCategory;
    
    @FindBy(css = ".categoryCell:nth-child(2)")
    private WebElement tabletsCategory;
    
    @FindBy(css = ".laptopImg")
    private WebElement laptopsCategory;
    
    @FindBy(css = ".miceImg")
    private WebElement miceCategory;
    
    @FindBy(css = ".twoRows")
    private WebElement headphonesCategory;
    
    @FindBy(xpath = "//a[@id='shoppingCartLink']/child::span[1]")
    private WebElement cartCount;
    
    @FindBy(id = "menuCart")
    private WebElement cartIcon;
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to home page
     */
    public void navigateToHomePage() {
        navigateTo("https://advantageonlineshopping.com/#/");
        wait.until(ExpectedConditions.elementToBeClickable(speakersCategory));
    }
    
    /**
     * Click on Speakers category
     * @return CategoryPage instance
     */
    public CategoryPage clickSpeakersCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(speakersCategory));
        speakersCategory.click();
        return new CategoryPage(driver);
    }
    
    /**
     * Click on Tablets category
     * @return CategoryPage instance
     */
    public CategoryPage clickTabletsCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(tabletsCategory));
        tabletsCategory.click();
        return new CategoryPage(driver);
    }
    
    /**
     * Click on Laptops category
     * @return CategoryPage instance
     */
    public CategoryPage clickLaptopsCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(laptopsCategory));
        laptopsCategory.click();
        return new CategoryPage(driver);
    }
    
    /**
     * Get cart item count
     * @return Cart count as string
     */
    public String getCartCount() {
        return cartCount.getText();
    }
    
    /**
     * Click on cart icon to navigate to shopping cart
     * @return ShoppingCartPage instance
     */
    public ShoppingCartPage clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
        return new ShoppingCartPage(driver);
    }
    
    /**
     * Verify home page is loaded
     * @return true if home page is loaded
     */
    public boolean isHomePageDisplayed() {
        return speakersCategory.isDisplayed() && 
               tabletsCategory.isDisplayed();
    }
}
