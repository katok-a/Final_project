package katok.Core.WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private DriverFactory() {
    };

    public static WebDriver getDriver(String driverType) { //Hope its a Factory
        if (driverType.equals("chrome")) {
            return new ChromeDriver();
        }
        return new EdgeDriver();
    }
}
