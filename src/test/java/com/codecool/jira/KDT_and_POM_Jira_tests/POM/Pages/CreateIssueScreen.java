package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateIssueScreen {
    WebDriver driver;

    @FindBy(id="project-field")
    WebElement projectField;

    @FindBy(id="issuetype-field")
    WebElement issueTypeField;

    @FindBy(id="summary")
    WebElement summaryField;

    @FindBy(xpath="//div[contains(@class, 'jira-dialog-core-content')]")
    WebElement dialogScreen;

    @FindBy(xpath="//button[contains(.,'Cancel')]")
    WebElement cancelButton;

    @FindBy(id="create-issue-submit")
    WebElement submit;

    @FindBy(xpath="//div[@id='aui-flag-container']/div/div/a")
    WebElement notification;

    @FindBy(xpath="//*[@id=\"dialog-form\"]/div/div[2]/div[1]/div")
    WebElement errorMessage;

    public CreateIssueScreen(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void setProject(String project){
        projectField.sendKeys(project);
    }

    public String getProject(){
        return projectField.getAttribute("value");
    }

    public void setIssueType(String issueType){
        issueTypeField.sendKeys(issueType);
    }

    public String getIssueType(){
        return issueTypeField.getAttribute("value");
    }

    public void setSummary(String summary){
        summaryField.sendKeys(summary);
    }

    public void clickOutSide(){
        dialogScreen.click();
    }

    public void clickCancel(){
        cancelButton.click();
    }

    public void clickSubmit(){
        submit.click();
    }

    public void clickOnNotification(){
        notification.click();
    }

    public void clickProjectField(){
        projectField.click();
    }

    public void clickIssueTypeField(){
        issueTypeField.click();
    }

    public void clickSummaryField(){
        summaryField.click();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    public void setFields(String project, String issueType, String summary){
        clickProjectField();
        setProject("");
        setProject(project);
        clickOutSide();
        clickIssueTypeField();
        setIssueType("");
        setIssueType(issueType);
        clickOutSide();
        clickSummaryField();
        setSummary(summary);
    }
}
