package com.actitime.generic;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.io.Files;

public class MyTestNGListener implements ITestListener
{
	public static int startCount, passedCount, failedCount, skippedCount = 0;

	@Override
	public void onTestStart(ITestResult result)
	{
		startCount++;
		Reporter.log(result.getName()+" script execution starts", true);
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		passedCount++;
		Reporter.log(result.getName()+" script passed :)", true);
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		failedCount++;
		Reporter.log(result.getName()+" script is failed :(", true);

		TakesScreenshot ts = (TakesScreenshot) BaseLib.driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/"+result.getName()+".png");
		try 
		{
			Files.copy(srcFile, destFile);//FileUtils replacement in new Selenium version
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		Reporter.log("Screenshot has been taken", true);
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		skippedCount++;
		Reporter.log(result.getName()+" script skipped :(", true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) 
	{
		Reporter.log("Suite Execution Starts "+new Date(),true);
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		Reporter.log("Suite Execution Ends "+new Date(), true);
		System.out.println("Total Script Executed: "+startCount);
		System.out.println("Total Script Passed: "+passedCount);
		System.out.println("Total Script Failed: "+failedCount);
		System.out.println("Total Script Skipped: "+skippedCount);
	}
}