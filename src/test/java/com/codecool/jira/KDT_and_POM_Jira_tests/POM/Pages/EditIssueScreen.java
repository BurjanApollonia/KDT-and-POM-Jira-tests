package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class EditIssueScreen {
    WebDriver driver;

    @FindBy(id="summary")
    WebElement summary;

    @FindBy(id="edit-issue-submit")
    WebElement submitButton;

    @FindBy(xpath="(//button[@type='button'])[27]")
    WebElement cancelButton;

    @FindBy(xpath="//form[@id='dialog-form']/div/div/div/div")
    WebElement errorMessage;

    public EditIssueScreen(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void changeSummary(String message) {
        summary.clear();
        summary.sendKeys(message);
    }

    public void clickUpdateButton() {
        submitButton.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
