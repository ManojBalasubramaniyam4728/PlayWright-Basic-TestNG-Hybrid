package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.TestBase;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends TestBase{
    
	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage=homePage.navigateToLoginPage();
		String actualLoginPageTitle=loginPage.getLoginPageTitle();
		System.out.println("Page actual title : "+actualLoginPageTitle);
		Assert.assertEquals(actualLoginPageTitle,AppConstants.LOGIN_PAGE_TITLE );
	}
	
	@Test(priority = 2)
	public void forgotPasswordLinkExistTest() {
		boolean ispresent=loginPage.isForgotPasswordLinkExist();
		Assert.assertTrue(ispresent);
	}
	
	@Test(priority = 3)
	public void appLoginTest() {
		boolean isLogined=loginPage.doLogin(prop.getProperty("userName".trim()), prop.getProperty("password").trim());
		Assert.assertTrue(isLogined);
	}
}
