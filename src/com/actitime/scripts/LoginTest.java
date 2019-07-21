package com.actitime.scripts;

import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.actitime.generic.BaseLib;
import com.actitime.generic.ExcelUtilities;
import com.actitime.pageobjects.EnterTimeTrackPage;
import com.actitime.pageobjects.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseLib 
{
	@Test(priority = 1, description = "Test case 1 : To check the functionality of Login page", enabled=true)
	public void validLogin()
	{
		logger = extent.startTest("validLogin");
		String file = "./testData/testdata.xlsx";
		String username = ExcelUtilities.readData(file, "Sheet1", 1, 1);
		String password = ExcelUtilities.readData(file, "Sheet1", 1, 2);
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		
		EnterTimeTrackPage ettp = new EnterTimeTrackPage(driver);
		ettp.verifyHomePage();
		logger.log(LogStatus.PASS, "Test Case passed is ");
	}

	@Test(priority = 2, description ="Test case 2 : To check the functionality of Login page: Invalid login", enabled=false)
	public void invalidLogin()
	{
		String file = "./testData/testdata.xlsx";
		String username = ExcelUtilities.readData(file, "Sheet1", 2, 1);
		String password = ExcelUtilities.readData(file, "Sheet1", 2, 2);
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		lp.verifyInvalidLogin();
	}
}