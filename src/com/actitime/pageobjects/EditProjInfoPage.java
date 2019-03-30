package com.actitime.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.SeleniumLib;

public class EditProjInfoPage extends BasePage

{
	public EditProjInfoPage(WebDriver driver)
	{
		super(driver);
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="input[value='Delete This Project']")
	private WebElement deleteProjBtn;

	@FindBy(css="input[value='Delete Project']")
	private WebElement deleteProjConfirmBtn;

	//feature
	public void confirmDeleteProj()
	{
		slib.clickButton(deleteProjBtn);
		slib.clickButton(deleteProjConfirmBtn);
	}
}
