package TestCases;
import Page.ScheduleADemoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.Properties;

public class ScheduleADemoTC {
    public static void main(String[] args) {

       String scheduleADemoURL = "";

       //Read setup data values from a config file
       try {
        Properties properties = new Properties();
        properties.load(ScheduleADemoTC.class.getClassLoader().getResourceAsStream("Configuration.properties"));
        System.out.println(properties.getProperty("scheduleADemoURL"));
        scheduleADemoURL = properties.getProperty("scheduleADemoURL");
       } catch (IOException e) {}


        WebDriver driver = new ChromeDriver();
        driver.get(scheduleADemoURL);
        ScheduleADemoPage scheduleADemoPage = new ScheduleADemoPage();

        scheduleADemoPage.clickPlay(driver);
        scheduleADemoPage.enterFirstName(driver);
        driver.quit();
    }
}
