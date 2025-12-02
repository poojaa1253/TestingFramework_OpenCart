package com.qa.opencart.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qa.opencart.factory.DriverFactory;

public class LogUtil {
	
	public static Logger log = LoggerFactory.getLogger(DriverFactory.class);
	
	public static void info(String msg) {
		log.info(msg);
	}
	
	public static void warn(String msg) {
		log.warn(msg);
	}
	
	public static void error(String msg) {
		log.error(msg);
	}
	
	public static void trace(String msg) {
		log.trace(msg);
	}

}
