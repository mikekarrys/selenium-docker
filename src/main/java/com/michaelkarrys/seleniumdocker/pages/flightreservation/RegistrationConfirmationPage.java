package com.michaelkarrys.seleniumdocker.pages.flightreservation;

import com.michaelkarrys.seleniumdocker.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    @FindBy(css ="#registration-confirmation-section p b")
    private WebElement firstNameConfirmation;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
        return this.goToFlightsSearchButton.isDisplayed();
    }

    public String getFirstNameConfirmation(){
        return firstNameConfirmation.getText();
    }

    public void setGoToFlightsSearch(){
        goToFlightsSearchButton.click();;
    }
}
