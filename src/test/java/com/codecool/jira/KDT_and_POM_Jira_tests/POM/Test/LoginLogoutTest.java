package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLogin;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.LogoutPage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.NavBar;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.ProfilePage;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;

public class LoginLogoutTest extends TestBase {
    NavBar navBar;
    ProfilePage profilePage;
    LogoutPage logoutPage;

    @BeforeAll
    public static void start() throws MalformedURLException {
        setup();
    }

    @BeforeEach
    public void goToUrl() throws MalformedURLException {
        launchBrowser();
        driver.get("https://jira-auto.codecool.metastage.net");
    }

    @Test
    public void emptyCredentials() {
        login = new JiraLogin(driver);
        login.clickLogin();
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.",
                login.getErrorMessage());
    }

    @Test
    public void notRegisteredUser(){
        login = new JiraLogin(driver);
        login.login("invalid", "invalid");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.",
                login.getErrorMessage());
    }

    @Test
    public void invalidPassword() {
        login = new JiraLogin(driver);
        login.login("invalid", "invalid");
        Assertions.assertEquals("Sorry, your username and password are incorrect - please try again.",
                login.getErrorMessage());
        login.login(username, password);
        navBar = new NavBar(driver);
        navBar.clickProfilePicture();
        navBar.clickViewProfile();
        profilePage = new ProfilePage(driver);
        Assertions.assertEquals(username, profilePage.getUserName());
        navBar.clickProfilePicture();
        navBar.clickLogoutButton();
    }

    @Test
    public void successfulLogin() {
        login = new JiraLogin(driver);
        login.login(username, password);
        navBar = new NavBar(driver);
        navBar.clickProfilePicture();
        navBar.clickViewProfile();
        profilePage = new ProfilePage(driver);
        Assertions.assertEquals(username, profilePage.getUserName());
        navBar.clickProfilePicture();
        navBar.clickLogoutButton();
        logoutPage = new LogoutPage(driver);
        Assertions.assertEquals("You are now logged out. Any automatic login has also been stopped.",
                logoutPage.getMessage());
    }

    @AfterEach
    public void quit(){
        driver.quit();
    }
}