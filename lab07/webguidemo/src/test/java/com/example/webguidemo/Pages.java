package com.example.webguidemo;

import org.jbehave.web.selenium.WebDriverProvider;

import com.example.webguidemo.pages.ExampleLink;
import com.example.webguidemo.pages.VerLink;
import com.example.webguidemo.pages.Home;

public class Pages {

	private WebDriverProvider driverProvider;

	//Pages
	private Home home;
	private ExampleLink examplelink;
	private VerLink verLink;
	// ...

	public Pages(WebDriverProvider driverProvider) {
		super();
		this.driverProvider = driverProvider;
	}

	public Home home() {
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
	
	public ExampleLink examplelink(){
		if (examplelink == null) {
			examplelink = new ExampleLink(driverProvider);
		}
		return examplelink;
	}
	public VerLink verLink(){
		if (verLink == null) {
			verLink = new VerLink(driverProvider);
		}
		return verLink;
	}
}
