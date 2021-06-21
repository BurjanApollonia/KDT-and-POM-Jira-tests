package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.*;
import java.io.IOException;

public class LoginPageTest extends TestBase{

    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void goToURL() throws IOException {
        operation = new UIOperation();
        operation.goToUrl("loginPageURL");
    }

    @Test
    public void invalidPassword() throws Exception {
        operation.setText("username", "id", username);
        operation.setText("password", "id", "invalid");
        operation.click("loginFormSubmit", "id");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.", operation.getText("loginPageMessage", "xpath"));
        operation.setText("username", "id", username);
        operation.setText("password", "id", password);
        operation.click("loginFormSubmit", "id");
        operation.click("profilePicture", "xpath");
        operation.click( "viewProfile", "id");
        Assertions.assertEquals(username, operation.getText("name", "id"));
        operation.click( "profile", "xpath");
        operation.click( "logoutButton", "id");
    }

    @Test
    public void successfulLoginAndLogout() throws Exception{
        operation.setText("username", "id", username);
        operation.setText( "password", "id", password);
        operation.click("loginFormSubmit", "id");
        operation.click("profilePicture", "xpath");
        operation.click("viewProfile", "id");
        Assertions.assertEquals(username, operation.getText("name", "id"));
        operation.click("profile", "xpath");
        operation.click("logoutButton", "id");
    }

    @AfterEach
    public void quit() {
        UIOperation.quitDriver();
    }
}
