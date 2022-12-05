package TestCases;
import Page.ScheduleADemoPage;
import Setup.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScheduleADemoErrorTC {
    public static void main(String[] args) {

       //Constants 
       final String SCHEDULE_DEMO_URL = "scheduleADemoURL";
       final String COMPANY_NAME = "companyName"; 
       final String CHECKBOX_ERROR_MESSAGE = "This field is required.";
       final String ACCEPTANCE_NUM_UNITS = "100";

       //Read setup data values from a config file
       LoadProperties loadProperties = new LoadProperties();

        WebDriver driver = new ChromeDriver();
        driver.get(loadProperties.getConfigProperty(SCHEDULE_DEMO_URL));
        ScheduleADemoPage scheduleADemoPage = new ScheduleADemoPage();

        /*  Scenario: play the video, fill out the form correctly with at least 100 units
            except do not select a checkbox for Product Interest
            Expectated Outcome: Remain on the same page and see an error message 
        */
        //scheduleADemoPage.clickPlay(driver);
        scheduleADemoPage.completeForm(driver, loadProperties.getConfigProperty(COMPANY_NAME), ACCEPTANCE_NUM_UNITS);
        scheduleADemoPage.clickSubmit(driver);

        //Assigning the error message to a string
        String checkboxErrorMessage = scheduleADemoPage.getCheckboxErrorMessage(driver);

        //Condition to verify actual behavior is as expected
        if (!checkboxErrorMessage.equals(CHECKBOX_ERROR_MESSAGE)) {
            System.out.println("TEST FAILED");
            driver.quit();
        }
        else if (checkboxErrorMessage.equals(CHECKBOX_ERROR_MESSAGE)) {
            System.out.println("TEST PASSED");
            driver.quit();
        }
        else {
            System.out.println("TEST RESULT UNKNOWN");
            driver.quit();
        }
    }
}
