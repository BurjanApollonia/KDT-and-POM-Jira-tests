package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.*;

public class BrowseProjectTest extends TestBase {

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
    public void browseToucan() throws Exception {
        operation.goToUrl("toucanURL");
        Assertions.assertEquals("TOUCAN", operation.getText("projectName", "xpath"));
    }

    @Test
    public void browseCoala() throws Exception {
        operation.goToUrl("coalaURL");
        Assertions.assertEquals("COALA", operation.getText("projectName", "xpath"));
    }

    @Test
    public void browseJeti() throws Exception {
        operation.goToUrl("jetiURL");
        Assertions.assertEquals("JETI", operation.getText("projectName", "xpath"));
    }

    @Test
    public void browseMTP() throws Exception {
        operation.goToUrl("mtpURL");
        Assertions.assertEquals("MTP", operation.getText("projectName", "xpath"));
    }

    @Test
    public void browseLion() throws Exception {
        operation.goToUrl("lionURL");
        Assertions.assertEquals("You can't view this project", operation.getText("lionMessage", "xpath"));
    }

    @AfterEach
    public void quit() {
        UIOperation.quitDriver();
    }
}
