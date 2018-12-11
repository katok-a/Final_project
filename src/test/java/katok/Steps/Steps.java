package katok.Steps;

import cucumber.api.java.en.Given;
import katok.Core.API.VkApiHandler;
import katok.Core.SQL.SqlDataMiner;
import katok.Core.XML.XMLDataMiner;
import katok.Core.XML.eMailsModel;
import katok.Pages.ComposePage;
import org.openqa.selenium.support.ui.FluentWait;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import katok.Core.WebDriver.DriverFactory;
import katok.Pages.HomePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class Steps {
    private WebDriver driver = DriverFactory.getDriver("chrome");
    private HomePage homePage = new HomePage(driver);
    private ComposePage composePage = new ComposePage(driver);

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
    private String apiMessage = "You have new email at "+login+domainName;
    private int apiRecepient = emails.get(eMailsRecepientPosition).getId();

    public Steps() throws SQLException, ParserConfigurationException, SAXException, IOException {
    }

    @When("^I am on (.+) page$")
    public void loadHomePage(String url) {
        driver.get(url);
    }

    @When("^I enter correct UserName to Login field$")
    public void enterLogin() {
        homePage.enterLogin(login);
    }

    @When("^I enter correct Password to Password field$")
    public void enterPassword() {
        homePage.enterPassword(password);
    }

    @When("^I choose correct domain name from dropdown$")
    public void choseDomain() {
        homePage.selectDomain(domainName);
    }

    @When("^I click Войти$")
    public void clickSubmitButton() {
        homePage.pushSubmitButton();
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
        driver.close();
    }

    @When("^I enter incorrect UserName to Login field$")
    public void enterIncorrectLogin() {
        homePage.enterLogin(incorrectLoginPassword);
    }

    @When("^I enter incorrect Password to Password field$")
    public void enterIncorrectPassword() {
        homePage.enterPassword(incorrectLoginPassword);
    }

    @Then("^I see Error message$")
    public void iSeeErrorMessage() {
        Assert.assertNotNull(homePage.getLoginError());
        driver.close();
    }

    @Given("^I am logged in (.+) page$")
    public void doLogin(String url) {
        driver.get(url);
        homePage.doLogin(login, password, domainName);
    }

    @When("^I click button Написать письмо$")
    public void goToComposePage() {
        homePage.goToComposePage(driver);
    }

    @When("^I enter adresse to Кому line$")
    public void enterAdressee() {
        composePage.fillAddressee(adressee);
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
    public void sendEmail(){composePage.sendLetter();}

    @Then("^I see confirmation message$")
    public void messageSent(){
        Assert.assertTrue(composePage.confirmationMessageDisplayed(driver));
    }

    @When ("^I send confirmation message via VK API$")
    public void sendVKMessage() throws IOException, URISyntaxException {
    vk.sendMessage(apiRecepient,apiMessage);
    }

    @Then("^I receive confirmation from API$")
    public void apiConfirmation() throws IOException, URISyntaxException {
        Assert.assertEquals(apiMessage, vk.getMessageText(vk.getMessageID()));
    }
}