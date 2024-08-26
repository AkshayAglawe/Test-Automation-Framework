package com.qa.Assigment.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
public BrowserContext browserContext;
	Page page;
	Properties prop;

	public Page initBrowser(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		playwright = Playwright.create();
		switch (browserName.toLowerCase()) {
		case "chromium":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			break;

		case "edge":
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;

		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + browser);
		}
			browserContext=	browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("./src/test/resources/signedInState.json")));
		Page page=browserContext.newPage();
		
		
		page.navigate(prop.getProperty("url").trim());
		
	
		return page;
	}
	


	/*
	 * This method is used to initialize the properties from config file
	 */
	public Properties initProp() {
		
		
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return prop;
	}
}
