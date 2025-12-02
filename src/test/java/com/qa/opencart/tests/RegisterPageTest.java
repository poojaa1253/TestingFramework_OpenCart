package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtils;
import static com.qa.opencart.constants.AppConstants.*;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void registerSetup() {
		registerPage = loginpage.navigatetoRegisterPage();
	}
	
	// microsoft official excel version whoch support .xlsx 
  // /SeleniumFramework/src/test/resources/testdata/OpencartTestData.xlsx
	
	@DataProvider
	public Object[][] getUserRegistrationTestData() {
		return new Object[][] {
			{"vishal", "kumar", "2341232345", "Today@Nov22", "yes"},
			{"jyoti", "sharma", "2341232345", "TestAutomation@123", "yes"},
			{"Archan", "varma", "2341232345", "Today@Nov23", "no"},
		};
	}
	
	@DataProvider
	public Object[][] getUserRegData() {
		Object regData [][]= ExcelUtils.getTestData(REGISTER_SHEET_NAME);
		return regData;
		
	}
	
	
	@Test(dataProvider = "getUserRegData")
	public void userRegistrationTest(String firstname, String lastname, 
			String telephone, String password, String subscribe) {
		Assert.assertTrue(
				registerPage.
					userRegistration(firstname, lastname, telephone, password, subscribe));
	}
	
	

}
