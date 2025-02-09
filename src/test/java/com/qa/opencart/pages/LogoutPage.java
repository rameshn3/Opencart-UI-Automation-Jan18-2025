package com.qa.opencart.pages;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends WebDriverUtils{
    private Logger log = LogManager.getLogger(LogoutPage.class);

    //constructor
    public LogoutPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//h1[normalize-space()='Account Logout']")
    private WebElement accountLogoutHeader;

    @FindBy(xpath="//a[normalize-space()='Continue']")
    private WebElement continueBtn;

    @FindBy(css="#content p")
    private WebElement accountLoggedOffMsg;

    @FindBy(xpath="//i[@class='fa fa-home']")
    private WebElement homeIcon;

    @FindBy(xpath="//ul[@class='breadcrumb']//a[normalize-space()='Logout']")
    private WebElement logoutBreadCrumb;


    public String getLogoutPageTitle(){
        return getTitle();
    }

    public String getLogoutPageUrl(){
        return waitForUrlContains(Constants.LOGOUT_PAGE_FRACTION_URL);
    }

    public boolean isAccountLogoutHeaderExists(){
        return isDisplayed(accountLogoutHeader);
    }

    public boolean isAccountLoggedOffMsgExists(){
        return isDisplayed(accountLoggedOffMsg);
    }

    public boolean isLogoutBreadCrumbExists(){
        return isDisplayed(logoutBreadCrumb);
    }

    public void clickContinueBtn() throws InterruptedException{
       click(continueBtn);
}

    public void navigateToHomePage() throws InterruptedException{
        click(homeIcon);
    }

}
