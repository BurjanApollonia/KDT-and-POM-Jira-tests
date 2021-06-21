package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LogoutPage {
    WebDriver driver;

    @FindBy(xpath="//main[@id='main']/div/div/p/strong")
    WebElement logoutMessage;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public String getMessage() {
        return logoutMessage.getText();
    }
}
