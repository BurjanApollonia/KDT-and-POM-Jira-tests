package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLoginPage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.LogoutPage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.NavBar;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.ProfilePage;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;

public class LoginPageTest extends TestBase {
    JiraLoginPage login;
    NavBar navBar;
    ProfilePage profilePage;
    LogoutPage logoutPage;

    @BeforeAll
    public static void start() throws MalformedURLException {
        setup();
        launchBrowser();
    }

    @BeforeEach
    public void goToUrl(){
        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
    }

    @Test
    public void invalidPassword() {
        login = new JiraLoginPage(driver);
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
        login = new JiraLoginPage(driver);
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
