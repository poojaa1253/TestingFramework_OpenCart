package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

@Feature("Feature 51 : open cart - Account Page feature")
@Epic("Epic 101: design Account page for opne cart application")
@Story("UserSotry 103: implement account page for open cart application")
public class AccountsPageTest extends BaseTest{
	
	//before Test-> before class->
	
	
	@BeforeClass
	public void accPageSetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("checking open cartaccount page title.")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Pooja")
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accountpage.getAccPageTitle(), "My Account");
		
	}
	
	@Description("checking open cart account page url.")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Pooja")
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accountpage.getAccountPageurl().contains(ACCOUNT_PAGE_FRACTION_URL));
		
	}
	
	@Description("checking open cart account page header.")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Pooja")
	@Test
	public void accPageHeadersTest() {
		List<String> ActualHeaderList =accountpage.getAccountsPageHeaders();
		Assert.assertEquals(ActualHeaderList, expectedAccPageHeadersList);	
	}
	

}
