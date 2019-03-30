package com.actitime.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.SeleniumLib;

public class LoginPage 
{

	SeleniumLib slib;

	@FindBy(name="username")
	private WebElement unTxtBx;

	@FindBy(name="pwd")
	private WebElement pwdTxtBx;

	@FindBy(id="loginButton")
	private WebElement loginButton;

	@FindBy(xpath="//div[@id='ServerSideErrorMessage']//span[@class='errormsg']")
	private WebElement errorMsg;

	public LoginPage(WebDriver driver)
	{
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	//feature
	public void login(String username, String password)
	{
		slib.enterData(unTxtBx, username);
		slib.enterData(pwdTxtBx, password);
		loginButton.click();
	}

	//feature
	public void verifyInvalidLogin()
	{
		slib.validate("actiTIME - Login",slib.getPageTitle(), "Login Page title is verified");
		slib.validate("Username or Password is invalid. Please try again.", slib.getElementText(errorMsg), "Error message is verified");
		//OR
		//		String expected = "Username or Password is invalid. Please try again.";
		//		String actual = slib.getElementText(errorMsg);
		//		slib.validate(expected, actual, "Error message is verified");
	}
}