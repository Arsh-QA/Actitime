package com.actitime.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.SeleniumLib;

public abstract class BasePage
{

	SeleniumLib slib;

	@FindBy(xpath="//div[text()='Tasks']")
	private WebElement tasks;

	@FindBy(id="logoutLink")
	private WebElement logoutLink;


	public BasePage(WebDriver driver)
	{
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	//step
	public void clickOnTasks()
	{
		tasks.click();
	}

	//step
	public void clickOnLogout()
	{
		logoutLink.click();
	}
}