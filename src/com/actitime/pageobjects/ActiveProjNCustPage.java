package com.actitime.pageobjects;

import java.text.MessageFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.BaseLib;
//import com.actitime.generic.ExcelUtilities;
import com.actitime.generic.SeleniumLib;

public class ActiveProjNCustPage extends BasePage 
{
	WebDriver driver = BaseLib.driver;

	@FindBy(css="input[value='Create New Customer']")
	private WebElement createNewCustBtn;

	@FindBy(className="successmsg")
	private WebElement successMsg;

	@FindBy(name="selectedCustomer")
	private WebElement custDrpDwn;

	@FindBy(css="input[value*='Show']")
	private WebElement custShowBtn;

	@FindBy(xpath="//td[starts-with(@id,'customerNameCell')]//a[contains(@href,'customerId')]")
	private WebElement customerNameLink;

	@FindBy(css="input[value='Create New Project']")
	private WebElement createNewProjBtn;

	public String projName = "//a[text()=''{0}'']";

	public ActiveProjNCustPage(WebDriver driver)
	{
		super(driver);
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	//step
	public void clickOnCreateNewCustBtn()
	{
		slib.clickButton(createNewCustBtn);
	}

	//feature
	public void verifyCreateCustomer(String customerName)
	{
		//		String expected = "Customer "+"\""+custName+"\""+" has been successfully created.";
		//		String actual = slib.getElementText(successMsg);
		//		slib.validate(expected, actual, "Success message is verified");

		slib.validateElementDisplayed(successMsg, "Create Customer Msg is displayed");
		slib.validateDataContains(successMsg, customerName, successMsg.getText());
		//OR
		//slib.validateDataContains(successMsg, customerName, slib.getElementText(successMsg));
	}

	//feature
	public void showCustomer(String customerName)
	{
		slib.selectOptionByText(custDrpDwn, customerName);
		slib.clickButton(custShowBtn);
		customerNameLink.click();
	}

	//feature
	public void verifyDeleteCustomer()
	{
		String expected = "Customer has been successfully deleted.";
		slib.validate(expected, slib.getElementText(successMsg), expected);
	}

	//step
	public void clickOnCreateNewProjBtn()
	{
		slib.clickButton(createNewProjBtn);
	}

	//feature
	public void verifyCreateProject(String projectName)
	{
		slib.validateElementDisplayed(successMsg, "Create Project Msg is displayed");
		slib.validateDataContains(successMsg, projectName, successMsg.getText());
	}

	//step
	public void clickProjName(String projectName)
	{
		String value = MessageFormat.format(projName, projectName);
		driver.findElement(By.xpath(value)).click();
	}

	public void verifyDeleteProject()
	{
		String expected = "Project has been successfully deleted.";
		slib.validate(expected, slib.getElementText(successMsg), expected);
	}
}