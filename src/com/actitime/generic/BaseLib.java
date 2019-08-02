package com.actitime.generic;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseLib
{
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest parent;
	public static ExtentTest logger;

	@BeforeTest
	public void startReport(){
		extent = new ExtentReports("./test-output/extentReport.html", true);	
		extent.addSystemInfo("Environment", "Production");
		extent.addSystemInfo("User Name", "Arsh Gupta");
		extent.loadConfig(new File("extentConfig.xml"));
	}

	@BeforeClass
	public void startLogger(){
		parent = extent.startTest(this.getClass().getSimpleName());
	}

	@BeforeMethod
	@Parameters({"browser", "baseurl"})
	public static void preCondition(String browserName, String url, ITestResult result)
	{
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
			//driver = new ChromeDriver();
			driver = new RemoteWebDriver(DesiredCapabilities.chrome());
			Reporter.log("Chrome Browser Launched", true);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./exefiles/geckodriver.exe");
			//driver = new FirefoxDriver();
			driver = new RemoteWebDriver(DesiredCapabilities.firefox());
			Reporter.log("Firefox Browser Launched", true);
		}
		else if(browserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "./exefiles/IEDriverServer.exe");
			//driver = new InternetExplorerDriver();
			driver = new RemoteWebDriver(DesiredCapabilities.internetExplorer());
			Reporter.log("IE Browser Launched", true);
		}
		logger = extent.startTest(result.getMethod().getMethodName());
		parent.appendChild(logger);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(url);
		Reporter.log(url+" url is navigated", true);
	}

	@AfterMethod
	public static void postCondition(ITestResult result)
	{	
		driver.close();
		Reporter.log("Browser Closed", true);
		if(driver!= null)
		{
			driver.quit();
			Reporter.log("All sessions are closed", true);
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case failed is : "+ result.getName());
			logger.log(LogStatus.FAIL, "Error is : "+ result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test case skipped is : "+ result.getName());
		}
		extent.endTest(logger);
	}

	@AfterClass
	public void endLogger(){
		extent.endTest(parent);
	}

	@AfterTest
	public void endReport(){
		extent.flush();
		//extent.close();
	}
}
