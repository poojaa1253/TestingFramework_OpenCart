package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class SearchTest extends BaseTest{
	
	@BeforeClass
	public void searchSetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("checking search feture test.")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Pooja")
	@Test
	public void searchTest() {
		searchResultPage = accountpage.searchProduct("macbook");
		int actualResultCount = searchResultPage.getResulsProductCount();
		Assert.assertEquals(actualResultCount, 3);
	}
	
}
