package TestCases;
import Page.ScheduleADemoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.Properties;

public class ScheduleADemoAcceptanceTC {
    public static void main(String[] args) {

       String scheduleADemoURL = "";
       String companyName = "";
       final String SCHEDULE_MESSAGE = "Pick a time that works best for your schedule";
       final String ACCEPTANCE_NUM_UNITS = "100";

       //Read setup data values from a config file
       try {
        Properties properties = new Properties();
        properties.load(ScheduleADemoAcceptanceTC.class.getClassLoader().getResourceAsStream("Configuration.properties"));
        scheduleADemoURL = properties.getProperty("scheduleADemoURL");
        companyName = properties.getProperty("companyName");
       } catch (IOException e) {}


        WebDriver driver = new ChromeDriver();
        driver.get(scheduleADemoURL);
        ScheduleADemoPage scheduleADemoPage = new ScheduleADemoPage();

        /*  Scenario: play the video, fill out the form correctly and with at least 100 units
            Expectated Outcome: Be redirected to the next page with a message to pick a time
            and a calender to select the date and time to schedule
        */
        //Perform steps
        scheduleADemoPage.clickPlay(driver);
        scheduleADemoPage.completeForm(driver, companyName, ACCEPTANCE_NUM_UNITS);
        scheduleADemoPage.selectProductPay(driver);
        scheduleADemoPage.clickSubmit(driver);
        //Wait for page to load
        try { Thread.sleep(10000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        //Condition to verify actual behavior is as expected
        if (!driver.getPageSource().contains(SCHEDULE_MESSAGE)) {
            System.out.println("TEST FAILED");
            driver.quit();
        }
        else if (driver.getPageSource().contains(SCHEDULE_MESSAGE)) {
            System.out.println("TEST PASSED");
            driver.quit();
        }
        else {
            System.out.println("TEST RESULT UNKNOWN");
            driver.quit();
        }
    }
}
