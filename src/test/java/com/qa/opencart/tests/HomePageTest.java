package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.TestBase;
import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends TestBase{


	@Test(priority = 1)
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void homePageUrlTest() {
		String actualUrl = homePage.getHomePageUrl();
		Assert.assertEquals(actualUrl, prop.getProperty("url"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Mackbook"},
			{"iMac"},
			{"Samsung"}
		};
	}

	@Test(dataProvider = "getProductData",priority = 3)
	public void searcTest(String productName) {
	    String actualSearchHeader=homePage.doSearch(productName);
	    Assert.assertEquals(actualSearchHeader, "Search - "+productName);
	}

}
