package katok.Steps;


import cucumber.api.java.en.When;
import katok.Pages.InboxPage;

public class InboxPageSteps extends BaseSteps {
    private InboxPage inboxPage = new InboxPage(driver);

    @When("^I click button Написать письмо$")
    public void goToComposePage(){
        inboxPage.goToComposePage(driver);
    }
}
