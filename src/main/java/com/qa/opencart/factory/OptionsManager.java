package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("running in headless mode---");
			co.addArguments("--headless");	
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("running in incognito mode---");
			co.addArguments("--incognito");	
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		fo.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("running in headless mode---");
			fo.addArguments("--headless");	
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("running in incognito mode---");
			fo.addArguments("--incognito");	
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions eo = new EdgeOptions();
		eo.setBinary("/usr/bin/microsoft-edge /usr/share/man/man1/microsoft-edge.1.gz");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("running in headless mode---");
			eo.addArguments("--headless");	
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("running in incognito mode---");
			eo.addArguments("--inprivate");	
		}
		return eo;
	}


}
