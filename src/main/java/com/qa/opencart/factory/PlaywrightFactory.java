package com.qa.opencart.factory;

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

public class PlaywrightFactory {
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	static Properties prop;
	
	//declaring Thread local to give local copy to the thread which is running
	private static ThreadLocal<Playwright> threadPlaywright=new ThreadLocal<Playwright>();
	private static ThreadLocal<Browser> threadBrowser=new ThreadLocal<Browser>();
	private static ThreadLocal<BrowserContext> threadBrowserContext=new ThreadLocal<BrowserContext>();
	private static ThreadLocal<Page> threadPage=new ThreadLocal<Page>();
	
	//Since Thread local is private making it as getter and setter
	public static Playwright getPlaywright() {
		return threadPlaywright.get();
	}
	
	public static Browser getBrowser() {
		return threadBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return threadBrowserContext.get();
	}

	public static Page getPage() {
		return threadPage.get();
	}


	public Page initializeBrowser(Properties prop) {
		String browserName=prop.getProperty("browser").trim();
		String headless=prop.getProperty("headless").trim();
	    boolean headlessOpetioin=stringToBoolean(headless);

		System.out.println("Browser name is : " + browserName);

		threadPlaywright.set( Playwright.create());

		switch (browserName.toLowerCase()) {
		case "chromium":
			threadBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessOpetioin)));
			break;
		case "firefox":
			threadBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessOpetioin)));
			break;
		case "safari":
			threadBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessOpetioin)));
			break;
		case "chrome":
			threadBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headlessOpetioin)));
			break;
		case "edge":
			threadBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headlessOpetioin)));
			break;

		default:
			System.out.println("Please pass the right browser name....");
			break;
		}

		threadBrowserContext.set(getBrowser().newContext());
		threadPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
		return getPage();

	}

	/**
	 * This method is used to initialize the properties for the config file
	 */
	public Properties initializeProperties() {
		try {
			FileInputStream fip = new FileInputStream("./src/test/resource/config/config.properties");
			prop = new Properties();
			prop.load(fip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * This method is used to convert String to boolean
	 */
	public static boolean stringToBoolean(String stringInput) {
		boolean booleanResult=Boolean.parseBoolean(stringInput);
		return booleanResult;
	}
	
	/**
	 * This method is to take screenshot
	 */
	public static String takeScreenshot() {
		String fullPageScreenshoot= prop.getProperty("fullPageScreenshoot");
		boolean setFullPageScreenshoot= stringToBoolean(fullPageScreenshoot);
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		getPage().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get(path))
						.setFullPage(setFullPageScreenshoot));
		return path;
    }
}
