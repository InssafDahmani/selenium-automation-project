package com.advantageonline.listeners;

import com.advantageonline.tests.BaseTest;
import com.advantageonline.utils.ExtentTestManager;
import com.advantageonline.utils.ScreenshotUtil;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener for automatic screenshot capture and reporting
 */
public class TestListener implements ITestListener {
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("========================================");
        System.out.println("Starting Test Suite: " + context.getName());
        System.out.println("========================================");
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("========================================");
        System.out.println("Finished Test Suite: " + context.getName());
        System.out.println("Tests Passed: " + context.getPassedTests().size());
        System.out.println("Tests Failed: " + context.getFailedTests().size());
        System.out.println("Tests Skipped: " + context.getSkippedTests().size());
        System.out.println("========================================");
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        
        if (description != null && !description.isEmpty()) {
            ExtentTestManager.startTest(testName, description);
        } else {
            ExtentTestManager.startTest(testName);
        }
        
        ExtentTestManager.getTest().log(Status.INFO, "Test Started: " + testName);
        System.out.println("Starting Test: " + testName);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        
        // Capture screenshot on success
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName + "_PASS");
        
        // Log to Extent Report
        try {
            ExtentTestManager.getTest().pass("Test Passed: " + testName,
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            ExtentTestManager.getTest().pass("Test Passed: " + testName);
        }
        
        System.out.println("✓ Test PASSED: " + testName);
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        
        // Capture screenshot on failure
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName + "_FAIL");
        
        // Log failure to Extent Report with screenshot
        try {
            ExtentTestManager.getTest().fail("Test Failed: " + testName,
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            ExtentTestManager.getTest().fail(result.getThrowable());
        } catch (Exception e) {
            ExtentTestManager.getTest().fail("Test Failed: " + testName);
            ExtentTestManager.getTest().fail(result.getThrowable());
        }
        
        System.err.println("✗ Test FAILED: " + testName);
        System.err.println("Reason: " + result.getThrowable().getMessage());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        
        ExtentTestManager.getTest().skip("Test Skipped: " + testName);
        if (result.getThrowable() != null) {
            ExtentTestManager.getTest().skip(result.getThrowable());
        }
        
        System.out.println("⊘ Test SKIPPED: " + testName);
    }
}
