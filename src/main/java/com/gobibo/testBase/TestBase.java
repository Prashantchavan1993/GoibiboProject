package com.gobibo.testBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestBase {
    protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
//    public static WebDriver driver;
    public static Properties prop;
    public static Logger log = Logger.getLogger(TestBase.class.getName());
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static DesiredCapabilities capabilities = new DesiredCapabilities();

    @BeforeSuite
    public void startReport(){
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/gobibo_Automation_Tests.html", true);
    }

    public TestBase(){
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
            prop = new Properties();
            try {
                prop.load(file);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void initialize() {
        if (prop.getProperty("browser").equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            WebDriver cd = new ChromeDriver();
            webDriver.set(cd);
        } else if (prop.getProperty("browser").equals("FireFox")){
            WebDriverManager.firefoxdriver().setup();
            WebDriver fd = new FirefoxDriver();
            webDriver.set(fd);
        } else if (prop.getProperty("browser").equals("remoteChrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--log-level=3");
            options.addArguments("--silent");

            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            try {
                WebDriver rwd = new RemoteWebDriver(new URL("url of node"),capabilities);
                webDriver.set(rwd);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        webDriver.get().manage().window().maximize();
        webDriver.get().get(prop.getProperty("testEnvironmentUrl"));
        webDriver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void extentClose(){
        extentReports.flush();
        extentReports.close();
    }
}
