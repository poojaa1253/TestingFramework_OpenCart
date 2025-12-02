package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	//private by locator
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private final By productMetadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private final By productPricedata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");
	private Map<String, String> productMap;
	public static final Logger log = LogManager.getLogger(ProductInfoPage.class);
	
	//public page constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		 eutil = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String header = eutil.waitForElementVisible(productHeader, DEFAULT_TIMEOUT).getText();
		log.info("Product Header is: " + header);
		return header;
	}
	
	
	public int getProductImageCount() {
		int imageCount = 
		eutil.waitForAllElementsVisible(productImages, DEFAULT_TIMEOUT).size();
		log.info("Total number of Image is: " + imageCount);
		return imageCount;
	}

	

	private void getProductMetaData() {
		List<WebElement> MetaLsit= eutil.waitForAllElementsVisible(productMetadata, DEFAULT_TIMEOUT);
		for (WebElement e: MetaLsit) {
			String metaData= e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> priceLsit= eutil.waitForAllElementsVisible(productPricedata, DEFAULT_TIMEOUT);
		String productPrice = priceLsit.get(0).getText();
		String exTaxPrice = priceLsit.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("extaxPrice", exTaxPrice);
		}
	
	
	public Map<String, String> getproductDetailsMap() {
		//productMap = new HashMap<String, String>();  // hashmap does not maintain any order: non index order 
		//productMap = new LinkedHashMap<String, String>();   linked hashmap will maintain the order of the data: maintains insertion order
		 productMap = new TreeMap<String, String>();  // maintains sorted order for the key
		productMap.put("ProductHeader", getProductHeader());
		productMap.put("ProductImageCount", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		log.info("Full product detail: " + productMap);
		return productMap;
	}
	
}
