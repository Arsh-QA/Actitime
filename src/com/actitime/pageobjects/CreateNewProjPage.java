package com.actitime.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.SeleniumLib;

public class CreateNewProjPage extends BasePage
{
	
	@FindBy(xpath="//select[@name='customerId']")
	private WebElement selectCustDropdown;
	
	@FindBy(css="input[name='name']")
	private WebElement enterProj;
	
	@FindBy(css="input[name='createProjectSubmit']")
	private WebElement createProjSubmit;
	
	SeleniumLib slib;
	
	public CreateNewProjPage(WebDriver driver)
	{
		super(driver);
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}
	
	//feature
	public void createProject(String customerName, String projectName)
	{
		slib.selectOptionByText(selectCustDropdown, customerName);
		slib.enterData(enterProj, projectName);
		slib.clickButton(createProjSubmit);
	}
}
