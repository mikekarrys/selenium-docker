package com.michaelkarrys.seleniumdocker.vendorportal;

import com.michaelkarrys.seleniumdocker.AbstractTest;
import com.michaelkarrys.seleniumdocker.pages.vendorportal.DashboardPage;
import com.michaelkarrys.seleniumdocker.pages.vendorportal.LoginPage;
import com.michaelkarrys.seleniumdocker.util.Config;
import com.michaelkarrys.seleniumdocker.util.Constants;
import com.michaelkarrys.seleniumdocker.vendorportal.model.VendorPortalTestData;
import com.michaelkarrys.seleniumdocker.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(VendorPortalTest.class);

    LoginPage loginPage;
    DashboardPage dashboardPage;
    private VendorPortalTestData testData;

     @BeforeTest
     @Parameters("testDataPath")
     public void setPageObjects(String testDataPath){
        // Browser Version must match. Google Chrome for Testing
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
     }

    @Test
    public void loginTest(){
        //LoginPage loginPage = new LoginPage(driver);
//        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html#");
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        //DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarnings(), testData.monthlyEarnings());
        Assert.assertEquals(dashboardPage.getAnnualEarnings(), testData.annualEarnings());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        // order history search
        dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());

        // logout
        //dashboardPage.logout();
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
         dashboardPage.logout();
         Assert.assertTrue(loginPage.isAt());
    }


}
