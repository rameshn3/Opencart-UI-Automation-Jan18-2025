package com.qa.opencart.testcases;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.TestBase;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{

    @BeforeClass
    public void pageClassInstantiationSetUp(){
        homePg = new HomePage(driver);
        regPg = new RegistrationPage(driver);
        loginPg = new LoginPage(driver);
    }

    @Test(description="TC01_Verify the opencart page title")
    public void TC01_Verify_opencart_page_title_Test(){
        ChainTestListener.log("TC01_Verify the opencart page title");
        Assert.assertEquals(homePg.getHomePageTitle(),Constants.HOME_PAGE_TITLE);
    }

    @Test(description="TC02_Verify opencart logo test")
    public void TC02_Verify_opencart_logo_Test(){
        ChainTestListener.log("TC02_Verify_opencart_logo_Test()");
        Assert.assertTrue(homePg.isOpenCartLogoExists());
    }
    @Test(description="TC03_Verify opencart  featured section cards count test")
    public void TC03_Verify_opencart_featured_Cards_Count_Test(){
        ChainTestListener.log("TC03_Verify opencart  featured section cards count test");
        Assert.assertTrue(homePg.getFeaturedSectionCardsCount()==4);
    }

    @Test(description="TC04_Verify navigate to registration page from homepage test")
    public void TC04_Verify_navigate_to_registration_page_Test() throws InterruptedException{
        ChainTestListener.log("TC04_Verify_navigate_to_registration_page_Test()");
        homePg.navigateToRegisterPage();
        regPg.waitForPageLoad(1000);
        ChainTestListener.log("Verify the Registration page title");
        Assert.assertEquals(regPg.getRegisterPageTitle(),Constants.REGISTRATION_PAGE_TITLE);
        ChainTestListener.log("navigate back to HomePage");
        regPg.clickBreadCrumbHomeIcon();
    }
    @Test(description="TC05_Verify navigate to login page from homepage test")
    public void TC05_Verify_navigate_to_login_page_Test() throws InterruptedException{
        ChainTestListener.log("TC05_Verify_navigate_to_login_page_Test()");
        homePg.navigateToLoginPage();
        loginPg.waitForPageLoad(1000);
        ChainTestListener.log("Verify the Login page title");
        Assert.assertEquals(loginPg.getLoginPageTitle(),Constants.LOGIN_PAGE_TITLE);
        ChainTestListener.log("navigate back to HomePage");
        loginPg.navigateToHomePage();
    }

}


