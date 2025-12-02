package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opnecart.exceptions.BrowserExceptions;
import com.qa.opnecart.exceptions.FrameworkException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight;
	public static final Logger log = LogManager.getLogger(DriverFactory.class);
	// warning message, info , error message, fatal error message
	
	/**
	 * This method is used to initilize the driver on the basis of given browser name.
	 * @param Browser
	 * @return
	 */
	
	public WebDriver initdriver (Properties prop) {
		
		log.info("Properties: " + prop);
		
		String browser = prop.getProperty("broswer");
		String url = prop.getProperty("url");
		log.info("Browser name you have given is: " + browser);
		optionsManager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight");
		
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions())); // set the value if driver with threadlocal driver'
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;
		default: 
			log.error("Please pass valid browser name: " + browser);
			throw new BrowserExceptions("=====INVALID BROWSER=======");
		}
		
		getDriver().get(url);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	/**
	 * Get the local thread copy of the driver
	 */
	
	public static WebDriver getDriver() {
	return	tlDriver.get();
	}
	
	
	/**
	 * This is use to initilize the config property.
	 * @return
	 */
	//mvn clean install -Denv="stage"
	public Properties initProp() {
		
		String envName = System.getProperty("env");
		FileInputStream ip = null;
		prop = new Properties();
		
		try {
		if (envName == null) {
			log.warn("env is null, hence running the enviroment on QA env...");
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		else {
			log.info("running the test on env: " +envName);
			//System.out.println("running the test on env: " +envName );
			switch(envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/UAT.config.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;	
				default:
					log.error("Invalid env name: " + envName);
					throw new FrameworkException ("===========Invaalid Enviorment Name============" + envName);
			
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/*Takes Screenshot
	 * 
	 * */
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE); // temp file
		return srcFile;	
	}
	
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES); // temp file
			
	}
	
	public static String getScreenshotBase64() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64); // lightest file will take , we use this when running on ci/cd pipeline
		
	}

}
