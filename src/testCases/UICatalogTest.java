package testCases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * <a href="https://github.com/appium/appium">Appium</a> test which runs against a local Appium instance deployed
 * with the 'UICatalog' iPhone project which is included in the Appium source distribution.
 *
 * @author Ross Rowe
 *
 * Running below Test Cases on Simulator needs few steps as below
 * Unzip UICatalog.zip and build for simulator per below blog
 * http://samwize.com/2015/03/11/xcode-commands-to-build-app-and-run-on-simulator/
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UICatalogTest {

    private AppiumDriver<IOSElement> driver;

    @Before
    public void setUp() throws Exception {

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "app");
        File app = new File(appDir, "UICatalog.app");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("app", app.getAbsolutePath());

        driver = new IOSDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void step1() throws Exception {
        //first view in UICatalog is a table
        IOSElement table = driver.findElementByClassName("UIATableView");
        assertNotNull(table);
        //is number of cells/rows inside table correct
        List<MobileElement> rows = table.findElementsByClassName("UIATableCell");
        assertEquals(18, rows.size());
        //is first one about buttons
        assertEquals("Action Sheets", rows.get(0).getAttribute("name"));

        rows.get(0).click();
        List<MobileElement> msgs = driver.findElementByClassName("UIATableView").findElementsByClassName("UIATableCell");
        assertEquals("Okay / Cancel", msgs.get(0).getAttribute("name"));
    }
}