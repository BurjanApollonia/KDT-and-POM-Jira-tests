package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.CSVDataReaders;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseIssuePage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.EditIssueScreen;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.MalformedURLException;

public class EditIssueTest extends TestBase {
    BrowseIssuePage browseIssuePage;
    EditIssueScreen editIssueScreen;

    public static String[][] editCheck() throws IOException, CsvValidationException {
        String csvDataFilePath = "src/test/java/com/codecool/jira/KDT_and_POM_Jira_tests/resources/EditIssueKeys.csv";
        String[][] dataFromCSV = CSVDataReaders.getCSVData(csvDataFilePath, ',');
        return dataFromCSV;
    }

    @BeforeAll
    public static void start() {
        setup();
    }

    @BeforeEach
    public void loginToJira() throws MalformedURLException {
        launchBrowser();
        this.login();
        browseIssuePage = new BrowseIssuePage(driver);
        editIssueScreen = new EditIssueScreen(driver);
    }

    @MethodSource("editCheck")
    @ParameterizedTest
    public void checkIssueEditButton(String project, String issue) {
        Assertions.assertEquals("Edit", browseIssuePage.checkEditButton(project, issue));
    }

    @Test
    public void emptySummary() {
        driver.get("https://jira-auto.codecool.metastage.net/projects/MTP/issues/MTP-146");
        browseIssuePage.clickEditButton();
        editIssueScreen.changeSummary("");
        editIssueScreen.clickUpdateButton();
        Assertions.assertEquals("You must specify a summary of the issue.", editIssueScreen.getErrorMessage());
    }

    @Test
    public void cancelEditing() {
        driver.get("https://jira-auto.codecool.metastage.net/projects/MTP/issues/MTP-146");
        browseIssuePage.clickEditButton();
        editIssueScreen.changeSummary("This is a changed Summary");
        editIssueScreen.clickCancelButton();
        driver.switchTo().alert().accept();
        Assertions.assertEquals("Test", browseIssuePage.getSummary());
    }

    @Test
    public void editExistingIssue() {
        driver.get("https://jira-auto.codecool.metastage.net/projects/MTP/issues/MTP-146");
        browseIssuePage.clickEditButton();
        editIssueScreen.changeSummary("This is a changed Summary");
        editIssueScreen.clickUpdateButton();
        Assertions.assertEquals("This is a changed Summary", browseIssuePage.getSummary());
        browseIssuePage.clickEditButton();
        editIssueScreen.changeSummary("Test");
        editIssueScreen.clickUpdateButton();
        Assertions.assertEquals("Test", browseIssuePage.getSummary());
    }

    @AfterEach
    public void closeDriver() {
        driver.close();
    }
}
