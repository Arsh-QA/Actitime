package com.actitime.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.SeleniumLib;

public class CreateNewCustPage extends BasePage
{

	@FindBy(name="name")
	private WebElement custNameTxtBx;

	@FindBy(name="createCustomerSubmit")
	private WebElement createCustSubmitBtn;

	public CreateNewCustPage(WebDriver driver)
	{
		super(driver);
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	//feature
	public void createCustomer(String customerName)
	{
		slib.enterData(custNameTxtBx, customerName);
		slib.clickButton(createCustSubmitBtn);
	}
}