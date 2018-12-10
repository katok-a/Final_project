package katok.Steps;

import cucumber.api.java.en.When;
import katok.Pages.ComposePage;

public class ComposePageSteps extends BaseSteps{
    ComposePage composePage = new ComposePage(driver);
    private String adressee = "testUser";
    private String subject = "testSubject";
    private String mailText = "testText";


    @When("^I enter adresse to Кому line$")
    public void enterAdressee(){
        composePage.fillAddressee(adressee);
    }

    @When("^I enter Subject to Тема line$")
    public void enterSubject(){
        composePage.fillSubject(subject);
    }

    @When("^I enter message text to e-mail body field$")
    public void fillMailText(){
        composePage.fillMessageText(driver, mailText);
    }
}
