package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScheduleADemoPage {

    //Locators for title, form inputs, and other web elements
    By title = By.id("main");
    By firstName = By.id("FirstName");
    By lastName = By.id("LastName");
    By email = By.id("Email");
    By phoneNum = By.id("Phone");
    By companyName = By.id("Company");
    By numUnits = By.id("Units_Opportunity__c");
    By submitButton = By.className("mktoButton");
    By productCheckboxPay = By.id("LblmktoCheckbox_9901_0"); 
    By missingCheckboxError = By.id("ValidMsgProduct_Interest__c");
    

    //Method to click video play button
    /* 1. switch to iFrame
       2. click to play the video
       3. wait to play video for a few seconds
       4. then switch out of the iFrame */
    public void clickPlay(WebDriver driver) {

        //Locator for video play button (video is in an iFrame, so need to retrieve iFrame element first)
        WebElement iframeElement = driver.findElement(By.xpath ("//*[@id=\"main\"]/main/div/div/div/section/div/div/div/div/div/div[1]/div/header/div/div[1]/div[2]/iframe"));
        By playButton = By.xpath("//button[@aria-label='Play Video: Get a Demo of Zego™ with Marissa']");

        driver.switchTo().frame(iframeElement);
        driver.findElement(playButton).click();
        try {
            Thread.sleep(6000);
        } catch(InterruptedException e) { e.toString(); }

        driver.switchTo().defaultContent();
    }

    //Method to fill out form
    public void completeForm(WebDriver driver, String nameOfCompany, String numberOfUnits) {
        driver.findElement(firstName).sendKeys("Tahani");
        driver.findElement(lastName).sendKeys("Yosuf");
        driver.findElement(email).sendKeys("tahaniyosuf@gmail.com");
        driver.findElement(phoneNum).sendKeys("5712762801");
        driver.findElement(companyName).sendKeys(nameOfCompany);
        driver.findElement(numUnits).sendKeys(numberOfUnits);
    }

    //Method to click the Product Interest-Pay checkbox
    public void selectProductPay(WebDriver driver) {
        driver.findElement(productCheckboxPay).click();
    }

    //Method to click 'submit & Pick a Time' button
    public void clickSubmit(WebDriver driver) {
        driver.findElement(submitButton).click();
    }

    //Method to retrieve the missing checkbox entry error message 
    public String getCheckboxErrorMessage(WebDriver driver) {
        return driver.findElement(missingCheckboxError).getText();
    }
}
