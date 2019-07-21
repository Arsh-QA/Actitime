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
		String file = "./testData/testdata.xlsx";
		String username = ExcelUtilities.readData(file, "Sheet1", 1, 1);
		String password = ExcelUtilities.readData(file, "Sheet1", 1, 2);
		logger.log(LogStatus.INFO, "Data from excel is red");
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		logger.log(LogStatus.INFO, "Login successful");

		EnterTimeTrackPage ettp = new EnterTimeTrackPage(driver);
		ettp.verifyHomePage();
		logger.log(LogStatus.INFO, "Home page WebElements verified");
		logger.log(LogStatus.PASS, "Test case passed");
	}

	@Test(priority = 2, description ="Test case 2 : To check the functionality of Login page: Invalid login", enabled=true)
	public void invalidLogin()
	{
		String file = "./testData/testdata.xlsx";
		String username = ExcelUtilities.readData(file, "Sheet1", 2, 1);
		String password = ExcelUtilities.readData(file, "Sheet1", 2, 2);
		logger.log(LogStatus.INFO, "Data from excel is red");
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		logger.log(LogStatus.INFO, "Invalid Login credentials entered");
		lp.verifyInvalidLogin();
		logger.log(LogStatus.INFO, "Error message verified");
		logger.log(LogStatus.PASS, "Test case passed");
	}
}