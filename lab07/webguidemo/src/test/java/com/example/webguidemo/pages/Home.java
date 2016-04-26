package com.example.webguidemo.pages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home extends WebDriverPage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
		driver = driverProvider.get();
		wait = new WebDriverWait(driver, 3);
		js = ((JavascriptExecutor) driver);
	}


	public void open() {
		get("http://www.seleniumframework.com/Practiceform/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickExampleLink(){
		WebElement element = driver.findElement(By.className("vfb-submit"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		findElement(By.xpath("//*[@id='recent-posts-3']/ul/li[1]/a")).click();
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickAlertButton() throws InterruptedException{
		WebElement element = driver.findElement(By.className("vfb-submit"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
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
		WebElement element = driver.findElement(By.className("vfb-submit"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
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
		WebElement element = driver.findElement(By.className("vfb-submit"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
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
