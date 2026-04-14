package com.automation.tests;
import com.automation.US01.pages.HomePage;
import com.automation.US01.pages.RegisterPage;
import com.automation.US01.utils.ConfigReader;
import com.automation.US01.utils.DriverManager;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC013_UsernameExistsTest {

    private HomePage homePage;
    private RegisterPage registerPage;

    // ─── Initialisation ────────────────────────
    @BeforeClass
    public void setUp() {
        DriverManager.initDriver();
        homePage = new HomePage(DriverManager.getDriver());
    }

    // ─── TC-013 ────────────────────────────────
    @Test
    public void TC013_usernameAlreadyExists() throws InterruptedException {

        // ÉTAPE 1 — Ouvrir le site
        homePage.openHomePage(ConfigReader.get("base.url"));
        Thread.sleep(2000); // Ralentit l'exécution de 2 secondes

        // ÉTAPE 2 — Aller sur le formulaire
        homePage.clickUserIcon();
        Thread.sleep(1000); // Ralentissement
        registerPage = homePage.clickCreateNewAccount();
        Thread.sleep(2000); // Ralentissement avant de remplir

        // ÉTAPE 3 — Remplir avec username existant
        registerPage.fillRegistrationForm(
            ConfigReader.get("existing.username"),  // ← déjà en base
            ConfigReader.get("new.email"),
            ConfigReader.get("password")
        );
        Thread.sleep(2000); // Observation du formulaire rempli

        // ÉTAPE 4 — Soumettre
        registerPage.clickRegister();
        Thread.sleep(5000); // Observation après clic

        // ÉTAPE 5 — Assertions
        Assert.assertTrue(
            registerPage.isUsernameErrorDisplayed(),
            "Le message d'erreur username n'est pas affiché"
        );

        Assert.assertTrue(
            registerPage.getUsernameErrorMessage().replace(" ", "").toLowerCase().contains("usernamealreadyexists"),
            "Le message d'erreur ne correspond pas: " + registerPage.getUsernameErrorMessage()
        );

        // Message de succès dans la console
        System.out.println("TEST PASSED : Le message d'erreur est bien affiche ! (" + registerPage.getUsernameErrorMessage() + ")");

    }

    // ─── Nettoyage ─────────────────────────────
    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}