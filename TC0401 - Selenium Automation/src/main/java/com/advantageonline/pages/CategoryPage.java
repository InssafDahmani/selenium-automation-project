package com.advantageonline.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page Object for Product Category Page
 */
public class CategoryPage extends BasePage {
    
    @FindBy(css = "a.productName")
    private List<WebElement> productNames;
    
    @FindBy(css = ".select.ng-binding")
    private WebElement categoryTitle;
    
    public CategoryPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click on a product by name
     * @param productName Name of the product to click
     * @return ProductDetailsPage instance
     */
    public ProductDetailsPage clickProductByName(String productName) {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(@class, 'productName') and text()='" + productName + "']")
        ));
        product.click();
        return new ProductDetailsPage(driver);
    }
    
    /**
     * Click on the first available product
     * @return ProductDetailsPage instance
     */
    public ProductDetailsPage clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(productNames.get(0)));
        productNames.get(0).click();
        return new ProductDetailsPage(driver);
    }
    
    /**
     * Get the category title
     * @return Category title text
     */
    public String getCategoryTitle() {
        return categoryTitle.getText();
    }
    
    /**
     * Get count of products displayed
     * @return Number of products
     */
    public int getProductCount() {
        return productNames.size();
    }
    
    /**
     * Verify category page is displayed
     * @return true if category page is loaded
     */
    public boolean isCategoryPageDisplayed() {
        return categoryTitle.isDisplayed() && !productNames.isEmpty();
    }
}
