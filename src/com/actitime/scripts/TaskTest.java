package com.actitime.scripts;

import org.testng.annotations.Test;

import com.actitime.generic.BaseLib;
import com.actitime.generic.ExcelUtilities;
import com.actitime.pageobjects.ActiveProjNCustPage;
import com.actitime.pageobjects.CreateNewCustPage;
import com.actitime.pageobjects.CreateNewProjPage;
import com.actitime.pageobjects.EditCustInfoPage;
import com.actitime.pageobjects.EditProjInfoPage;
import com.actitime.pageobjects.EnterTimeTrackPage;
import com.actitime.pageobjects.LoginPage;
import com.actitime.pageobjects.OpenTasksPage;

public class TaskTest extends BaseLib 
{

	@Test(description="Test case 3 : To check the functionality of Create Customer.")
	public void createCustomer()
	{
		String file = "./testData/testdata.xlsx";
		String username = ExcelUtilities.readData(file, "Sheet1", 3, 1);
		String password = ExcelUtilities.readData(file, "Sheet1", 3, 2);
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);

		EnterTimeTrackPage ettp = new EnterTimeTrackPage(driver);
		ettp.clickOnTasks();

		OpenTasksPage otp = new OpenTasksPage(driver);
		otp.clickOnProjCustLink();

		ActiveProjNCustPage apcp = new ActiveProjNCustPage(driver);
		apcp.clickOnCreateNewCustBtn();


		String customerName = ExcelUtilities.readData(file, "Sheet1", 3, 3);
		CreateNewCustPage cncp = new CreateNewCustPage(driver);
		cncp.createCustomer(customerName);

		apcp.verifyCreateCustomer(customerName);
		apcp.clickOnLogout();
	}

	@Test(dependsOnMethods={"deleteProject"}, description="Test case 4 : To check the functionality of delete Customer")
	public void deleteCustomer()
	{
		String file = "./testData/testdata.xlsx";
		String username = ExcelUtilities.readData(file, "Sheet1", 4, 1);
		String password = ExcelUtilities.readData(file, "Sheet1", 4, 2);
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);

		EnterTimeTrackPage ettp = new EnterTimeTrackPage(driver);
		ettp.clickOnTasks();

		OpenTasksPage otp = new OpenTasksPage(driver);
		otp.clickOnProjCustLink();

		String customerName = ExcelUtilities.readData(file, "Sheet1", 4, 3);
		ActiveProjNCustPage apcp = new ActiveProjNCustPage(driver);
		apcp.showCustomer(customerName);

		EditCustInfoPage ecip = new EditCustInfoPage(driver);
		ecip.deleteCustomer();

		apcp.verifyDeleteCustomer();

		apcp.clickOnLogout();
	}

	@Test(dependsOnMethods={"createCustomer"}, description ="Test case 5 : To check the functionality of Create Project")
	public void createProject()
	{
		String file = "./testData/testdata.xlsx";
		String username = ExcelUtilities.readData(file, "Sheet1", 5, 1);
		String password = ExcelUtilities.readData(file, "Sheet1", 5, 2);
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);

		EnterTimeTrackPage ettp = new EnterTimeTrackPage(driver);
		ettp.clickOnTasks();

		OpenTasksPage otp = new OpenTasksPage(driver);
		otp.clickOnProjCustLink();

		ActiveProjNCustPage apcp = new ActiveProjNCustPage(driver);
		apcp.clickOnCreateNewProjBtn();

		String customerName = ExcelUtilities.readData(file, "Sheet1", 5, 3);
		String projectName = ExcelUtilities.readData(file, "Sheet1", 5, 4);
		CreateNewProjPage cnpp = new CreateNewProjPage(driver);
		cnpp.createProject(customerName, projectName);

		apcp.verifyCreateProject(projectName);

		apcp.clickOnLogout();

	}

	@Test(dependsOnMethods={"createProject"}, description ="Test case 6 : To check the functionality of Delete Project")
	public void deleteProject()
	{
		String file = "./testData/testdata.xlsx";
		String username = ExcelUtilities.readData(file, "Sheet1", 6, 1);
		String password = ExcelUtilities.readData(file, "Sheet1", 6, 2);
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);

		EnterTimeTrackPage ettp = new EnterTimeTrackPage(driver);
		ettp.clickOnTasks();

		OpenTasksPage otp = new OpenTasksPage(driver);
		otp.clickOnProjCustLink();

		String projectName = ExcelUtilities.readData(file, "Sheet1", 6, 4);
		ActiveProjNCustPage apcp = new ActiveProjNCustPage(driver);
		apcp.clickProjName(projectName);

		EditProjInfoPage epip = new EditProjInfoPage(driver);
		epip.confirmDeleteProj();

		apcp.verifyDeleteProject();

		apcp.clickOnLogout();
	}
}