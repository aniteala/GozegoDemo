package TestCases;
import Page.ScheduleADemoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.Properties;

public class ScheduleADemoRejectionTC {
    public static void main(String[] args) {
        
       String scheduleADemoURL = "";
       String companyName = "";
       final String THANK_YOU_MESSAGE = "Thank you for your interest in Zego™!";
       final String REJECTION_NUM_UNITS = "30";

       //Read setup data values from a config file
       try {
        Properties properties = new Properties();
        properties.load(ScheduleADemoRejectionTC.class.getClassLoader().getResourceAsStream("Configuration.properties"));
        scheduleADemoURL = properties.getProperty("scheduleADemoURL");
        companyName = properties.getProperty("companyName");
       } catch (IOException e) {}


        WebDriver driver = new ChromeDriver();
        driver.get(scheduleADemoURL);
        ScheduleADemoPage scheduleADemoPage = new ScheduleADemoPage();

        /*  Scenario: play the video, fill out the form correctly but with less than 100 units
            Expectated Outcome: Be redirected to the next page with the thank you message and
            contact information for further assistance
        */
        //Perform steps
        scheduleADemoPage.clickPlay(driver);
        scheduleADemoPage.completeForm(driver, companyName, REJECTION_NUM_UNITS);
        scheduleADemoPage.selectProductPay(driver);
        scheduleADemoPage.clickSubmit(driver);
        //Wait for page to load
        try { Thread.sleep(10000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        //Condition to verify actual behavior is as expected
        if (!driver.getPageSource().contains(THANK_YOU_MESSAGE)) {
            System.out.println("TEST FAILED");
            driver.quit();
        }
        else if (driver.getPageSource().contains(THANK_YOU_MESSAGE)) {
            System.out.println("TEST PASSED");
            driver.quit();
        }
        else {
            System.out.println("TEST RESULT UNKNOWN");
            driver.quit();
        }
    }
}
