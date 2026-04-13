package com.advantageonline.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots
 */
public class ScreenshotUtil {
    
    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/screenshots/";
    
    static {
        // Create screenshots directory if it doesn't exist
        File directory = new File(SCREENSHOT_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    /**
     * Capture screenshot and save to file
     * @param driver WebDriver instance
     * @param screenshotName Name for the screenshot file
     * @return Path to the saved screenshot
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destination = SCREENSHOT_DIR + screenshotName + "_" + timestamp + ".png";
            
            FileUtils.copyFile(source, new File(destination));
            System.out.println("Screenshot captured: " + destination);
            return destination;
            
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get the screenshot directory path
     * @return Screenshot directory path
     */
    public static String getScreenshotDirectory() {
        return SCREENSHOT_DIR;
    }
}
