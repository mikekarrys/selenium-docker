package com.michaelkarrys.seleniumdocker;

import com.google.common.util.concurrent.Uninterruptibles;
import com.michaelkarrys.seleniumdocker.listener.TestListener;
import com.michaelkarrys.seleniumdocker.pages.vendorportal.DashboardPage;
import com.michaelkarrys.seleniumdocker.pages.vendorportal.LoginPage;
import com.michaelkarrys.seleniumdocker.util.Config;
import com.michaelkarrys.seleniumdocker.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public abstract class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
    //@Parameters({"browser"})
    //public void setDriver(String browser) throws MalformedURLException {
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        // Browser Version must match. Google Chrome for Testing
        //WebDriverManager.chromedriver().setup();
        driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))?
                getRemoteDriver():getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, driver);
      }

     private WebDriver getRemoteDriver() throws MalformedURLException {
        // http://localhost:4444/wd/hub
         Capabilities capabilities = new ChromeOptions();
         if(Constants.FIREFOX.equalsIgnoreCase((Config.get(Constants.BROWSER)))){
             capabilities = new FirefoxOptions();
         }
         String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
         String hubHost = Config.get(Constants.GRID_HUB_HOST);
         String url = String.format(urlFormat, hubHost);
         log.info("grid url: {}", url);
         return new RemoteWebDriver(new URL(url),capabilities);
     }

     private WebDriver getLocalDriver(){
         WebDriverManager.chromedriver().clearDriverCache().setup();
         WebDriverManager.chromedriver().setup();
         return driver = new ChromeDriver();
     }

    @AfterTest
    public void quitDriver(){
        driver.quit();
    }

//    @AfterMethod
//    public void sleep(){
//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
//    }

}
