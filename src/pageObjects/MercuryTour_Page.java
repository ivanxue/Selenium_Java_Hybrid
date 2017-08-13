package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utility.Log;
import utility.Utils;

public class MercuryTour_Page extends BaseClass {
	
	private static WebElement element = null;

    public MercuryTour_Page(WebDriver driver) {
        super(driver);
    }

    public static WebElement userNameInput() {
        try {
            element = driver.findElement(By.name("userName"));
            Log.info("User Name field is found on the page");
        } catch(Exception error) {
            Log.error("User Name field is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement passwordInput() {
        try {
            element = driver.findElement(By.name("password"));
            Log.info("Password field is found on the page");
        } catch(Exception error) {
            Log.error("Password field is not found on the page");
            throw (error);
        }
        return element;
    }

    public static WebElement signInBtn(WebDriver driver) {
        try {
            element = driver.findElement(By.name("login"));
            Log.info("Sign-in button is found on the page");
        } catch(Exception error) {
            Log.error("Sign-in button is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement tripTypeRadio() {
        try {
            element = driver.findElement(By.name("tripType"));
            Log.info("Trip Type radio button is found on the page");
        } catch(Exception error) {
            Log.error("Trip Type radio button is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement fromDropdown() {
        try {
            element = driver.findElement(By.name("fromPort"));
            Log.info("Depart from dropdown is found on the page");
        } catch(Exception error) {
            Log.error("Depart from dropdown is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement toDropdown() {
        try {
            element = driver.findElement(By.name("toPort"));
            Log.info("Arrive in dropdown is found on the page");
        } catch(Exception error) {
            Log.error("Arrive in dropdown is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement servClassRadio() {
        try {
            element = driver.findElement(By.name("servClass"));
            Log.info("Service Class radio button is found on the page");
        } catch(Exception error) {
            Log.error("Service Class radio button is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement continueFindBtn() {
        try {
            element = driver.findElement(By.name("findFlights"));
            Log.info("Continue button at Find Flights is found on the page");
        } catch(Exception error) {
            Log.error("Continue button at Find Flights is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement continueReserveBtn() {
        try {
            element = driver.findElement(By.name("reserveFlights"));
            Log.info("Continue button at Reserve Flights is found on the page");
        } catch(Exception error) {
            Log.error("Continue button at Reserve Flights is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement firstNameInput() {
        try {
            element = driver.findElement(By.name("passFirst0"));
            Log.info("First Name field is found on the page");
        } catch(Exception error) {
            Log.error("First Name field is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement lastNameInput() {
        try {
            element = driver.findElement(By.name("passLast0"));
            Log.info("Last Name field is found on the page");
        } catch(Exception error) {
            Log.error("Last Name field is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement creditNumberInput() {
        try {
            element = driver.findElement(By.name("creditnumber"));
            Log.info("Credit Card number field is found on the page");
        } catch(Exception error) {
            Log.error("Credit Card number is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement ticketlessTravelCheck() {
        try {
            element = driver.findElement(By.name("ticketLess"));
            Log.info("Ticketless Travel checkbox is found on the page");
        } catch(Exception error) {
            Log.error("Ticketless Travel checkbox is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement securePurchaseBtn() {
        try {
            element = driver.findElement(By.name("buyFlights"));
            Log.info("Secure Purchase button is found on the page");
        } catch(Exception error) {
            Log.error("Secure Purchase button is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement backToHomeBtn() {
        try {
            element = driver.findElement(By.xpath("//*/tbody/tr[7]/td/table/tbody/tr/td[2]/a"));
            Log.info("Back To Home button is found on the page");
        } catch(Exception error) {
            Log.error("Back To Home button is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static WebElement logoutBtn() {
        try {
            element = driver.findElement(By.xpath("//*/tbody/tr[7]/td/table/tbody/tr/td[3]/a"));
            Log.info("Log Out button is found on the page");
        } catch(Exception error) {
            Log.error("Log Out button is not found on the page");
            throw (error);
        }
        return element;
    }
    
    public static void login(String userName, String password) {    	
        userNameInput().sendKeys(userName);
        passwordInput().sendKeys(password);
        
        signInBtn(driver).click();
    }
    
    public static void findFlight(String departFrom, String arriveIn) {
        Utils.checkByValue("tripType", "oneway");
        Utils.selectByValue("fromPort", departFrom);
        Utils.selectByValue("toPort", arriveIn);
        Utils.checkByValue("servClass", "First");
        
        continueFindBtn().click();
    }
    
    public static void bookFlight(String firstName, String lastName, String creditNumber) {
        firstNameInput().sendKeys(firstName);
        lastNameInput().sendKeys(lastName);
        creditNumberInput().sendKeys(creditNumber);
        
        ticketlessTravelCheck().click();
        securePurchaseBtn().click();
    }
    
    public static void reviewFlight() {
        MercuryTour_Page.backToHomeBtn().click();
    }
    
    public static void validPageTitle(String expectedPageTitle) {
    		String strPageTitle = driver.getTitle();
        System.out.println("Actual Page title: - " + strPageTitle);
        System.out.println("Expected Page title: - " + expectedPageTitle);
        Assert.assertTrue(strPageTitle.equalsIgnoreCase(expectedPageTitle), "Page title doesn't match");
    }
    
    
}
