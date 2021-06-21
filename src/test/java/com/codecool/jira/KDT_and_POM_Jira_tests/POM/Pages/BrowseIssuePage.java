package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class BrowseIssuePage {
    WebDriver driver;

    @FindBy(id="project-name-val")
    WebElement projectName;

    @FindBy(id="type-val")
    WebElement issueType;

    @FindBy(id="summary-val")
    WebElement summary;

    @FindBy(xpath="//*[@id=\"edit-issue\"]")
    WebElement editButton;

    @FindBy(id="opsbar-operations_more")
    WebElement moreOptions;

    @FindBy(id="create-subtask")
    WebElement createSubtask;

    @FindBy(xpath="//*[@id=\"main\"]/div/div[2]/div/div/div/div/div/div/h2")
    WebElement issueNotFound;

    @FindBy(xpath = "//section[@id='create-subtask-dialog']/header/h2")
    WebElement subtaskScreenHeader;

    @FindBy(id="key-val")
    WebElement key;

    @FindBy(xpath="//*[@id=\"issue-content\"]/div/div/h1")
    WebElement issueNotAvailable;

    public BrowseIssuePage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public String getProjectName(){
        return projectName.getText();
    }

    public String getIssueType(){
        return issueType.getText();
    }

    public String getSummary(){
        return summary.getText();
    }

    public void clickEditButton(){
        editButton.click();
    }

    public void clickMoreOptions(){
        moreOptions.click();
    }

    public void clickCreateSubtask(){
        createSubtask.click();
    }

    public String getErrorMessage(){
        return issueNotFound.getText();
    }

    public String issueNotAvailable(){
        return issueNotAvailable.getText();
    }

    public String browseIssue(String key){
        driver.get("https://jira-auto.codecool.metastage.net/browse/" + key);
        return this.key.getText();
    }

    public String createSubtaskInIssue(String key){
        driver.get("https://jira-auto.codecool.metastage.net/browse/" + key);
        clickMoreOptions();
        clickCreateSubtask();
        return subtaskScreenHeader.getText();
    }

    public String checkEditButton(String project, String issue){
        driver.get("https://jira-auto.codecool.metastage.net/projects/" + project + "/issues/" + issue);
        return editButton.getText();
    }
}
