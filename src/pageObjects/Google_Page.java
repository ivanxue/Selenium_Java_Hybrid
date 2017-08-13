package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.Log;

/**
 * Created by ivanxue on 4/08/2016.
 */
public class Google_Page extends BaseClass {
    private static WebElement element = null;

    public Google_Page(WebDriver driver) {
        super(driver);
    }

    public static WebElement keywordField() {
        try {
            element = driver.findElement(By.xpath("//*[@id=\"lst-ib\"]"));
            Log.info("Search keyword field is found on the page");
        } catch(Exception error) {
            Log.error("Search keyword field is not found on the page");
            throw (error);
        }
        return element;
    }

    public static WebElement searchBtn(WebDriver driver) {
        try {
            element = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]"));
            Log.info("Search button is found on the page");
        } catch(Exception error) {
            Log.error("Search button is not found on the page");
            throw (error);
        }
        return element;
    }
}