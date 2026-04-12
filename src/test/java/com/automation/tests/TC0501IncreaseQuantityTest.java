package com.automation.tests;

import com.automation.base.DriverFactory;
import com.automation.pages.CartPage;
import com.automation.pages.ProductPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TC0501IncreaseQuantityTest {

    private static final int    PRODUCT_ID     = 20;
    private static final String SCREENSHOT_DIR = "target/screenshots/TC0501";

    private WebDriver   driver;
    private CartPage    cartPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUpPrecondition() {
        driver      = DriverFactory.initializeDriver();
        cartPage    = new CartPage(driver);
        productPage = new ProductPage(driver);

        new File(SCREENSHOT_DIR).mkdirs();
        productPage.navigateToProduct(PRODUCT_ID);
        productPage.clickAddToCart();
        waitBriefly(1_500);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test(description = "TC0501 – Increase product quantity by 1 via EDIT + '+' button")
    public void tc0501_increaseQuantityByOneViaEditButton() {
        cartPage.navigateToCart();
        Assert.assertTrue(cartPage.isCartNotEmpty(),
            "[Precondition] Cart must contain at least one product before test starts.");
        String productName = cartPage.getProductName(0);
        Assert.assertTrue(productName.toUpperCase().contains("BOSE"),
            "[Precondition] Expected BOSE product in cart, found: " + productName);
        int qtyBeforeEdit = cartPage.getProductQuantity(0);
        Assert.assertEquals(qtyBeforeEdit, 1,
            "Precondition: product must have quantity = 1 in cart before edit. Found: " + qtyBeforeEdit);
        double unitPrice = cartPage.getProductPrice(0);
        System.out.println("[TC0501] Unit price from cart: $" + unitPrice);
        cartPage.clickEditLink(0);
        String editPageUrl = productPage.getPageUrl();
        Assert.assertTrue(editPageUrl.contains("pageState=edit"),
            "Expected URL to contain 'pageState=edit' after clicking EDIT. URL: " + editPageUrl);
        int qtyOnEditPage = productPage.getCurrentQuantity();
        Assert.assertEquals(qtyOnEditPage, 1,
            "Product page in edit mode must show quantity = 1. Found: " + qtyOnEditPage);

        System.out.println("[TC0501] Navigated to EDIT mode. Product page qty: " + qtyOnEditPage);

        productPage.clickIncrementButton();
        int qtyAfterIncrement = productPage.getCurrentQuantity();
        Assert.assertEquals(qtyAfterIncrement, 2,
            "Product page quantity must be 2 after clicking '+'. Found: " + qtyAfterIncrement);

        System.out.println("[TC0501] Clicked '+'. Product page qty is now: " + qtyAfterIncrement);

        productPage.clickAddToCart();
        cartPage.waitForCartPageAfterUpdate();
        String cartUrl = cartPage.getPageUrl();
        Assert.assertTrue(cartUrl.contains("shoppingCart"),
            "Expected redirect to Shopping Cart after 'Add to Cart'. URL: " + cartUrl);
        int qtyInCartAfterUpdate = cartPage.getProductQuantity(0);
        Assert.assertEquals(qtyInCartAfterUpdate, 2,
            "Cart must show quantity = 2 after update. Found: " + qtyInCartAfterUpdate);
        double updatedCartPrice = cartPage.getProductPrice(0);
        double expectedPrice    = unitPrice * 2;
        Assert.assertEquals(updatedCartPrice, expectedPrice, 0.01,
            "Cart price must equal unit price × 2 = $" + expectedPrice
            + ". Found: $" + updatedCartPrice);

        System.out.println("[TC0501] Cart qty=" + qtyInCartAfterUpdate
            + " | Price=$" + updatedCartPrice + " | Expected=$" + expectedPrice);

        captureScreenshot("TC0501_PASS");
    }
    private void captureScreenshot(String label) {
        try {
            TakesScreenshot ts  = (TakesScreenshot) driver;
            File            src = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = SCREENSHOT_DIR + File.separator + label + "_" + timestamp + ".png";
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
            Files.copy(src.toPath(), Paths.get(path));
            System.out.println("[TC0501] Screenshot saved: " + path);
        } catch (Exception e) {
            System.err.println("[TC0501] Screenshot capture failed: " + e.getMessage());
        }
    }

    private void waitBriefly(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}
