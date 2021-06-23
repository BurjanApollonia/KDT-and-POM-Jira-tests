package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public abstract class TestBase {
    public static WebDriver driver;
    public static String username;
    public static String password;
    public static Properties browserProps;
    public JiraLogin login;
    public static String nodeUrl;
    public String baseUrl = "https://seleniumhub.codecool.metastage.net/";

    public static void setup() {
        browserProps = new Properties();
        try {
            String browserConfigPath = "settings.properties";
            browserProps.load(new FileInputStream(browserConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        username = browserProps.getProperty("username");
        password = browserProps.getProperty("password");
    }

    public static void launchBrowser() throws MalformedURLException {
        nodeUrl = System.getenv("NODE_URL");
        System.out.println(nodeUrl);
        String browserName = System.getenv("BROWSER");
        System.out.println(browserName);
        if (browserName.equals("Chrome")){
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("87.0.4280.66");
            driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        } else if (browserName.equals("Firefox")){
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            capabilities.setVersion("82.0.3");
            driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        }
        /*
        String webdriverPath = browserProps.getProperty("webdriver");
        System.setProperty("webdriver.gecko.driver", webdriverPath);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        driver = new FirefoxDriver(firefoxOptions);
         */
    }

    public void login(){
        driver.get("https://jira-auto.codecool.metastage.net");
        login = new JiraLogin(driver);
        login.login(username, password);
    }

}
