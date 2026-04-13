package com.advantageonline.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager class for ExtentTest instances
 */
public class ExtentTestManager {
    
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private static ExtentReports extent = ExtentManager.createInstance();
    
    /**
     * Get the ExtentTest instance for current thread
     * @return ExtentTest instance
     */
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
    
    /**
     * Start a new test and add to the report
     * @param testName Name of the test
     * @return ExtentTest instance
     */
    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
    
    /**
     * Start a new test with description
     * @param testName Name of the test
     * @param description Test description
     * @return ExtentTest instance
     */
    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
    
    /**
     * Flush the report
     */
    public static synchronized void flush() {
        extent.flush();
    }
}
