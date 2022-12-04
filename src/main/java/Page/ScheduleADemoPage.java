package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScheduleADemoPage {

    //Locator for title and form inputs
    By title = By.id("main");
    By firstName = By.id("FirstName");
    By lastName = By.id("LastName");
    
    //Method to click video play button
    /* 1. switch to iFrame
       2. click to play the video
       3. wait to play video for 30 seconds
       4. then switch out of the iFrame */
    public void clickPlay(WebDriver driver) {

        //Locator for video play button (video is in an iFrame, so need to retrieve iFrame element first)
        WebElement iframeElement = driver.findElement(By.xpath ("//*[@id=\"main\"]/main/div/div/div/section/div/div/div/div/div/div[1]/div/header/div/div[1]/div[2]/iframe"));
        By playButton = By.xpath("//button[@aria-label='Play Video: Get a Demo of Zego™ with Marissa']");

        driver.switchTo().frame(iframeElement);
        driver.findElement(playButton).click();
        try {
            Thread.sleep(12000);
        } catch(InterruptedException e) { e.toString(); }

        driver.switchTo().defaultContent();
    }

    //Method to fill out first name
    public void enterFirstName(WebDriver driver) {
        driver.findElement(firstName).sendKeys("Tahani");
        driver.findElement(lastName).sendKeys("Yosuf");
    }
}
