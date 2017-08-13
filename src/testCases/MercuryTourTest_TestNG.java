package testCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.MercuryTour_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class MercuryTourTest_TestNG {
	public static WebDriver driver;
    private String sTestCaseName;
    private int iTestCaseRowNo;
    private String sBrowserName;
    private String sUserName;
    private String sPassword;
    private String sDepartFrom;
    private String sArriveIn;
    private String sFirstName;
    private String sLastName;
    private String sCreditNumber;

    @BeforeMethod
    public void beforeMethod() throws Exception {

        DOMConfigurator.configure("log4j.xml");

        sTestCaseName = Utils.getTestCaseName(this.toString());
        Log.startTestCase(sTestCaseName);

        ExcelUtils.loadExcelFile(Constant.dataPath + Constant.dataFile);

        iTestCaseRowNo = ExcelUtils.getRowContains(sTestCaseName, Constant.Col_TestCaseName, "MercuryTour");
        sBrowserName = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_Browser, "MercuryTour");
        
        sUserName = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_UserName, "MercuryTour");
        sPassword = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_Password, "MercuryTour");
        sDepartFrom = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_DepartFrom, "MercuryTour");
        sArriveIn = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_ArriveIn, "MercuryTour");
        sFirstName = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_FirstName, "MercuryTour");
        sLastName = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_LastName, "MercuryTour");
        sCreditNumber = ExcelUtils.getCellData(iTestCaseRowNo, Constant.Col_CreditNumber, "MercuryTour");

        driver = Utils.openBrowser(iTestCaseRowNo, Constant.url,sBrowserName);

        new BaseClass(driver);

    }

    @Test
    public void main() throws Exception {

//        Utils.waitForElement(MercuryTour_Page.userNameInput());
//
//        String strPageTitle = driver.getTitle();
//        System.out.println("Page title: - " + strPageTitle);
//        Assert.assertTrue(strPageTitle.equalsIgnoreCase("Welcome: Mercury Tours"), "Page title doesn't match");
//        
//        MercuryTour_Page.userNameInput().sendKeys(sUserName);
//        MercuryTour_Page.passwordInput().sendKeys(sPassword);
//        MercuryTour_Page.signInBtn(driver).click();
    	
    	Utils.waitForElement(MercuryTour_Page.userNameInput());
    	MercuryTour_Page.validPageTitle("Welcome: Mercury Tours");
    	
    	MercuryTour_Page.login(sUserName,sPassword);
    	
    	Utils.waitForElement(MercuryTour_Page.tripTypeRadio());
    	MercuryTour_Page.validPageTitle("Find a Flight: Mercury Tours:");
    	
    	MercuryTour_Page.findFlight(sDepartFrom, sArriveIn);
    	
    	Utils.waitForElement(MercuryTour_Page.continueReserveBtn());
    MercuryTour_Page.validPageTitle("Select a Flight: Mercury Tours");
    
    MercuryTour_Page.continueReserveBtn().click();
    	
    Utils.waitForElement(MercuryTour_Page.firstNameInput());
    MercuryTour_Page.validPageTitle("Book a Flight: Mercury Tours");
    
    	MercuryTour_Page.bookFlight(sFirstName, sLastName, sCreditNumber);
    	
    	Utils.waitForElement(MercuryTour_Page.backToHomeBtn());
    	MercuryTour_Page.validPageTitle("Flight Confirmation: Mercury Tours");
    	
    	MercuryTour_Page.reviewFlight();
    	
        
        
//        Utils.waitForElement(MercuryTour_Page.tripTypeRadio());
//        
//        Utils.checkByValue("tripType", "oneway");
//        Utils.selectByValue("fromPort", sDepartFrom);
//        Utils.selectByValue("toPort", sArriveIn);
//        Utils.checkByValue("servClass", "First");
//        
//        MercuryTour_Page.continueFindBtn().click();
//        
//        Utils.waitForElement(MercuryTour_Page.continueReserveBtn());
//        
//        strPageTitle = driver.getTitle();
//        System.out.println("Page title: - " + strPageTitle);
//        Assert.assertTrue(strPageTitle.equalsIgnoreCase("Select a Flight: Mercury Tours"), "Page title doesn't match");
     
//        MercuryTour_Page.continueReserveBtn().click();;
//        Utils.waitForElement(MercuryTour_Page.firstNameInput());
//        
//        strPageTitle = driver.getTitle();
//        System.out.println("Page title: - " + strPageTitle);
//        Assert.assertTrue(strPageTitle.equalsIgnoreCase("Book a Flight: Mercury Tours"), "Page title doesn't match");
//        
//        MercuryTour_Page.firstNameInput().sendKeys(sFirstName);
//        MercuryTour_Page.lastNameInput().sendKeys(sLastName);
//        MercuryTour_Page.creditNumberInput().sendKeys(sCreditNumber);
//        
//        MercuryTour_Page.ticketlessTravelCheck().click();
//        MercuryTour_Page.securePurchaseBtn().click();
//        Utils.waitForElement(MercuryTour_Page.backToHomeBtn());
//        
//        strPageTitle = driver.getTitle();
//        System.out.println("Page title: - " + strPageTitle);
//        Assert.assertTrue(strPageTitle.equalsIgnoreCase("Flight Confirmation: Mercury Tours"), "Page title doesn't match");
//        
//        MercuryTour_Page.backToHomeBtn().click();
    }

    @AfterMethod
    public static void tearDown() throws Exception {
        if (driver != null) {
            System.out.println("Closing browser");
            driver.quit();
        }
    }
}
