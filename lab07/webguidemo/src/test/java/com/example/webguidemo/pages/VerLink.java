package com.example.webguidemo.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VerLink extends WebDriverPage{

	public VerLink(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}
	
	public boolean checkElement(){
		WebElement pom = findElement(By.id("form_success"));
		if (pom != null){
			 return true;
		}else {
			return false;
		}
	}
	

}
