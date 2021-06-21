package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class BrowseProjectPage {
    WebDriver driver;

    @FindBy(xpath="//section[@id='summary-body']/div/div[2]/dl/dd[2]")
    WebElement projectKey;

    @FindBy(xpath="//main[@id='main']/h1")
    WebElement errorMessage;

    public BrowseProjectPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public String browseProject(String projectName){
        driver.get("https://jira-auto.codecool.metastage.net/projects/" + projectName + "/summary");
        return projectKey.getText();
    }

    public String browseNonexistentProject(String projectName){
        driver.get("https://jira-auto.codecool.metastage.net/projects/" + projectName + "/summary");
        return errorMessage.getText();
    }
}
