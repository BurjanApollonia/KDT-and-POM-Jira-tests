package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar {
    WebDriver driver;

    @FindBy(id="create_link")
    WebElement createButton;

    @FindBy(xpath="//a[@id='header-details-user-fullname']/span")
    WebElement profilePicture;

    @FindBy(id="view_profile")
    WebElement viewProfile;

    @FindBy(id="log_out")
    WebElement logoutButton;

    public NavBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCreateButton(){
        createButton.click();
    }

    public void clickProfilePicture(){
        profilePicture.click();
    }

    public void clickViewProfile(){
        viewProfile.click();
    }

    public void clickLogoutButton(){
        logoutButton.click();
    }
}
