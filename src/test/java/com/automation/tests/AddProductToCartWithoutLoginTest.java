package com.automation.tests;

import com.automation.US03.base.BaseTest;
import com.automation.US03.pages.CartPage;
import com.automation.US03.pages.CategoryPage;
import com.automation.US03.pages.HomePage;
import com.automation.US03.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;

public class AddProductToCartWithoutLoginTest extends BaseTest {

    @Test(description = "TC03 - Ajouter un produit au panier sans connexion")
    public void shouldAddBeatsStudioToCartWithoutLogin() throws IOException {
        HomePage homePage = new HomePage(driver, wait);
    homePage.openSite();

    CategoryPage categoryPage = homePage.clickHeadphonesCategory();
    ProductPage productPage = categoryPage.clickBeatsStudioProduct();
    productPage.clickAddToCart();
    CartPage cartPage = productPage.clickCartIcon();

    Assert.assertTrue(cartPage.isBeatsStudioInCart(), "Beats Studio product should be visible in cart.");

        Path screenshotPath = cartPage.takeScreenshot("TC03_Ajouter_Produit_Sans_Connexion_PASSED.png");
        System.out.println("Screenshot saved: " + screenshotPath.toAbsolutePath());
    }
}