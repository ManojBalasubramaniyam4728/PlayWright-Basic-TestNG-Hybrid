package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	
	//1.String Locators - OR
	private String search="input[name='search']";
	private String searchIcon="div#search button";
	private String searchPageHeader="div#content h1";
	private String loginLink="a:text('Login')";
	private String myAccountLink="a[title='My Account']";
	
	//2.Page constructor
	public HomePage(Page page) {
		this.page=page;
	}
	
	//3.Page actions/method
	public String getHomePageTitle() {
		String title=page.title();
		System.out.println("The title of the page : "+title);
		return title;
	}
	
	public String getHomePageUrl() {
		String url= page.url();
		System.out.println("The Url of the page : "+url);
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String searchPageHeaderText=page.textContent(searchPageHeader);
		return searchPageHeaderText;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccountLink);
		page.click(loginLink);
		return new LoginPage(page);
	}
	
	

}
