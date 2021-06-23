package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.CSVDataReaders;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseIssuePage;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.MalformedURLException;

public class BrowseIssueTest extends TestBase {
    BrowseIssuePage issue;

    public static String[][] dp() throws IOException, CsvValidationException {
        String csvDataFilePath = "src/test/java/com/codecool/jira/KDT_and_POM_Jira_tests/resources/IssueKeys.csv";
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
        issue = new BrowseIssuePage(driver);
    }

    @MethodSource("dp")
    @ParameterizedTest
    public void browseIssue(String key) {
        Assertions.assertEquals(key, issue.browseIssue(key));
    }

    @Test
    public void browseNonExistentIssue() {
        driver.get("https://jira-auto.codecool.metastage.net/browse/COALA-5");
        Assertions.assertEquals("You can't view this issue", issue.issueNotAvailable());
    }

    @AfterEach
    public void quitDriver(){
        driver.quit();
    }
}