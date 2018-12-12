package katok.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    public WebElement passwordField;

    @FindBy(id = "mailbox:domain")
    private WebElement domain;

    @FindBy(id = "mailbox:submit")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id=\"PH_user-email\"]")
    private WebElement eMailLoggedIn;

    @FindBy(xpath = "//*[@id=\"mailbox:error\"]")
    private WebElement loginError;

    public WebElement getLoginError() {
        return loginError;
    }



    public void enterLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void selectDomain(String domainName) {
        Select domainSelect = new Select(domain);
        domainSelect.selectByVisibleText(domainName);
    }

    public void doLogin(String login, String password, String domainName) {
        enterLogin(login);
        enterPassword(password);
        selectDomain(domainName);
        submitButton.click();
    }

    public void pushSubmitButton() {
        submitButton.click();
    }

    public String confirmLogin() {
        return eMailLoggedIn.getText();
    }
}
