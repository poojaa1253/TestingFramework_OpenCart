package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class Cartpage {
	
	private By cartlink = By.linkText("link");
	
	public void cart() {
		System.out.println("cart method" + cartlink);
	}

}
