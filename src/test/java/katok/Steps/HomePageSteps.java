package katok.Steps;

import cucumber.api.java.en.Given;
import katok.Core.SQL.SqlDataMiner;
import katok.Pages.ComposePage;
import org.openqa.selenium.support.ui.FluentWait;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import katok.Core.WebDriver.DriverFactory;
import katok.Pages.HomePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;
import java.util.function.Function;

public class HomePageSteps {
    private WebDriver driver = DriverFactory.getDriver("chrome");
    private HomePage homePage = new HomePage(driver);
    private ComposePage composePage = new ComposePage(driver);
    SqlDataMiner dataMiner = SqlDataMiner.getMiner();
    private String login = dataMiner.getFieldByColumn("login");
    private String password = dataMiner.getFieldByColumn("password");
    private String domainName = dataMiner.getFieldByColumn("domain");
    private String incorrectLoginPassword = "!@#$%";
    private String adressee = "testUser";
    private String subject = "testSubject";
    private String mailText = "testText";


    public HomePageSteps() throws SQLException {
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
    public void goToComposePage(){
       homePage.goToComposePage();
    }

    @When("^I enter adresse to Кому line$")
    public void enterAdressee(String adressee){
        composePage.fillAddressee(adressee);
    }

    @When("^I enter Subject to Тема line$")
    public void enterSubject(String subject){
        composePage.fillSubject(subject);
    }

    @When("^I enter message text to e-mail body field$")
    public void fillMailText(){
        composePage.fillMessageText(driver, mailText);
    }



}
