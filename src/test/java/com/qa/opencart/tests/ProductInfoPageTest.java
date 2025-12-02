package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;

public class ProductInfoPageTest extends BaseTest {
		
		@BeforeClass
		public void productInfoSetup() {
			accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			
		}
		//Arrange --act assert (1 assert for one test case)
		
		@DataProvider 
    	public Object[][] getproductTestData(){
    		return new Object[][] {
    			{"macbook", "MacBook"},
    			{"macbook", "MacBook Air"},
    			{"macbook", "MacBook Pro"},
    			{"imac", "iMac"},
    			{"samsung", "Samsung SyncMaster 941BW"},
    			{"samsung", "Samsung Galaxy Tab 10.1"}
    		};
    	} 
		
		@DataProvider
		public Object [][] getProductCSVdata(){
			return CsvUtil.csvData("product");
		}
		
	@Test(dataProvider = "getproductTestData")
     public void productHeaderTest(String searchKey, String ProdcutName) {
		searchResultPage = accountpage.searchProduct(searchKey);
    	 productInfoPage = searchResultPage.selectProduct(ProdcutName);
    	 String ActualHeader = productInfoPage.getProductHeader();
    	 Assert.assertEquals(ActualHeader, ProdcutName);
    	 }
	
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{"macbook", "MacBook", "5"},
			{"macbook", "MacBook Air","4"},
			{"macbook", "MacBook Pro", "4"},
			{"imac", "iMac","3"},
			{"samsung", "Samsung SyncMaster 941BW","1"},
			{"samsung", "Samsung Galaxy Tab 10.1","7"}	
		};	
	}
	
     @Test(dataProvider = "getProductCSVdata")
     public void productImageCountTest(String searchKey, String ProdcutName, String expImageCount) {
    	 searchResultPage= accountpage.searchProduct(searchKey);
    	 productInfoPage = searchResultPage.selectProduct(ProdcutName);
    	 int actImageCount = productInfoPage.getProductImageCount();
    	 Assert.assertEquals(String.valueOf(actImageCount), expImageCount);
    	 }
    	 
    

    	public void productInformationTest() {
    	 searchResultPage = accountpage.searchProduct("macbook");
       	 productInfoPage = searchResultPage.selectProduct("MacBook Pro");
    	 Map<String, String> ActualproductDetails = productInfoPage.getproductDetailsMap();
    	 
    	 SoftAssert sa = new SoftAssert();
    	
    	 sa.assertEquals(ActualproductDetails.get("Brand"), "Apple"); 
    	 sa.assertEquals(ActualproductDetails.get("Product Code"), "Product 18");  
    	 sa.assertEquals(ActualproductDetails.get("Reward Points"), "800"); 
    	 sa.assertEquals(ActualproductDetails.get("Availability"), "Out Of Stock");
    	 sa.assertEquals(ActualproductDetails.get("productPrice"), "$2,000.00");
    	 sa.assertEquals(ActualproductDetails.get("extaxPrice"), "$2,000.00");
    	 sa.assertAll();
    	
    	} 
    	 
     
}
