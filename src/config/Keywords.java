package config;

/**
 * Created by ivanxue on 11/08/2016.
 */

import executionEngine.DriverScript;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import utility.Log;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static executionEngine.DriverScript.Objects;

public class Keywords {

    public static WebDriver driver;

    public static void openBrowser(String object, String data) throws MalformedURLException {
        Log.info("Launch " + data + " browser");
        driver = utility.Browser.getBrowser(data);
        driver.manage().window().maximize();
    }

    public static void navigate(String object, String data) {
        Log.info("Navigate browser to page " + data);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(data);
    }

    public static void click(String object, String data) {
        try {
            Log.info("Clicking on web element " + object);
            driver.findElement(By.cssSelector(Objects.getProperty(object))).click();
        } catch (Exception e) {
            Log.error("Not able to click web element " + object + " " + e.getMessage());
            DriverScript.result = false;
        }
    }

    public static void input(String object, String data) {
        try {
            Log.info("Input " + data + " in " + object);
            driver.findElement(By.cssSelector(Objects.getProperty(object))).clear();
            driver.findElement(By.cssSelector(Objects.getProperty(object))).sendKeys(data);
        } catch (Exception e) {
            Log.error("Not able to input " + data + " in " + object + " " + e.getMessage());
            DriverScript.result = false;
        }
    }

    public static void clickEnter(String object, String data) {
        try {
            Log.info("Click keyboard Enter in " + object);
            driver.findElement(By.cssSelector(Objects.getProperty(object))).sendKeys(Keys.ENTER);
        } catch (Exception e) {
            Log.error("Not able to click keyboard Enter in " + object + " " + e.getMessage());
            DriverScript.result = false;
        }
    }

    public static void select(String object, String data) {
        try {
            Log.info("Select " + data + " from " + object);
            Select List = new Select(driver.findElement(By.cssSelector(Objects.getProperty(object))));
            List.selectByValue(data);
        } catch (Exception e) {
            Log.error("Not able to select " + data + " from " + object + " " + e.getMessage());
            DriverScript.result = false;
        }
    }

    public static void waitForElement(String object, String data) throws Exception {
        try {
            Log.info("Wait for web element " + object + " to be displayed");
            WebDriverWait wait = new WebDriverWait(driver, Constants.Time_Wait);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(Objects.getProperty(object)))));
        } catch (Exception e) {
            Log.error("Web element " + object + " was not displayed in " + Constants.Time_Wait + " seconds " + e.getMessage());
            DriverScript.result = false;
        }
    }

    public static void implicityWait(String object, String data) throws Exception {
        Log.info("Wait implicitily for " + data + " million seconds");
        Thread.sleep(Long.parseLong(data));
    }

    public static void validatePageSource(String object, String data) throws Exception {
        try {
            Log.info("Check whether the page contains " + data);
            String actualText = driver.getPageSource();
            if(actualText.contains(data)) {
                Reporter.log(data + " is displayed in the page successfully");
            } else {
                Reporter.log(data + " failed to be displayed in the page");
                DriverScript.result = false;
            }
        } catch (Exception e) {
            Log.error("Not able to check the page source " + e.getMessage());
            DriverScript.result = false;
        }
    }

    public static void closeBrowser(String object, String data) {
        try {
            Log.info("Closing the browser");
            driver.quit();
        } catch (Exception e) {
            Log.error("Not able to close the browser " + e.getMessage());
            DriverScript.result = false;
        }
    }
}