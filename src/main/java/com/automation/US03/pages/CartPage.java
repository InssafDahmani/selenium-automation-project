package com.automation.US03.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By beatsStudioLabel = By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'beats studio')]");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isBeatsStudioInCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(beatsStudioLabel)).isDisplayed();
    }

    public Path takeScreenshot(String fileName) throws IOException {
        Path screenshotDir = Path.of("target", "screenshots");
        Files.createDirectories(screenshotDir);

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = screenshotDir.resolve(fileName);
        Files.copy(source.toPath(), destination, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        return destination;
    }
}