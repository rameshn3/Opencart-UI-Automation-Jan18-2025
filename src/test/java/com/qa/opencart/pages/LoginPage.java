package com.qa.opencart.pages;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends WebDriverUtils{
    private Logger log = LogManager.getLogger(LoginPage.class);

    //constructor
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//h2[normalize-space()='New Customer']")
    private WebElement newCustomerHeader;

    @FindBy(css="a[class='btn btn-primary']")
    private WebElement newCustomerContinueBtn;

    @FindBy(xpath="//h2[normalize-space()='Returning Customer']")
    private WebElement returningCustomerHeader;

    @FindBy(id="input-email")
    private WebElement emailAddressEditbox;
    @FindBy(name="password")
    private WebElement passwordEditbox;
@FindBy(linkText="Forgotten Password")
private WebElement forgottenPasswordLink;
    @FindBy(xpath="//input[@value='Login']")
    private WebElement loginBtn;
    @FindBy(xpath="//i[@class='fa fa-home']")
    private WebElement homeIcon;

    @FindBy(xpath="//ul[@class='breadcrumb']//a[normalize-space()='Login']")
    private WebElement loginBreadCrumb;

    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
    private WebElement emptyCredErrorMsg;
    public String getLoginPageTitle(){
        return getTitle();
    }

    public String getLoginPageUrl(){
        return waitForUrlContains(Constants.LOGIN_PAGE_FRACTION_URL);
    }

    public boolean isLoginBreadCrumbExists(){
        return isDisplayed(loginBreadCrumb);
    }

    public boolean isNewCustomerHeaderExists(){
        return isDisplayed(newCustomerHeader);
    }

    public boolean isReturningCustomerExists(){
        return isDisplayed(returningCustomerHeader);
    }

    public void clickNewCustomerContinueBtn() throws InterruptedException{
       click(newCustomerContinueBtn);
}

public void navigateToForgottenPasswordPage() throws InterruptedException{
        click(forgottenPasswordLink);
}

public void doLogin(String userName, String pwd) throws InterruptedException{
        type(emailAddressEditbox,userName);
        type(passwordEditbox,pwd);
        click(loginBtn);
}

    public void navigateToHomePage() throws InterruptedException{
        click(homeIcon);
    }

    public String getEmptyCredentialErrorMsg(){
        return emptyCredErrorMsg.getText();
    }

}
