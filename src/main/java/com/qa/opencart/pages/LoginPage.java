package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {
	//every page will have its own private webdriver reference
	private WebDriver driver;
	private ElementUtil eutil;
	
	//1. private by locators
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@type='submit']");
	private By forgotPassword  = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	public static final Logger log = LogManager.getLogger(LoginPage.class);
	
	//2. supply the driver ( public page constructor)
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		 eutil = new ElementUtil(driver);
	}
	
	//3.public page methods which will define page actions
	@Step("getting the login page title")
	public String getLoginPageTitle() {
		String title = eutil.waitFotTitleIs(Login_PAGE_TITLE, DEFAULT_TIMEOUT);
		log.info("Login page title is: " + title);
		return title;
	}
	
	@Step("getting the login page url")
	public String getLoginPageurl() {
		String url = eutil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		log.info("Login page url is: " + url);
		return url;
	}
	
	@Step("checking forgot password link exist or not")
	public boolean isForgotPasswordLinkExist() {
		return eutil.isElementDisplayed(forgotPassword);
	}
	
	@Step("login with valid username:{0} and password: {1}")
	public AccountsPage doLogin (String usrname, String pwd) {
		log.info("username: " + usrname +" Password: " + pwd);
		eutil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(usrname);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginbtn).click();
		String title =eutil.waitFotTitleIs(HOMEPAGE_PAGE_TITLE, DEFAULT_TIMEOUT);
		log.info("Accounts page title is: " +title );
		return new AccountsPage(driver);
	}
	
	@Step("Navigating to the registration page")
	public RegisterPage navigatetoRegisterPage() {
		eutil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
	

}
