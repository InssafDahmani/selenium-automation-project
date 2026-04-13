package com.advantageonline.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

/**
 * Factory class for creating and configuring WebDriver instances
 */
public class DriverFactory {
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    /**
     * Initialize WebDriver for the specified browser
     * @param browser Browser name (chrome, firefox, edge)
     * @return WebDriver instance
     */
    public static WebDriver initializeDriver(String browser) {
        WebDriver webDriver;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                webDriver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
                
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();
        
        driver.set(webDriver);
        return webDriver;
    }
    
    /**
     * Get the current WebDriver instance
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    /**
     * Quit the WebDriver and remove from ThreadLocal
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
