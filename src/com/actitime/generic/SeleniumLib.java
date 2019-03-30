package com.actitime.generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class SeleniumLib 
{
	WebDriver driver;
	/************************constructor********************/
	public SeleniumLib(WebDriver driver)
	{
		this.driver = driver;
	}
	/**********************enter data***********************/
	public void enterData(WebElement txtBx, String input)
	{
		if (txtBx.getAttribute("value")!= null) 
		{
			txtBx.clear();
			txtBx.sendKeys(input);
		}
		else
		{
			txtBx.sendKeys(input);
		}
	}
	/************************click button**********************/	
	public void clickButton(WebElement button)
	{
		if (button.getAttribute("type").equals("button"))
		{
			button.click();
		}
		else if(button.getAttribute("type").equals("submit"))
		{
			button.submit();
		}
	}
	/***********************click checkbox**********************/
	public void clickCheckBox(WebElement checkbox)
	{
		if (checkbox.isSelected()) //true
		{
			Reporter.log("Checkbox is already selected", true);
		}
		else
		{
			checkbox.click();
		}
	}
	/**********************select option by text***************/
	public void selectOptionByText(WebElement dropdown, String text)
	{
		Select sel = new Select(dropdown);
		sel.selectByVisibleText(text);
	}
	/**********************mouse hover*************************/
	public void mouseHover(WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**********************hard code wait*********************/
	public void iSleep(int secs)
	{
		try
		{
			Thread.sleep(secs*1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	/********************explicit wait*************************/
	public void eWaitForVisible(WebElement element, int secs)
	{
		WebDriverWait wait = new WebDriverWait(driver, secs);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/********************verify expected and actual result********/
	public void validate(String expected, String actual, String passedMsg)
	{
		Assert.assertEquals(actual, expected);
		Reporter.log(passedMsg, true);
	}
	/********************get Element Text************************/
	public String getElementText(WebElement element)
	{
		String text = element.getText();

		return text;
	}
	/*******************get Page title****************************/
	public String getPageTitle()
	{
		String title = driver.getTitle();

		return title;
	}
	/*****************validate element displayed******************/
	public void validateElementDisplayed(WebElement element, String passedMsg)
	{
		Assert.assertTrue(element.isDisplayed());
		Reporter.log(passedMsg, true);
	}
	/****************validate String contains********************/
	public void validateDataContains(WebElement element, String data, String passedMsg)
	{
		Assert.assertTrue(element.getText().contains(data));
		Reporter.log(passedMsg, true);
	}	
}