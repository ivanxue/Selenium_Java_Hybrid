package utility;

/**
 * Created by ivanxue on 3/08/2016.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Browser {

    public static WebDriver driver;
    public static final String KEY = "868c9ff90ed032ecfa27bd48ed89844a";
    public static final String SECRET = "af37e9afa932c9cb40133a7d28bc4ab0";
    public static final String URL = "http://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";

    public static DesiredCapabilities caps = new DesiredCapabilities();


    public static WebDriver getBrowser(String browserType) throws MalformedURLException {
        switch (browserType) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", Constant.driverPath + "geckodriver");
                return	driver = new FirefoxDriver();
            case "chrome":
                System.setProperty("webdriver.chrome.driver", Constant.driverPath + "chromedriver");
                return	driver = new ChromeDriver();
            case "safari":
                return	driver = new SafariDriver();
            case "IE":
                System.setProperty("webdriver.ie.driver", Constant.driverPath + "IEDriverServer.exe");
                return	driver = new InternetExplorerDriver();
            case "Remote":
                caps.setCapability("browserName", "IE");
                caps.setCapability("version", "11");
                caps.setCapability("platform", "WIN10");

                return driver = new RemoteWebDriver(new URL(URL), caps);
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
                return driver = new FirefoxDriver();
        }
    }
}