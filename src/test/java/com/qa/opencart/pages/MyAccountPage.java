package com.qa.opencart.pages;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyAccountPage extends WebDriverUtils{
    private Logger log = LogManager.getLogger(MyAccountPage.class);

    //constructor
    public MyAccountPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[normalize-space()='My Account']")
    private WebElement myAccountMenu;

    @FindBy(css="input[name='search']")
    private WebElement searchEditbox;

    @FindBy(xpath="//button[@class='btn btn-default btn-lg']")
    private WebElement searchTorchIcon;

    @FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
    private WebElement logoutLink;
    @FindBy(css="body div[id='account-account'] ul[class='breadcrumb'] li:nth-child(1) a:nth-child(1)")
    private WebElement accountBreadCrumb;
    @FindBy(css="#content h2")
    private List<WebElement> myAccountHeaderList;
    @FindBy(xpath="//div[@id='top-links']/ul/li[2]/ul/li")
    private List<WebElement> myAccountMenuOptionList;
    @FindBy(xpath="//i[@class='fa fa-home']")
    private WebElement homeIcon;
      public String getMyAccountPageTitle(){
        return getTitle();
    }

    public String getMyAccountPageUrl(){
        return waitForUrlContains(Constants.MY_ACCOUNT_PAGE_FRACTION_URL);
    }

    public boolean isSearchEditboxExists(){
        return isDisplayed(searchEditbox);
    }

    public void clickMyAccountMenu() throws InterruptedException{
       try{
           ChainTestListener.log("click on My account menu");
           click(myAccountMenu);
       }catch(NoSuchElementException ex){
           ChainTestListener.log("Unable to click on My account menu");
           ex.printStackTrace();
       }
}

public boolean isLogoutExists() throws InterruptedException{
    clickMyAccountMenu();
    return isDisplayed(logoutLink);
}

public void clickLogoutLink() throws InterruptedException{
        try{
            if(isLogoutExists()){
                ChainTestListener.log("click on logout link under myAccount menu");
                click(logoutLink);
            }
        }catch(NoSuchElementException ex){
            ex.printStackTrace();
        }

}

    public void navigateToHomePage() throws InterruptedException{
       try{
           ChainTestListener.log("click on Home icon");
           click(homeIcon);
       }catch(NoSuchElementException ex){
           ChainTestListener.log("Unable to click Home icon");
       }
    }

    public List<String> getMyAccountMenuOptionList(){
       List<String>myAccountMenuOptionsTextList = new ArrayList<>();
       try{
         clickMyAccountMenu();
         for(WebElement option:myAccountMenuOptionList){
             String text = option.getText();
             myAccountMenuOptionsTextList.add(text);
         }
       }catch(Exception ex){
          ex.printStackTrace();
       }
        return myAccountMenuOptionsTextList;
    }


    public List<String> getMyAccountHeaderOptionsList(){
        List<String>myAccountHeaderOptionsTextList = new ArrayList<>();
        try{

            for(WebElement option:myAccountHeaderList){
                String text = option.getText();
                myAccountHeaderOptionsTextList.add(text);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return myAccountHeaderOptionsTextList;
    }

    public ResultsPage doProductSearch(String productName) throws InterruptedException{
         ChainTestListener.log("searching for the product: "+productName);
         try{
            if(isSearchEditboxExists()){
                ChainTestListener.log("type the product in search field");
                type(searchEditbox,productName);
                ChainTestListener.log("click on search torch icon");
                click(searchTorchIcon);
            }
         }catch(NoSuchElementException e){
             ChainTestListener.log("unable to search for the product: as search is not present");
         }
       return new ResultsPage(driver);
    }

    public void pressEscapeKey(){
          Actions act = new Actions(driver);
          act.sendKeys(Keys.ESCAPE).perform();
    }

}
