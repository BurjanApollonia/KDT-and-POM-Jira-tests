package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.POM.CSVDataReaders;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseIssuePage;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.BrowseProjectPage;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;

public class BrowseProjectTest extends TestBase {
    BrowseProjectPage browseProjectPage;

    public static String[][] browse() throws IOException, CsvValidationException {
        String csvDataFilePath = "src/test/java/com/codecool/jira/KDT_and_POM_Jira_tests/resources/ProjectKeys.csv";
        String[][] dataFromCSV = CSVDataReaders.getCSVData(csvDataFilePath, ',');
        return dataFromCSV;
    }

    @BeforeAll
    public static void start() {
        setup();
    }

    @BeforeEach
    public void loginToJira() {
        launchBrowser();
        login();
        browseProjectPage = new BrowseProjectPage(driver);
    }

    @MethodSource("browse")
    @ParameterizedTest
    public void browseIssue(String key) {
        Assertions.assertEquals(key, browseProjectPage.browseProject(key));
    }

    @Test
    public void browseNonexistentProject() {
        Assertions.assertEquals("You can't view this project",
                browseProjectPage.browseNonexistentProject("LION"));
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }
}
