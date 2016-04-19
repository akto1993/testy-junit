package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Home extends WebDriverPage {

	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	private final static String SPORT_LINK = "//*[@id='main-menu']/a[4]";
	private final static String SPORT_LINK_TEXT = "SPORT";

	
	public void open() {
		get("http://www.teleman.pl");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickSportLink(){
		//findElement(By.xpath(SPORT_LINK)).click();
		findElement(By.linkText(SPORT_LINK_TEXT)).click();
	}
	
}
