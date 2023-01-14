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
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--log-level=3");
            options.addArguments("--silent");

            WebDriver cd = new ChromeDriver(options);
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
            options.addArguments("--headless");
            options.addArguments("--user-agent='Mozilla/5.0 (Windows NT 10.0;Win64) AppleWebkit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36'");
            options.addArguments("--accept-language='en-US,en;q=0.9,fr;q=0.8,es;q=0.7'");
            options.addArguments("--accept-encoding='gzip, deflate, br'");
            options.addArguments("--accept='text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'");


            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            try {
                WebDriverManager.chromedriver().setup();
                WebDriver rwd = new ChromeDriver(options);

//                WebDriver rwd = new RemoteWebDriver(new URL("url of node"),capabilities);
                webDriver.set(rwd);
            } catch (Exception e) {
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
