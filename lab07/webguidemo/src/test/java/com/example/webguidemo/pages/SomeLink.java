package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class SomeLink extends WebDriverPage{
	
	public SomeLink(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}

	public void open() {
		get("http://www.seleniumframework.com/setup-first-project/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
