package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class JiraLogin {
    WebDriver driver;

    @FindBy(id="login-form-username")
    WebElement username;

    @FindBy(id="login-form-password")
    WebElement password;

    @FindBy(id="login")
    WebElement loginButton;

    @FindBy(xpath="//div[@id='usernameerror']/p")
    WebElement wrongLoginMessage;

    public JiraLogin(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String userName){
        username.sendKeys(userName);
    }

    public void setPassword(String strPassword){
        password.sendKeys(strPassword);
    }

    public void clickLogin(){
        loginButton.click();
    }

    public String getErrorMessage(){
        return wrongLoginMessage.getText();
    }

    public void login(String strUserName,String strPassword){
        this.setUserName(strUserName);
        this.setPassword(strPassword);
        this.clickLogin();
    }
}
