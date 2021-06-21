package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class EditIssueTest extends TestBase {

    @BeforeAll
    public static void start() {
        setup();
    }

    @BeforeEach
    public void login() throws Exception {
        operation = new UIOperation();
        operation.goToUrl("url");
        operation.setText("username", "id", username);
        operation.setText("password", "id", password);
        operation.click("loginbutton", "id");
    }

    @Test
    public void editableToucanIssues() throws Exception {
        operation.goToUrl("toucan1");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
        operation.goToUrl("toucan2");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
        operation.goToUrl("toucan3");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
    }

    @Test
    public void editableCoalaIssues() throws Exception {
        operation.goToUrl("coala1");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
        operation.goToUrl("coala2");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
        operation.goToUrl("coala3");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
    }

    @Test
    public void editableJetiIssues() throws Exception {
        operation.goToUrl("jeti1");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
        operation.goToUrl("jeti2");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
        operation.goToUrl("jeti3");
        Assertions.assertEquals("Edit", operation.getText("editButton", "xpath"));
    }

    @Test
    public void emptySummary() throws Exception {
        operation.goToUrl("mtp146");
        operation.click("editButton", "xpath");
        operation.clear("summary", "id");
        operation.setText("summary", "id", "");
        operation.click("editSubmit", "id");
        Assertions.assertEquals("You must specify a summary of the issue.",
                operation.getText("dialogError", "xpath"));
    }

    @Test
    public void cancelEditing() throws Exception {
        operation.goToUrl("mtp146");
        operation.click("editButton", "xpath");
        operation.clear("summary", "id");
        operation.setText("summary", "id", "This is a changed Summary");
        operation.click("cancelEdit", "xpath");
        operation.acceptAlert();
        Assertions.assertEquals("Test", operation.getText("summaryValue", "id"));
    }

    @Test
    public void editExistingIssue() throws Exception {
        operation.goToUrl("mtp146");
        operation.click("editButton", "xpath");
        operation.clear("summary", "id");
        operation.setText("summary", "id", "This is a changed Summary");
        operation.click("editSubmit", "id");
        Assertions.assertEquals("This is a changed Summary", operation.getText("summaryValue", "id"));
        operation.click("editButton", "xpath");
        operation.clear("summary", "id");
        operation.setText("summary", "id", "Test");
        operation.click("editSubmit", "id");
        Assertions.assertEquals("Test", operation.getText("summaryValue", "id"));
    }

    @AfterEach
    public void quit() {
        UIOperation.quitDriver();
    }
}
