package katok.Pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public abstract class BasePage {
    private static final String SCREENSHOTS_PATH = "./target/ScreenShots/";
    private static int secondsToWait = 5;

   protected void setImplicitlyWait(WebDriver driver, long time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    }

    public void setExplicitWait(WebDriver driver, WebElement element) {
        (new WebDriverWait(driver, secondsToWait)).until(ExpectedConditions.visibilityOf(element));
    }
    public void takeScreenShot(WebDriver driver) {
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Thread.currentThread().getStackTrace()[1].getMethodName();
        try {
            FileUtils.copyFile(screenShot, new File(SCREENSHOTS_PATH + screenShot.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
