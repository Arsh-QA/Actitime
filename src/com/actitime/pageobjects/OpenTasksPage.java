package com.actitime.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.SeleniumLib;

public class OpenTasksPage extends BasePage
{

	@FindBy(linkText="Projects & Customers")
	private WebElement projNCustLink;

	public OpenTasksPage(WebDriver driver)
	{
		super(driver);
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	//step
	public void clickOnProjCustLink()
	{
		projNCustLink.click();
	}
}