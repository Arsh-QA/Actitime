package com.actitime.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseLib 
{
	public static WebDriver driver;
	
	@BeforeMethod
	@Parameters({"browser", "baseurl"})
	public static void preCondition(String browserName, String url)
	{
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
			driver = new ChromeDriver();
			//driver = new RemoteWebDriver(DesiredCapabilities.chrome());
			Reporter.log("Chrome Browser Launched", true);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./exefiles/geckodriver.exe");
			driver = new FirefoxDriver();
			//driver = new RemoteWebDriver(DesiredCapabilities.firefox());
			Reporter.log("Firefox Browser Launched", true);
		}
		else if(browserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "./exefiles/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			//driver = new RemoteWebDriver(DesiredCapabilities.internetExplorer());
			Reporter.log("IE Browser Launched", true);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(url);
		Reporter.log(url+" url is navigated", true);
	}
	
	@AfterMethod
	public static void postCondition()
	{
		driver.close();
		Reporter.log("Browser Closed", true);
		if(driver!= null)
		{
			driver.quit();
			Reporter.log("All sessions are closed", true);
		}
	}
}
