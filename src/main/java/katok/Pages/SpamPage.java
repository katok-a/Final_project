package katok.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SpamPage extends BasePage {
    public SpamPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"b-letters\"]/div/div[5]/div/div[2]/div[1]/div/a/div[1]")
    private WebElement firstLetterInSpamCheckBox;

    @FindBy(xpath = "//*[@id=\"b-toolbar__right\"]/div[3]/div/div[2]/div[3]/div")
    private WebElement notASpamButton;

    @FindBy(xpath = ".//*[contains(text(), 'Письмо')]")
    private WebElement movedFromSpamConfirmation;

    public void checkFirstLetterInSpam(WebDriver driver) {
        setExplicitWait(driver, firstLetterInSpamCheckBox);
        firstLetterInSpamCheckBox.click();
    }

    public void restoreFromSpam() {
        notASpamButton.click();
    }

    public boolean isRestoredFromSpam(WebDriver driver){
        setExplicitWait(driver, movedFromSpamConfirmation);
        return movedFromSpamConfirmation.isDisplayed();
    }
}