package executionEngine;

/**
 * Created by ivanxue on 11/08/2016.
 */

import config.Constants;
import config.Keywords;
import org.apache.log4j.xml.DOMConfigurator;
import utility.ExcelUtils;
import utility.Log;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import static config.Keywords.driver;

public class DriverScript {

    public static Properties Objects;
    public static Keywords Keywords;
    public static String keyword;
    public static String object;
    public static Method Method[];
    public static int testStep;
    public static int lastTestStep;
    public static String testCaseID;
    public static String runMode;
    public static String inputData;
    public static boolean result;

    public DriverScript() throws NoSuchMethodException, SecurityException {
        Keywords = new Keywords();
        Method = Keywords.getClass().getMethods();
    }

    public static void main(String[] args) throws Exception {
        ExcelUtils.loadExcelFile(Constants.Path_TestPlan);

        DOMConfigurator.configure("log4j.xml");

        String objectsPath = Constants.Path_Objects;
        FileInputStream fs = new FileInputStream(objectsPath);
        Objects = new Properties(System.getProperties());
        Objects.load(fs);

        DriverScript startEngine = new DriverScript();
        startEngine.execute_TestCase();
    }

    private void execute_TestCase() throws Exception {
        int totalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
        for (int i = 1; i < totalTestCases; i++) {
            result = true;
            testCaseID = ExcelUtils.getCellData(i, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
            runMode = ExcelUtils.getCellData(i, Constants.Col_RunMode, Constants.Sheet_TestCases);
            if (runMode.equals("Yes")) {
                testStep = ExcelUtils.getRowContains(testCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
                lastTestStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, testCaseID, testStep);
                Log.startTestCase(testCaseID);
                result = true;
                for (; testStep < lastTestStep; testStep++) {
                    keyword = ExcelUtils.getCellData(testStep, Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
                    object = ExcelUtils.getCellData(testStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
                    //Now we will use the data value and pass it to the methods
                    inputData = ExcelUtils.getCellData(testStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);
                    execute_TestSteps();
                    if (result == false) {
                        ExcelUtils.setCellData(Constants.KEYWORD_FAIL, i, Constants.Col_Result, Constants.Sheet_TestCases);
                        Log.endTestCase();
                        break;
                    }
                }
                if (result == true) {
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, i, Constants.Col_Result, Constants.Sheet_TestCases);
                    Log.endTestCase();
                }
            }
        }
        if (driver != null) {
            System.out.println("Closing browser");
            driver.quit();
        }
    }

    private static void execute_TestSteps() throws Exception {
        for (int i = 0; i < Method.length; i++) {
            if (Method[i].getName().equals(keyword)) {
                //This code will pass three parameters to every invoke method
                Method[i].invoke(Keywords, object, inputData);
                if (result == true) {
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, testStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                    break;
                } else {
                    ExcelUtils.setCellData(Constants.KEYWORD_FAIL, testStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                    break;
                }
            }
        }
    }
}