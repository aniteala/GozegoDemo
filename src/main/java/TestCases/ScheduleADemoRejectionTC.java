package TestCases;
import Page.ScheduleADemoPage;
import Setup.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScheduleADemoRejectionTC {
    public static void main(String[] args) {
        
       //Constants 
       final String SCHEDULE_DEMO_URL = "scheduleADemoURL";
       final String COMPANY_NAME = "companyName";
       final String THANK_YOU_MESSAGE = "Thank you for your interest in Zego™!";
       final String REJECTION_NUM_UNITS = "30";

       //Read setup data values from a config file
       LoadProperties loadProperties = new LoadProperties();

        WebDriver driver = new ChromeDriver();
        driver.get(loadProperties.getConfigProperty(SCHEDULE_DEMO_URL));
        ScheduleADemoPage scheduleADemoPage = new ScheduleADemoPage();

        /*  Scenario: play the video, fill out the form correctly but with less than 100 units
            Expectated Outcome: Be redirected to the next page with the thank you message and
            contact information for further assistance
        */
        //Perform steps
        //scheduleADemoPage.clickPlay(driver);
        scheduleADemoPage.completeForm(driver, loadProperties.getConfigProperty(COMPANY_NAME), REJECTION_NUM_UNITS);
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
