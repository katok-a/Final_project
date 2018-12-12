package katok.Steps;

import cucumber.api.java.en.Given;
import katok.Core.API.VkApiHandler;
import katok.Core.SQL.SqlDataMiner;
import katok.Core.XML.XMLDataMiner;
import katok.Core.XML.eMailsModel;
import katok.Pages.ComposePage;
import katok.Pages.InboxPage;
import katok.Pages.SpamPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.FluentWait;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import katok.Core.WebDriver.DriverFactory;
import katok.Pages.HomePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.TakesScreenshot;

public class Steps {
    private WebDriver driver = DriverFactory.getDriver("chrome");
    private HomePage homePage = new HomePage(driver);
    private ComposePage composePage = new ComposePage(driver);
    private InboxPage inboxPage = new InboxPage(driver);
    private SpamPage spamPage = new SpamPage(driver);

    private SqlDataMiner dataMiner = SqlDataMiner.getMiner();
    private String login = dataMiner.getFieldByColumn("login");
    private String password = dataMiner.getFieldByColumn("password");
    private String domainName = dataMiner.getFieldByColumn("domain");
    private String incorrectLoginPassword = "!@#$%";

    private XMLDataMiner xmlDataMiner = new XMLDataMiner();
    private List<eMailsModel> emails = xmlDataMiner.getEmails();
    private int eMailsRecepientPosition = 3;
    private String adressee = emails.get(eMailsRecepientPosition).getRecepient();
    private String subject = emails.get(eMailsRecepientPosition).getSubject();
    private String mailText = emails.get(eMailsRecepientPosition).getText();

    private VkApiHandler vk = new VkApiHandler();
    private String apiMessage = "You have new email at " + login + domainName;
    private int apiRecepient = emails.get(eMailsRecepientPosition).getId();

    private static final Logger logger = Logger.getLogger(Steps.class);

    public Steps() throws SQLException, ParserConfigurationException, SAXException, IOException {
    }

    @When("^I am on (.+) page$")
    public void loadHomePage(String url) {
        driver.get(url);
        logger.info(url + "opened");
        homePage.takeScreenShot(driver);

    }

    @When("^I enter correct UserName to Login field$")
    public void enterLogin() {
        homePage.enterLogin(login);
        logger.info("Correct Login Entered");
        homePage.takeScreenShot(driver);
    }

    @When("^I enter correct UserName to Login field in Capital letters$")
    public void enterLoginInCapital(){
        homePage.enterLogin(login.toUpperCase());
        logger.info("Correct in Login Capital letters Entered");
        homePage.takeScreenShot(driver);
    }

    @When("^I enter correct Password to Password field$")
    public void enterPassword() {
        homePage.enterPassword(password);
        logger.info("Correct Password Entered");
        homePage.takeScreenShot(driver);
    }

    @When("^I choose correct domain name from dropdown$")
    public void choseDomain() {
        homePage.selectDomain(domainName);
        logger.info("Correct domain selected");
        homePage.takeScreenShot(driver);
    }

    @When("^I click Войти$")
    public void clickSubmitButton() {
        homePage.pushSubmitButton();
        logger.info("Submit Button pushed");
        homePage.takeScreenShot(driver);
    }

    @Then("^I see Inbox page$")
    public void isLogedIn() {
        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return homePage.confirmLogin().equals(login + domainName);
            }
        };
        FluentWait wait = new FluentWait(driver);
        wait.until(function);
        Assert.assertTrue(homePage.confirmLogin().equals(login + domainName));
        logger.info("Inbox page displayed");
        homePage.takeScreenShot(driver);
        driver.close();
    }

    @When("^I go spam page$")
    public void goToSpamPage() {
        inboxPage.goToSpamPage(driver);
    }

    @When("^I check email at Inbox$")
    public void checkEmail() {
        inboxPage.checkEmail(driver);
    }

    @When("^I click Спам$")
    public void checkAsSpam() {
        inboxPage.addToSpam();
    }

    @When("^I check email at Spam$")
    public void checkFirstLetterInSpam() {
        spamPage.checkFirstLetterInSpam(driver);
        }

    @When("^I click Не спам$")
    public void checkAsNotASpam() {
        spamPage.restoreFromSpam();
    }

    @Then("^I see Добавлено в спам message$")
    public void confirmedAddToSpam() {
        Assert.assertTrue(inboxPage.isConfirmedToSpam(driver));
        driver.close();
    }

    @When("^I enter incorrect UserName to Login field$")
    public void enterIncorrectLogin() {
        homePage.enterLogin(incorrectLoginPassword);
        logger.info("Incorrect Login Entered");
        homePage.takeScreenShot(driver);
    }

    @When("^I enter incorrect Password to Password field$")
    public void enterIncorrectPassword() {
        homePage.enterPassword(incorrectLoginPassword);
        logger.info("Incorrect Password Entered");
    }

    @Then("^I see Error message$")
    public void iSeeErrorMessage() {
        Assert.assertNotNull(homePage.getLoginError());
        logger.info("Error message displayed");
        homePage.takeScreenShot(driver);
        driver.close();
    }

    @Given("^I am logged in (.+) page$")
    public void doLogin(String url) {
        driver.get(url);
        homePage.doLogin(login, password, domainName);
    }

    @When("^I click button Написать письмо$")
    public void goToComposePage() {
        inboxPage.goToComposePage(driver);
    }

    @When("^I enter adresse to Кому line$")
    public void enterAdressee() {
        composePage.fillAddressee(adressee);
    }

    @When("^I enter all of adresses to Кому line$")
    public void enterAllAdressees() {
        for (int i = 0; i < emails.size(); i++) {
            composePage.fillAddressee(emails.get(i).getRecepient());
            composePage.fillAddressee("; ");
        }
    }

    @When("^I enter Subject to Тема line$")
    public void enterSubject() {
        composePage.fillSubject(subject);
    }

    @When("^I enter message text to e-mail body field$")
    public void fillMailText() {
        composePage.fillMessageText(driver, mailText);
    }

    @When("^I click Отправить button$")
    public void sendEmail() {
        composePage.sendLetter();
    }

    @When("^I send confirmation message via VK API$")
    public void sendVKMessage() throws IOException, URISyntaxException {
        vk.sendMessage(apiRecepient, apiMessage);
    }

    @When("^I click выход button$")
    public void logOut(){
        inboxPage.logOut(driver);
        driver.close();
    }

    @Then("^I see confirmation message$")
    public void messageSent() {
        Assert.assertTrue(composePage.confirmationMessageDisplayed(driver));
        driver.close();
    }
    @Then("^I receive confirmation from API$")
    public void apiConfirmation() throws IOException, URISyntaxException {
        Assert.assertEquals(apiMessage, vk.getMessageText(vk.getMessageID()));
        driver.close();
    }

    @Then("^I see Письмо перемещено в папка Входящие$")
    public void confirmedRestoreFromSpam(){
        Assert.assertTrue(spamPage.isRestoredFromSpam(driver));
        driver.close();
    }

    @Then("^I see login form$")
    public void logedOut(){
        Assert.assertTrue(homePage.passwordField.isDisplayed());
    }
}