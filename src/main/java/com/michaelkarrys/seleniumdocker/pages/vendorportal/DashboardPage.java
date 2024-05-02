package com.michaelkarrys.seleniumdocker.pages.vendorportal;

import com.michaelkarrys.seleniumdocker.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitEarningElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultsCountElement;

    @FindBy(css = "img.img-profile")
    private WebElement userProfilePictureElement;

    // prefer in order: id / name / css / linkText
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(monthlyEarningElement));
        return monthlyEarningElement.isDisplayed();
    }


    public String getMonthlyEarnings(){
        return monthlyEarningElement.getText();
    }

    public String getAnnualEarnings(){
        return annualEarningElement.getText();
    }

    public String getProfitMargin(){
        return profitEarningElement.getText();
    }

    public String getAvailableInventory(){
        return availableInventoryElement.getText();
    }

    public void searchOrderHistory(String keyword){
        searchInput.sendKeys(keyword);
    }

    public int getSearchResultsCount(){
        String resultsText = searchResultsCountElement.getText();
        String [] arr = resultsText.split(" ");
        // if no fifth element then an exception is thrown
        int count = Integer.parseInt(arr[5]);
        log.info("Result count: {}", count);

        return count;
    }

    public void logout(){
        userProfilePictureElement.click();
        wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        logoutLink.click();
        wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
        modalLogoutButton.click();
    }

}
