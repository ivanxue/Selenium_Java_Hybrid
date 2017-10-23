package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static WebDriver driver = null;

    public static WebDriver openBrowser(int iTestCaseRowNo, String URL, String browserName) throws Exception {
        try {

            driver = Browser.getBrowser(browserName);
            Log.info("New driver instantiated");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Log.info("Implicit wait applied on the driver for 20 seconds");

//            driver.manage().window().maximize();

            driver.get(URL);
            Log.info("Navigate to " + URL + " successfully");

        } catch (Exception error) {
            Log.error("Class Utils | Method OpenBrowser | Exception desc : " + error.getMessage());
        }
        return driver;
    }

    public static void waitForElement(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public static void checkByValue(String name, String value) {
    		List<WebElement> selectOptions = driver.findElements(By.name(name));
    		int iSize = selectOptions.size();
    		 // Start the loop from first option to last option
    		 for(int i=0; i < iSize ; i++ ){    		 
    			 String sValue = selectOptions.get(i).getAttribute("value");
    			 if (sValue.equalsIgnoreCase(value)){
    				 selectOptions.get(i).click();
    				 break;
    				 }
    			}
    }
    
    public static void selectByValue(String name, String value) {
		Select selectOptions = new Select(driver.findElement(By.name(name)));
		//selectOptions.deselectAll();
		selectOptions.selectByValue(value);
}

    public static String getTestCaseName(String sTestCase) throws Exception {
        try {
            int index = sTestCase.indexOf("@");
            String sTestCasePath = sTestCase.substring(0, index);
            index = sTestCasePath.lastIndexOf(".");
            String sTestCaseName = sTestCasePath.substring(index + 1);
            return sTestCaseName;
        } catch (Exception error) {
            Log.error("Class Utils | Method getTestCaseName | Exception desc : " + error.getMessage());
            throw (error);
        }
    }

    public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(Constant.Path_ScreenShot + sTestCaseName + ".jpg"));
        } catch (Exception error) {
            Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : " + error.getMessage());
            throw new Exception();
        }
    }
}