package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

import static com.qa.opencart.constants.AppConstants.*;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By confirmYes = By.xpath("//label[@class='radio-inline']//input[@value='1']");
	private By confirmNo = By.xpath("//label[@class='radio-inline']//input[@value='0']");
	private By chkboxAgree = By.xpath("//div[@class='pull-right']//input[@name='agree']");
	private By continuebtn = By.xpath("//div[@class='pull-right']//input[@type='submit']");
	
	private By successmsg = By.cssSelector("div#content h1");
	private By logoutLink =By.linkText("Logout");
	private By registerLink =By.linkText("Register");
	public static final Logger log = LogManager.getLogger(RegisterPage.class);		
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil (driver);
	}
	
	public boolean userRegistration(String firstname, String lastname, 
									String telephone, String password, String subscribe) {
		eutil.waitForElementVisible(this.firstname, DEFAULT_TIMEOUT).sendKeys(firstname);
		eutil.doSendKeys(this.lastname, lastname);
		eutil.doSendKeys(this.email, StringUtils.getRandomEmailID());
		eutil.doSendKeys(this.telephone, telephone);
		eutil.doSendKeys(this.password, password);
		eutil.doSendKeys(this.confirmPassword, password);
		if (subscribe.equalsIgnoreCase("yes")) {
			eutil.doClick(confirmYes);	
		}
		else {
			eutil.doClick(confirmNo);	
		}
		eutil.doClick(chkboxAgree);
		eutil.doClick(continuebtn);
		
		if(eutil.waitForElementVisible(successmsg, LONG_TIMEOUT).getText().contains(REGISTRATION_SUCCESS_MSG)) {
			eutil.waitForElementVisible(logoutLink, MEDIUM_DEFAULT_TIMEOUT).click();
			eutil.doClick(registerLink);
			return true;
		}
		return false;
	}
	
	
	
	

}
