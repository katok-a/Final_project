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

    @FindBy(xpath = "//*[@id=\"b-toolbar__right\"]/div[4]/div/div[2]/div[1]/div/span")
    WebElement sendLetterButton;

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

    public void sendLetter(WebDriver driver) {
        sendLetterButton.click();
    }
}
