package com.advantageonline.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Manager class for ExtentReports configuration
 */
public class ExtentManager {
    
    private static ExtentReports extent;
    private static final String REPORT_DIR = System.getProperty("user.dir") + "/test-output/";
    
    /**
     * Create and configure ExtentReports instance
     * @return ExtentReports instance
     */
    public static ExtentReports createInstance() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = REPORT_DIR + "TestReport_" + timestamp + ".html";
        return createInstance(fileName);
    }
    
    /**
     * Create and configure ExtentReports instance with custom filename
     * @param fileName Report file name with path
     * @return ExtentReports instance
     */
    public static ExtentReports createInstance(String fileName) {
        // Create report directory if it doesn't exist
        File directory = new File(REPORT_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        
        // Configure report settings
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Advantage Online Shopping - Test Report");
        sparkReporter.config().setReportName("Selenium Test Automation Results");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Set system information
        extent.setSystemInfo("Application", "Advantage Online Shopping");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        
        return extent;
    }
    
    /**
     * Get the ExtentReports instance
     * @return ExtentReports instance
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }
}
