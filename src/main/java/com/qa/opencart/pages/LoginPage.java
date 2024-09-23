package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;

	// 1. String Locator - OR
	private String emailId = "input#input-email";
	private String password = "input#input-password";
	private String LoginButton = "//input[@type='submit']";
	private String forgotPasswordLink = "//input[@id='input-password']/..//a[text()='Forgotten Password']";
	private String logoutLink="//div[@class='list-group']//a[text()='Logout']";

	// 2. page constructor
	public LoginPage(Page page) {
	this.page=page;
     }
	
	//3. page action/method
	public String getLoginPageTitle() {
		String loginpageTitletext=page.title();
		return loginpageTitletext;
	}
	
	public boolean isForgotPasswordLinkExist() {
	boolean isForgotPasswordLinkExist=page.isVisible(forgotPasswordLink);
	return isForgotPasswordLinkExist;
	}
	
	public boolean doLogin(String appUserName, String appPassword) {
		System.out.println("App Creds: "+appUserName+ " : "+ appPassword);
		page.fill(emailId, appUserName);
		page.fill(password, appPassword);
		page.click(LoginButton);
		if (page.isVisible(logoutLink)) {
			System.out.println("User is logged in successfully.....");
			return true;
		}
		return false;
	}
}