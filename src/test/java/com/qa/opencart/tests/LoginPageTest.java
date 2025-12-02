package com.qa.opencart.tests;

import org.testng.Assert;
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


@Feature("Feature 50 : open cart - login feature")
@Epic("Epic 100: design Login page for opne cart application")
@Story("UserSotry 101: implement login page for open cart application")
public class LoginPageTest extends BaseTest{

	@Description("checking opne cart login page.")
	@Severity(SeverityLevel.MINOR)
	@Owner("Pooja")
	@Test (description ="Get login Page Title", priority =1)
	public void loginPageTitleTest() {
		String actualTitle = loginpage.getLoginPageTitle();
		//ChainTestListener.log("Checking login page title");
		Assert.assertEquals(actualTitle, Login_PAGE_TITLE);	
	}
	
	@Description("checking opne cart url.")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Pooja")
	@Test (description ="Get login Page URL", priority = 2)
	public void loginPageurlTest() {
		String actualurl= loginpage.getLoginPageurl();
		Assert.assertTrue(actualurl.contains(LOGIN_PAGE_FRACTION_URL));	
	}
	
	@Description("checking forgotpassword link exist.")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Pooja")
	@Test(description ="verify forgotpassword link", priority = 3)
	public void forgotpasswordLinkTest() {
		Assert.assertTrue(loginpage.isForgotPasswordLinkExist());
	}
	
	@Description("checking user is able to login with valid user credential")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Pooja")
	@Test(description ="login with valid cred" , priority = Short.MAX_VALUE)
	public void loginTest() {
	accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountpage.getAccPageTitle(),HOMEPAGE_PAGE_TITLE);
		
	}
	
	@Test(enabled= false, description ="WIP, forgot pwd check") //enabled= false is used when we don't want to include the test in execution.
	public void forgotPwd() {
			System.out.println("forgot password");		
		}
	

}
