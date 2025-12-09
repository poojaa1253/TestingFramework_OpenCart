package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;


import io.qameta.allure.Description;

@Listeners(ChainTestListener.class)
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginpage;
	protected AccountsPage accountpage;
	protected SearchResultsPage searchResultPage; 
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	public static final Logger log = LogManager.getLogger(BaseTest.class);
	
	@Description("initlize the driver and properties")
	@BeforeTest
	@Parameters({"browser"})
	public void setup(@Optional("chrome") String browserName) {
		df = new DriverFactory ();
		prop = df.initProp();
		 if (browserName!=null) { // this means browsername is passed from testng file
		  prop.setProperty("broswer", browserName); // at the runtime key browser in config.property file will get updated to value we have in testng file.
		 }
		 
		driver = df.initdriver(prop); // assign the returned driver
		loginpage = new LoginPage(driver);
		
	}
	
//	@BeforeMethod
//	public void beforeMethod(ITestResult result) {
//		LogUtil.info("-----------------Starting test case----------" + result.getMethod().getMethodName());
//		
//	}
	
	@AfterMethod // will be running after each @test method
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) { // only for failure test cases-- true
			log.info("------Screenshot is taken----");
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		
	//	ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");  we can comment above line and uncomment this  
	//if we want to take screenshot for every test.
		//LogUtil.info("-----------------ending test case----------" + result.getMethod().getMethodName());
	}
	
	@Description("teardown the driver")
	@AfterTest
	public void tearDown() {
		if (driver != null) 
		{
		 driver.quit(); 
		 log.info("Closing the browser");
		 }
	}
	
	

}
