package katok.Pages;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected void closeAllTabs(WebDriver driver) {
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
        for (int i = 0; i < handles.size(); i++) {
            driver.switchTo().window(handles.get(i));
            driver.close();
        }
    }

    protected void setImplicitlyWait(WebDriver driver, long time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    }
}
