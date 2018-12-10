package katok.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPage extends BasePage {
    public InboxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//*[@id=\"b-toolbar__left\"]/div/div/div[2]/div/a/span")
    WebElement composeLetterButton;

    public void goToComposePage(WebDriver driver) {
        setExplicitWait(driver, 10, composeLetterButton);
        composeLetterButton.click();
    }
}

