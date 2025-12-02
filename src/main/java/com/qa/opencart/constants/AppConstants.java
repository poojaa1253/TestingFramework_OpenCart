package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	public static final String Login_PAGE_TITLE = "Account Login";
	public static final String HOMEPAGE_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	
	public static final String ACCOUNT_PAGE_FRACTION_URL = "route=account/account";

	public static final int DEFAULT_TIMEOUT = 5;
	public static final int MEDIUM_DEFAULT_TIMEOUT = 10;
	public static final int LONG_TIMEOUT = 15;
	
	public static List<String> expectedAccPageHeadersList = List.of("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final String REGISTRATION_SUCCESS_MSG = "Your Account Has Been Created!";


	//*****************sheet name*********************
	
	public static final String REGISTER_SHEET_NAME = "register";
	
	
}
