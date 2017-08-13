package utility;

import config.Constants;
import executionEngine.DriverScript;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtils {

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
    public static void loadExcelFile(String sFilePath) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(sFilePath);

            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            //ExcelWSheet = ExcelWBook.getSheet(sSheetName);
        } catch (Exception error) {
            throw (error);
        }
    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int intRowNo, int intColNum, String sSheetName) throws Exception {
        try {
            ExcelWSheet = ExcelWBook.getSheet(sSheetName);
            Cell = ExcelWSheet.getRow(intRowNo).getCell(intColNum);

            String sCellData = Cell.getStringCellValue();

            return sCellData;
        } catch (Exception error) {
            return "";
        }
    }

    public static void setCellData(String Result,  int RowNum, int ColNum, String sSheetName) throws Exception    {
        try{

            ExcelWSheet = ExcelWBook.getSheet(sSheetName);
            Row  = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestPlan);
            ExcelWBook.write(fileOut);
            //fileOut.flush();
            fileOut.close();
            ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestPlan));
        }catch(Exception e){
            DriverScript.result = false;
        }
    }

    public static int getRowCount(String sSheetName) {
        ExcelWSheet = ExcelWBook.getSheet(sSheetName);
        int number = ExcelWSheet.getLastRowNum() + 1;
        return number;
    }

    public static int getRowContains(String sTestCaseName, int intColNum, String sSheetName) throws Exception {
        int i;
        ExcelWSheet = ExcelWBook.getSheet(sSheetName);
        try {
            int intRowCount = ExcelUtils.getRowUsed();

            for (i = 0; i < intRowCount; i++) {
                if (ExcelUtils.getCellData(i, intColNum, sSheetName).equalsIgnoreCase(sTestCaseName)) {
                    break;
                }
            }
            return i;
        } catch (Exception error) {
            Log.error("Class ExcelUtil | Method getRowContains | Exception desc : " + error.getMessage());
            throw (error);
        }
    }

    public static int getTestStepsCount(String sSheetName, String sTestCaseID, int iTestCaseStart) throws Exception {
        for (int i = iTestCaseStart; i <= ExcelUtils.getRowCount(sSheetName); i++) {
            if (!sTestCaseID.equals(ExcelUtils.getCellData(i, 0, sSheetName))) {
                int number = i;
                return number;
            }
        }
        ExcelWSheet = ExcelWBook.getSheet(sSheetName);
        int number = ExcelWSheet.getLastRowNum() + 1;
        return number;
    }

    public static int getRowUsed() throws Exception {
        try {
            int intRowCount = ExcelWSheet.getLastRowNum();
            Log.info("Total number of Row used return as < " + intRowCount + " >.");
            return intRowCount;
        } catch (Exception error) {
            Log.error("Class ExcelUtil | Method getRowUsed | Exception desc : " + error.getMessage());
            System.out.println(error.getMessage());
            throw (error);
        }
    }
}