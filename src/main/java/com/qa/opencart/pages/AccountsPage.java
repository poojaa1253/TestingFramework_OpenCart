package com.qa.opencart.pages;
import static com.qa.opencart.constants.AppConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	//every page will have its own private webdriver reference
	private WebDriver driver;
	private ElementUtil eutil;
	private final By headers = By.cssSelector("div#content>h2");
	private final By searchfield = By.xpath("//input[@name='search']");
	private final By searchIcon = By.xpath("//i[@class ='fa fa-search']");
	public static final Logger log = LogManager.getLogger(AccountsPage.class);
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		 eutil = new ElementUtil(driver);
	}
	
	@Step("getting the login page title")
	public String getAccPageTitle() {
		String title = eutil.waitFotTitleIs(HOMEPAGE_PAGE_TITLE, DEFAULT_TIMEOUT);
		log.info("Login page title is: " + title);
		return title;	
	}
	
	@Step("getting the Account page url")
	public String getAccountPageurl() {
		String url = eutil.waitForURLContains(ACCOUNT_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		log.info("Account page url is: " + url);
		return url;
	}
	
	@Step("getting the account page header")
	public List<String> getAccountsPageHeaders() {
		List<WebElement> headerList  = driver.findElements(headers);
		List<String> headertexts = new ArrayList<String>();
		for (WebElement e: headerList) {
			String text = e.getText();
			headertexts.add(text);
			
		}
		System.out.println("Acc Page Header" + headertexts);
		return headertexts;
	}
	
	@Step("Searching the product: {0}")
	public SearchResultsPage searchProduct(String ProductName) {
		log.info("Search Key is: " + ProductName);
		eutil.doSendKeys(searchfield, ProductName);
		eutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
		
	}
	
	

}
