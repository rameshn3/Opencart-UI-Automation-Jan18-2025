package com.qa.opencart.testcases;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.TestBase;
import com.qa.opencart.factory.WebDriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest extends TestBase{

    @BeforeClass
    public void pageClassInstantiationSetUp() throws InterruptedException{
        homePg = new HomePage(driver);
        regPg = new RegistrationPage(driver);
        loginPg = new LoginPage(driver);
        myaccountPg = new MyAccountPage(driver);
        logoutPg = new LogoutPage(driver);
        ChainTestListener.log("navigate to login page");
        homePg.navigateToLoginPage();
    }

    @Test(description="TC01_Verify the login page title")
    public void TC01_Verify_login_page_title_Test(){
        ChainTestListener.log("TC01_Verify the login page title");
        Assert.assertEquals(loginPg.getTitle(),Constants.LOGIN_PAGE_TITLE);
    }

    @Test(description="TC02_Verify the login page url test")
    public void TC02_Verify_login_url_Test(){
        ChainTestListener.log("TC02_Verify the login page url test()");
        Assert.assertTrue(loginPg.getLoginPageUrl().contains(Constants.LOGIN_PAGE_FRACTION_URL));
    }
    @Test(description="TC03_Verify login page elements test")
    public void TC03_Verify_login_page_elements_Test(){
        ChainTestListener.log("TC03_Verify opencart  featured section cards count test");
        Assert.assertTrue(loginPg.isLoginBreadCrumbExists());
        Assert.assertTrue(loginPg.isNewCustomerHeaderExists());
        Assert.assertTrue(loginPg.isReturningCustomerExists());
    }

    @Test(description="TC04_Verify navigate to registration page from loginpage test")
    public void TC04_Verify_navigate_to_registration_page_Test() throws InterruptedException{
        ChainTestListener.log("TC04_Verify_navigate_to_registration_page_Test from login page ");
        loginPg.clickNewCustomerContinueBtn();
        regPg.waitForPageLoad(1000);
        ChainTestListener.log("Verify the Registration page title");
        Assert.assertEquals(regPg.getRegisterPageTitle(),Constants.REGISTRATION_PAGE_TITLE);
        ChainTestListener.log("navigate back to loginpage ");
        regPg.clickOnLoginPageLink();
    }
    @Test(description="TC05_Verify navigate to forgot password page from loginpage test",dependsOnMethods={"TC04_Verify_navigate_to_registration_page_Test"})
    public void TC05_Verify_navigate_to_forgotpassword_page_Test() throws InterruptedException{
        ChainTestListener.log("TC05_Verify_navigate_to_forgotpassword_page_Test()");
       loginPg.navigateToForgottenPasswordPage();
        loginPg.waitForPageLoad(1000);
        ChainTestListener.log("navigate back to login page");
        driver.navigate().back();
    }
    @Test(description="TC06_Verify empty credential test")
    public void TC06_Verify_empty_Credential_Test() throws InterruptedException{
        ChainTestListener.log("TC06_Verify_empty_Credential_Test()");
        loginPg.doLogin(" "," ");
        loginPg.waitForPageLoad(1000);
        ChainTestListener.log(" verify empty credential error message in login page");
       Assert.assertTrue(loginPg.getEmptyCredentialErrorMsg().contains(Constants.EMPTY_CREDS_ERROR_MSG));
    }

    @Test(description="TC07_Verify valid credential test",dependsOnMethods={"TC06_Verify_empty_Credential_Test"})
    public void TC07_Verify_valid_Credential_Test() throws InterruptedException, IOException{
        ChainTestListener.log("TC07_Verify_valid_Credential_Test()");
        loginPg.doLogin(WebDriverFactory.readPropertyValue("username"),WebDriverFactory.readPropertyValue("pwd"));
       myaccountPg.waitForPageLoad(1000);
        ChainTestListener.log(" verify my account page title");
        Assert.assertEquals(myaccountPg.getMyAccountPageTitle(),Constants.MYACCOUNT_PAGE_TITLE);
    }

    @Test(description="TC08_Verify logout from myAccount test",dependsOnMethods={"TC07_Verify_valid_Credential_Test"})
    public void TC08_Verify_logout_Test() throws InterruptedException, IOException{
        ChainTestListener.log("TC08_Verify_logout_Test()");
        myaccountPg.clickLogoutLink();
        logoutPg.waitForPageLoad(2000);
        ChainTestListener.log(" verify logout page title");
        Assert.assertEquals(logoutPg.getTitle(),Constants.LOGOUT_PAGE_TITLE);
        ChainTestListener.log(" click continue in logout page");
        logoutPg.clickContinueBtn();
        homePg.waitForPageLoad(2000);
        Assert.assertEquals(homePg.getHomePageTitle(),Constants.HOME_PAGE_TITLE);
    }

}


