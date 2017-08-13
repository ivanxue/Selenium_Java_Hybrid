package utility;

import java.io.File;

/**
 * Created by ivanxue on 29/07/2016.
 */
public class Constant {

    public static File rootDir = new File(System.getProperty("user.dir"));

    public static final String dataPath = rootDir + "//test-data//";
    public static final String dataFile = "TestData.xlsx";

    public static final String driverPath = rootDir + "//drivers//";
    public static final String Path_ScreenShot = rootDir + "//Screenshots//";
    public static final String url = "http://newtours.demoaut.com/mercurypurchase.php" ;

    //Test Data Sheet Columns
    public static final int Col_TestCaseName = 0;
    public static final int Col_Browser = 1;
    public static final int Col_UserName = 2;
    public static final int Col_SearchKeyword = 2;
    public static final int Col_Password = 3;
    public static final int Col_DepartFrom = 4;
    public static final int Col_ArriveIn = 5;
    public static final int Col_FirstName = 6;
    public static final int Col_LastName = 7;
    public static final int Col_CreditNumber = 8;
    public static final int Col_Result = 9;
}