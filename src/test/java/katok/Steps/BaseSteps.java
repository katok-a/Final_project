package katok.Steps;

import katok.Core.WebDriver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class BaseSteps {
   WebDriver driver = DriverFactory.getDriver("chrome");
}
