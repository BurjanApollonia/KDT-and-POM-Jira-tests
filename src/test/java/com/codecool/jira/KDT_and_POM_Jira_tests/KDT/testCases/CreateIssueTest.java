package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.*;

public class CreateIssueTest extends TestBase{

    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void login() throws Exception {
        launchBrowser();
        operation.login(username, password);
    }

    @Test
    public void createIssueGeneral() throws Exception {
        String project = "Main Testing Project (MTP)";
        String issueType = "Bug";
        String summary = "Test create issue";
        operation.openCreateIssueScreen(project, issueType, summary);
        operation.clickCreateIssueSubmit();
        operation.click("createdIssueNotification", "xpath");
        Assertions.assertEquals(summary, operation.getText("summaryValue", "id"));
        Assertions.assertEquals(issueType, operation.getText("issueTypeValue", "id"));
        Assertions.assertEquals("Main Testing Project",
                operation.getText("projectNameValue", "id"));
    }

    @Test
    public void createIssueWithoutSummary() throws Exception {
        String errorMessage = "You must specify a summary of the issue.";
        operation.openCreateIssueScreen("Main Testing Project (MTP)", "Story", "" );
        operation.clickCreateIssueSubmit();
        Assertions.assertEquals(errorMessage, operation.getText("errorMessage", "css"));
        operation.clickCancelIssue();
        operation.acceptAlert();
    }

    @Test
    public void cancelButton() throws Exception {
        operation.openCreateIssueScreen("Main Testing Project (MTP)",
                "Story", "ID 12345");
        operation.clickCancelIssue();
        operation.acceptAlert();
        operation.goToUrl("issueID12345");
        String error = "No issues were found to match your search";
        Assertions.assertEquals(error, operation.getText("issueNotFound", "css"));
    }

    @Test
    public void createStoryInCOALA() throws Exception {
        operation.createIssueInProject("COALA project (COALA)", "Story", "COALA Story");
    }

    @Test
    public void createBugInCOALA() throws Exception {
        operation.createIssueInProject("COALA project (COALA)", "Bug", "Coala bug" );
    }

    @Test
    public void createTaskInCOALA() throws Exception {
        operation.createIssueInProject("COALA project (COALA)", "Task", "Coala task" );
    }

    @Test
    public void createStoryInJETI() throws Exception {
        operation.createIssueInProject("JETI project (JETI)", "Story", "Jeti story");
    }

    @Test
    public void createBugInJETI() throws Exception {
        operation.createIssueInProject("JETI project (JETI)", "Bug", "Jeti bug");
    }

    @Test
    public void createTaskInJETI() throws Exception {
        operation.createIssueInProject("JETI project (JETI)", "Task", "Jeti task");
    }

    @Test
    public void createStoryInTOUCAN() throws Exception {
        operation.createIssueInProject("TOUCAN project (TOUCAN)", "Story", "Toucan story");
    }

    @Test
    public void createBugInTOUCAN() throws Exception {
        operation.createIssueInProject("TOUCAN project (TOUCAN)", "Bug", "Toucan bug");
    }

    @Test
    public void createTaskInTOUCAN() throws Exception {
        operation.createIssueInProject("TOUCAN project (TOUCAN)", "Task", "Toucan task");
    }

    @Test
    public void createSubtaskInTOUCAN() throws Exception {
        operation.createSubtask("TOUCAN-1");
    }

    @Test
    public void createSubtaskInJETI() throws Exception {
        operation.createSubtask("JETI-1");
    }

    @Test
    public void createSubtaskInCOALA() throws Exception {
        operation.createSubtask("COALA-13");
    }

    @AfterEach
    public void closeDriver(){
        UIOperation.quitDriver();
    }
}
