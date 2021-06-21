package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.*;

public class BrowseIssueTest extends TestBase {
    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void openBrowser() throws Exception {
        launchBrowser();
        operation.login(username, password);
    }

    @Test
    public void browseIssue() throws Exception {
        operation.browseIssue("MTP-146");
    }

    @Test
    public void browseNonExistentIssue() throws Exception {
        operation.goToCompoundUrl("browseUrlBase", "COALA-5");
        Assertions.assertEquals("You can't view this issue", operation.getText("issueNotAvailable", "xpath"));
    }

    @Test
    public void browseCoalaIssue1() throws Exception {
        operation.browseIssue("COALA-1");
    }

    @Test
    public void browseCoalaIssue2() throws Exception {
        operation.browseIssue("COALA-2");
    }

    @Test
    public void browseCoalaIssue3() throws Exception {
        operation.browseIssue("COALA-3");
    }

    @Test
    public void browseJetiIssue1() throws Exception {
        operation.browseIssue("JETI-1");
    }

    @Test
    public void browseJetiIssue2() throws Exception {
        operation.browseIssue("JETI-2");
    }

    @Test
    public void browseJetiIssue3() throws Exception {
        operation.browseIssue("JETI-3");
    }

    @Test
    public void browseToucanIssue1() throws Exception {
        operation.browseIssue("TOUCAN-1");
    }

    @Test
    public void browseToucanIssue2() throws Exception {
        operation.browseIssue("TOUCAN-2");
    }

    @Test
    public void browseToucanIssue3() throws Exception {
        operation.browseIssue("TOUCAN-3");
    }

    @AfterEach
    public void closeBrowser(){
        UIOperation.quitDriver();
    }
}
