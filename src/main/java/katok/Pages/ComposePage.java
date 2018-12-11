package katok.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComposePage extends BasePage {
    public ComposePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//textarea[@data-original-name=\"To\"]")
    WebElement addresseeInputLine;

    @FindBy(xpath = ".//input[@name=\"Subject\"]")
    WebElement subjectLine;

    @FindBy(xpath = ".//*[@id='tinymce']")
    WebElement letterText;

    @FindBy(xpath = ".//*[@data-name=\"send\"]")
    WebElement sendLetterButton;

    @FindBy(xpath = ".//div[@class='message-sent__title']")
    WebElement confirmationMessage;

    @FindBy(xpath = "//*//*[@id=\"b-compose__sent\"]/div/div[1]/div/a[2]")
    WebElement goToInbox;

    public void fillAddressee(String addressee) {
        addresseeInputLine.sendKeys(addressee);
    }

    public void fillSubject(String subject) {
        subjectLine.sendKeys(subject);
    }

    public void fillMessageText(WebDriver driver, String messageText) {
        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe")));
        letterText.sendKeys(messageText);
        driver.switchTo().defaultContent();
   }

   public boolean confirmationMessageDisplayed(WebDriver driver){
        setExplicitWait(driver,10, confirmationMessage);
        return confirmationMessage.isDisplayed();
   }

    public void sendLetter() {
        sendLetterButton.click();
    }
}
