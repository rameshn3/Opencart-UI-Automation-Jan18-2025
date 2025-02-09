package com.qa.opencart.testcases;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.TestBase;
import com.qa.opencart.factory.WebDriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.io.IOException;

public class MyAccountPageTest extends TestBase{

    @BeforeClass
    public void pageClassInstantiationSetUp() throws InterruptedException, IOException{
        homePg = new HomePage(driver);
        regPg = new RegistrationPage(driver);
        loginPg = new LoginPage(driver);
        myaccountPg = new MyAccountPage(driver);
        resultPg = new ResultsPage(driver);
        logoutPg = new LogoutPage(driver);
        ChainTestListener.log("navigate to login page");
        homePg.navigateToLoginPage();
        loginPg.doLogin(WebDriverFactory.readPropertyValue("username"),WebDriverFactory.readPropertyValue("pwd"));
        ChainTestListener.log("verify my account page title");
        Assert.assertEquals(myaccountPg.getMyAccountPageTitle(),Constants.MYACCOUNT_PAGE_TITLE);
    }

    @Test(description="TC01_Verify the my account page url test")
    public void TC01_Verify_myaccount_page_url_Test(){
        ChainTestListener.log("TC01_Verify_myaccount_page_url_Test()");
        Assert.assertTrue(myaccountPg.getMyAccountPageUrl().contains(Constants.MY_ACCOUNT_PAGE_FRACTION_URL));
    }
    @Test(description="TC02_Verify MyAccount page elements test")
    public void TC02_Verify_myAccount_page_elements_Test() throws InterruptedException{
        ChainTestListener.log("TC02_Verify_myAccount_page_elements_Test");
        Assert.assertTrue(myaccountPg.isSearchEditboxExists());
        Assert.assertTrue(myaccountPg.isLogoutExists());
        myaccountPg.pressEscapeKey();
    }

    @Test(description="TC03_Verify myAccount menu options presence test")
    public void TC03_Verify_myaccount_menu_options_Test() throws InterruptedException{
        ChainTestListener.log("C03_Verify myAccount menu options presence test ");
        ChainTestListener.log("Verify the my account menu options");
        Assert.assertEquals(myaccountPg.getMyAccountMenuOptionList(),Constants.EXPECTED_MYACCOUNT_MENU_OPTIONS_LIST);

    }
    @Test(description="TC04_Verify nmyaccount header options list test")
    public void TC04_Verify_myAccount_header_options_Test() throws InterruptedException{
        ChainTestListener.log("TC04_Verify nmyaccount header options list test()");
        ChainTestListener.log("Verify the my account header options");
        Assert.assertEquals(myaccountPg.getMyAccountHeaderOptionsList(),Constants.EXPECTED_MYACCOUNT_HEADER_OPTIONS_LIST);

    }
    @Test(description="TC05_Verify broken_links test")
    public void TC05_Verify_broken_links_Test() throws InterruptedException, IOException{
        ChainTestListener.log("TC05_Verify_broken_links_Test()");
        ChainTestListener.log("fetch all the links from my account page");
        List<WebElement>myAccountPageLinksList = driver.findElements(By.tagName("a"));
        for(WebElement link:myAccountPageLinksList){
            verifyUrls(link.getDomAttribute("href"));
        }
myaccountPg.pressEscapeKey();
         }

    @Test(description="TC06_Verify product search test",dataProvider="productTestData")
    public void TC06_Verify_product_Search_Test(String productName) throws InterruptedException, IOException{
        ChainTestListener.log("TC06_Verify_product_Search_Test()");
        resultPg = myaccountPg.doProductSearch(productName);
        resultPg.waitForPageLoad(1000);
        ChainTestListener.log("verify results page title");
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(resultPg.getTitle(),"Search - "+productName);
        sa.assertTrue(resultPg.getProductListSize()>0);
        ChainTestListener.log("navigate back to my account page");
        resultPg.navigateToMyAccountPage();
        myaccountPg.waitForPageLoad(2000);
        sa.assertAll();
          }
          @DataProvider
          public Object[][] productTestData(){
        return  new Object[][]{
                {"MacBook"},
                {"Apple Cinema 30\""},
                {"Samsung"}
        };
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


