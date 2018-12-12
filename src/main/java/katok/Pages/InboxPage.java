package katok.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InboxPage extends BasePage {
    public InboxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//*[@id=\"b-toolbar__left\"]/div/div/div[2]/div/a/span")
    WebElement composeLetterButton;

    @FindBy(xpath = "//*[@id=\"PH_logoutLink\"]")
    WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"b-toolbar__right\"]/div[2]/div/div[2]/div[3]")
    private WebElement addToSpamButton;

    @FindBy(xpath = "//*[@id=\"b-nav_folders\"]/div/div[4]/a")
    private WebElement goToSpamLink;

    @FindBy(xpath = ".//*/div/a/div/div/div[@class='b-checkbox__box']")
    private WebElement inboxLettersCheckbox;

    @FindBy(xpath = "//*[@id=\"jsHtml\"]/body/div[1]/div[1]/div/span")
    private WebElement movedToSpamConfirmed;

    public void goToComposePage(WebDriver driver) {
        setExplicitWait(driver, composeLetterButton);
        composeLetterButton.click();
    }

    public void goToSpamPage(WebDriver driver) {
        setExplicitWait(driver, goToSpamLink);
        goToSpamLink.click();
    }

    public void checkEmail(WebDriver driver) {
        setExplicitWait(driver, inboxLettersCheckbox);
        inboxLettersCheckbox.click();
    }

    public void addToSpam(){
        addToSpamButton.click();
    }

    public boolean isConfirmedToSpam(WebDriver driver){
        setExplicitWait(driver,movedToSpamConfirmed);
        return movedToSpamConfirmed.isDisplayed();
    }

    public void logOut(WebDriver driver){
        setExplicitWait(driver, logoutButton);
        logoutButton.click();
    }
}

