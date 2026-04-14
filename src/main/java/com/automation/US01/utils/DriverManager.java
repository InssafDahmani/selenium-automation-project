package com.automation.US01.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {

    private static WebDriver driver;

    // ─── Empêche l'instanciation ───────────────
    private DriverManager() {}

    // ─── Initialise le driver ──────────────────
    public static void initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));
    }

    // ─── Retourne le driver ────────────────────
    public static WebDriver getDriver() {
        return driver;
    }

    // ─── Ferme le navigateur ───────────────────
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}