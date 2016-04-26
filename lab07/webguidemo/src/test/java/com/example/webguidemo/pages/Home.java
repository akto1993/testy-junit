package com.example.webguidemo.pages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;

public class Home extends WebDriverPage {
	WebDriver driver;
	
	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
		driver = driverProvider.get();
	}


	public void open() {
		get("http://www.seleniumframework.com/Practiceform/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickSomeLink(){
		findElement(By.xpath("//*[@id='recent-posts-3']/ul/li[1]/a")).click();
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickAlertButton() throws InterruptedException{
		findElement(By.xpath("//*[@id='alert']")).click();
		Thread.sleep(2000);
	}
	
	public String alertCheck(){
			Alert simpleAlert = driver.switchTo().alert();
			String alertText = simpleAlert.getText();
			simpleAlert.accept();
			return alertText;
	}
	
	public void clickVerButton(){
		findElement(By.id("vfb-4")).click();
	}
	public boolean findValidatorMessageRequired(){
		WebElement pom = findElement(By.xpath("//*[contains(text(),'This field is required.')]"));
		if (pom != null){
			 return true;
		}else {
			return false;
		}
	}
	public void clearValue() {
		findElement(By.xpath("//*[@id='vfb-3']")).clear();
	}


	public void typeValue(String digits) {
		findElement(By.xpath("//*[@id='vfb-3']")).sendKeys(digits);
	}


	public String validMsg() {
		return findElement(By.xpath("//*[@id='item-vfb-2']/ul/li[1]/span/label[1]")).getText();
	}


	public void clickTestLink() {
		findElement(By.linkText("Find Element Strategies")).click();
	}


	public boolean createPrSc() {
		File prsc = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(prsc, new File("testlink.png"));
			return true;
		} catch (IOException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	
}
