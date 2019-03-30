package com.actitime.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.SeleniumLib;

public class EditCustInfoPage extends BasePage
{

	@FindBy(css="input[value='Delete This Customer']")
	private WebElement deleteCustBtn;

	@FindBy(id="deleteButton")
	private WebElement deleteCustConfirmBtn;

	public EditCustInfoPage(WebDriver driver)
	{
		super(driver);
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	//feature
	public void deleteCustomer()
	{
		slib.clickButton(deleteCustBtn);
		slib.clickButton(deleteCustConfirmBtn);
	}
}
