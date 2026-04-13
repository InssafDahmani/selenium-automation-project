package com.advantageonline.tests;

import com.advantageonline.pages.CategoryPage;
import com.advantageonline.pages.HomePage;
import com.advantageonline.pages.ProductDetailsPage;
import com.advantageonline.pages.ShoppingCartPage;
import com.advantageonline.utils.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for US04 - Delete Product from Cart functionality
 * Test Cases from: US4 Test Cases CSV.csv
 */
public class US04_DeleteProductFromCartTest extends BaseTest {
    
    /**
     * TC0401: Delete a single product from cart
     * Precondition: Cart contains one single product
     * 
     * Steps:
     * 1. Navigate to cart (https://advantageonlineshopping.com/#/shoppingCart)
     * 2. Click on "REMOVE"
     * 
     * Expected Result: Cart displays "Your shopping cart is empty"
     */
    @Test(description = "TC0401 - Delete a single product from cart")
    public void testTC0401_DeleteSingleProductFromCart() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting TC0401: Delete a single product from cart");
        
        // PRECONDITION: Add one product to cart
        ExtentTestManager.getTest().log(Status.INFO, "PRECONDITION: Adding a product to cart");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        ExtentTestManager.getTest().log(Status.PASS, "Navigated to home page");
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page should be displayed");
        
        CategoryPage categoryPage = homePage.clickSpeakersCategory();
        ExtentTestManager.getTest().log(Status.PASS, "Clicked on Speakers category");
        Assert.assertTrue(categoryPage.isCategoryPageDisplayed(), "Category page should be displayed");
        
        ProductDetailsPage productPage = categoryPage.clickProductByName("Bose Soundlink Bluetooth Speaker III");
        ExtentTestManager.getTest().log(Status.PASS, "Clicked on product: Bose Soundlink Bluetooth Speaker III");
        Assert.assertTrue(productPage.isProductDetailsPageDisplayed(), "Product details page should be displayed");
        
        productPage.clickAddToCart();
        ExtentTestManager.getTest().log(Status.PASS, "Clicked Add to Cart button");
        
        String cartCountAfterAdd = productPage.getCartCount();
        Assert.assertEquals(cartCountAfterAdd, "1", "Cart should contain 1 item after adding product");
        ExtentTestManager.getTest().log(Status.PASS, "Cart count is 1 - Precondition satisfied");
        
        // STEP 1: Navigate to cart
        ExtentTestManager.getTest().log(Status.INFO, "STEP 1: Navigate to shopping cart");
        ShoppingCartPage cartPage = productPage.navigateToCart();
        Assert.assertTrue(cartPage.isShoppingCartPageDisplayed(), "Shopping cart page should be displayed");
        ExtentTestManager.getTest().log(Status.PASS, "Navigated to shopping cart page");
        
        boolean hasProducts = cartPage.hasProducts();
        Assert.assertTrue(hasProducts, "Cart should contain one product");
        ExtentTestManager.getTest().log(Status.PASS, "Shopping cart contains one product");
        
        // STEP 2: Click on "REMOVE"
        ExtentTestManager.getTest().log(Status.INFO, "STEP 2: Click on REMOVE button");
        cartPage.clickRemoveFirstProduct();
        ExtentTestManager.getTest().log(Status.PASS, "Clicked REMOVE button");
        
        // EXPECTED RESULT: Cart displays "Your shopping cart is empty"
        ExtentTestManager.getTest().log(Status.INFO, "Verifying expected result");
        boolean isCartEmpty = cartPage.isCartEmpty();
        Assert.assertTrue(isCartEmpty, "Cart should be empty after removing the product");
        
        String emptyMessage = cartPage.getEmptyCartMessage();
        Assert.assertTrue(emptyMessage.contains("Your shopping cart is empty"), 
            "Cart should display 'Your shopping cart is empty' message");
        
        String cartCountAfterRemove = cartPage.getCartCount();
        Assert.assertEquals(cartCountAfterRemove, "0", "Cart count should be 0 after removing product");
        
        ExtentTestManager.getTest().log(Status.PASS, "✓ Cart is empty - Message: " + emptyMessage);
        ExtentTestManager.getTest().log(Status.PASS, "✓ Cart count is 0");
        ExtentTestManager.getTest().log(Status.PASS, "TC0401 PASSED - Product successfully deleted from cart");
    }
    
    /**
     * Utility test to demonstrate adding a product to cart (for other test cases)
     */
    @Test(description = "Helper - Add product to cart", enabled = false)
    public void testAddProductToCart() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        CategoryPage categoryPage = homePage.clickSpeakersCategory();
        ProductDetailsPage productPage = categoryPage.clickFirstProduct();
        
        productPage.clickAddToCart();
        String cartCount = productPage.getCartCount();
        
        Assert.assertEquals(cartCount, "1", "Cart should contain 1 item");
        ExtentTestManager.getTest().log(Status.PASS, "Product added to cart successfully");
    }
}
