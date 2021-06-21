package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.CSVDataReaders;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseIssuePage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.CreateIssueScreen;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.NavBar;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;

public class CreateIssueTest extends TestBase {
    CreateIssueScreen screen;
    NavBar navBar;
    BrowseIssuePage issue;

    public static String[][] dp() throws IOException, CsvValidationException {
        String csvDataFilePath = "src/test/java/com/codecool/jira/KDT_and_POM_Jira_tests/resources/ProjectAndIssueType.csv";
        String[][] dataFromCSV = CSVDataReaders.getCSVData(csvDataFilePath, ',');
        return dataFromCSV;
    }

    public static String[][] subtask() throws IOException, CsvValidationException {
        String csvDataFilePath = "src/test/java/com/codecool/jira/KDT_and_POM_Jira_tests/resources/SubtaskIssueKeys.csv";
        String[][] dataFromCSV = CSVDataReaders.getCSVData(csvDataFilePath, ',');
        return dataFromCSV;
    }

    private CreateIssueScreen openCreateIssueScreen(String project, String issueType, String summary){
        navBar = new NavBar(driver);
        navBar.clickCreateButton();
        screen = new CreateIssueScreen(driver);
        screen.setFields(project, issueType, summary);
        return screen;
    }

    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void loginToJira(){
        this.launchBrowser();
        this.login();
    }

    @Test
    public void testCreateIssue(){
        String project = "Main Testing Project (MTP)";
        String issueType = "Bug";
        String summary = "Test create issue";
        screen = openCreateIssueScreen(project, issueType, summary);
        screen.clickSubmit();
        screen.clickOnNotification();
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals(summary, issue.getSummary());
        Assertions.assertEquals(issueType, issue.getIssueType());
        Assertions.assertEquals("Main Testing Project", issue.getProjectName());
    }

    @Test
    public void createIssueWithoutSummary(){
        String errorMessage = "You must specify a summary of the issue.";
        screen = openCreateIssueScreen("Main Testing Project (MTP)", "Bug", "");
        screen.clickSubmit();
        Assertions.assertEquals(errorMessage, screen.getErrorMessage());
    }

    @Test
    public void cancelButton(){
        screen = openCreateIssueScreen("Main Testing Project (MTP)", "Story", "ID 12345");
        screen.clickCancel();
        driver.switchTo().alert().accept();
        driver.get("https://jira-auto.codecool.metastage.net/issues/?jql=summary%20~%20%27ID%2012345%27%20AND%20createdDate%20%3E%3D%20startOfDay()");
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals("No issues were found to match your search", issue.getErrorMessage());
    }

    @MethodSource("dp")
    @ParameterizedTest
    public void createIssueInProject(String project, String issueType){
        screen = openCreateIssueScreen(project, issueType, "New issue");
        Assertions.assertEquals(project, screen.getProject());
        Assertions.assertEquals(issueType, screen.getIssueType());
    }

    @MethodSource("subtask")
    @ParameterizedTest
    public void createSubtaskInIssue(String key){
        issue = new BrowseIssuePage(driver);
        Assertions.assertEquals("Create Subtask : " + key, issue.createSubtaskInIssue(key));
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }
}
