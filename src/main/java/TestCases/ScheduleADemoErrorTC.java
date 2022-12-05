package TestCases;
import Page.ScheduleADemoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.Properties;

public class ScheduleADemoErrorTC {
    public static void main(String[] args) {

       String scheduleADemoURL = "";
       String companyName = "";
       final String CHECKBOX_ERROR_MESSAGE = "This field is required.";
       final String ACCEPTANCE_NUM_UNITS = "100";

       //Read setup data values from a config file
       try {
        Properties properties = new Properties();
        properties.load(ScheduleADemoErrorTC.class.getClassLoader().getResourceAsStream("Configuration.properties"));
        scheduleADemoURL = properties.getProperty("scheduleADemoURL");
        companyName = properties.getProperty("companyName");
       } catch (IOException e) {}


        WebDriver driver = new ChromeDriver();
        driver.get(scheduleADemoURL);
        ScheduleADemoPage scheduleADemoPage = new ScheduleADemoPage();

        /*  Scenario: play the video, fill out the form correctly with at least 100 units
            except do not select a checkbox for Product Interest
            Expectated Outcome: Remain on the same page and see an error message 
        */
        scheduleADemoPage.clickPlay(driver);
        scheduleADemoPage.completeForm(driver, companyName, ACCEPTANCE_NUM_UNITS);
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
