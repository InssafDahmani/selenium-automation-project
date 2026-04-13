package com.advantageonline.tests;

import com.advantageonline.listeners.TestListener;
import com.advantageonline.utils.DriverFactory;
import com.advantageonline.utils.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Base Test class with setup and teardown methods
 */
@Listeners(TestListener.class)
public class BaseTest {
    
    protected WebDriver driver;
    
    /**
     * Setup method to initialize WebDriver before each test
     * @param browser Browser name from TestNG XML (defaults to "chrome")
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.initializeDriver(browser);
    }
    
    /**
     * Teardown method to quit WebDriver after each test
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
        }
        ExtentTestManager.flush();
    }
    
    /**
     * Get the WebDriver instance
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
}
