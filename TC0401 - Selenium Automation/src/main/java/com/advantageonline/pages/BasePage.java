package com.advantageonline.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base Page Object class with common functionality
 */
public class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navigate to a URL
     * @param url URL to navigate to
     */
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * Get current page URL
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Get page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}
