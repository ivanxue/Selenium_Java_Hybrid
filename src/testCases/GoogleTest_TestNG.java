package testCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.BaseClass;
import pageObjects.Google_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

/**
 * Created by ivanxue on 3/08/2016.
 */
public class GoogleTest_TestNG {
    public static WebDriver driver;
    private String sTestCaseName;
    private int iTestCaseRowNo;
    private String sBrowserName;

    @BeforeMethod
    public void beforeMethod() throws Exception {

        DOMConfigurator.configure("log4j.xml");

        sTestCaseName = Utils.getTestCaseName(this.toString());
        Log.startTestCase(sTestCaseName);

        ExcelUtils.loadExcelFile(Constant.dataPath + Constant.dataFile);

        iTestCaseRowNo = ExcelUtils.getRowContains(sTestCaseName, Constant.Col_TestCaseName, "Google");
        sBrowserName = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_Browser, "Google");

        driver = Utils.openBrowser(iTestCaseRowNo, "http://www.google.com",sBrowserName);

        new BaseClass(driver);

    }

    @Test
    public void main() throws Exception {

        Utils.waitForElement(Google_Page.keywordField());

        String strPageTitle = driver.getTitle();
        System.out.println("Page title: - " + strPageTitle);
        Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");

        String sSearchKeyword = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_SearchKeyword, "Google");
        Google_Page.keywordField().sendKeys(sSearchKeyword);
        //Google_Page.searchBtn(driver).click();
        Google_Page.keywordField().sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        Assert.assertTrue(driver.getPageSource().contains(sSearchKeyword), "Search result doesn't match");
    }

    @AfterMethod
    public static void tearDown() throws Exception {
        if (driver != null) {
            System.out.println("Closing browser");
            driver.quit();
        }
    }
}
