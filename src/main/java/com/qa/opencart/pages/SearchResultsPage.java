package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	private final By ResultProduct= By.cssSelector("div.product-thumb");
	public static final Logger log = LogManager.getLogger(SearchResultsPage.class);
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver; 
		eutil =  new ElementUtil(driver);
	}
	
	public int getResulsProductCount() {
		int searchCount = eutil.waitForAllElementsVisible(ResultProduct, MEDIUM_DEFAULT_TIMEOUT).size();
		log.info("Total number of Search Prodcuts are: " +searchCount);
		return searchCount;
	}
	
	// our product is dynamic and can change any time. hence we are not maintaining the by locator of the product
	//hence we are giving product name and using click method to click on it by link text locator.
	public ProductInfoPage selectProduct(String ProductName) {
		log.info("Product name is : " + ProductName);
		eutil.waitForElementPresence(By.linkText(ProductName), DEFAULT_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}
	
	

}
