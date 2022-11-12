package com.gobibo.pages;

import com.gobibo.testBase.TestBase;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightsPage extends TestBase {
    WebDriver driver;

    @FindBy(xpath = "//span[text()='From']/following-sibling::input")
    WebElement fromTextBox;

    @FindBy(xpath = "//span[text()='From']/following-sibling::p")
    WebElement fromCity;

    @FindBy(xpath = "//span[text()='To']/following-sibling::p")
    WebElement toCity;

    @FindBy(xpath = "//span[text()='To']/following-sibling::input")
    WebElement toTextBox;

    @FindBy(xpath = "//span[contains(text(),'Done')]")
    WebElement doneBtnOnCalender;

    @FindBy(xpath = "//h2[text()='Domestic and International Flights']/following-sibling::div//a[contains(text(),'Done')]")
    WebElement doneBtnOnTravAndClass;

    @FindBy(xpath = "//span[contains(text(),'SEARCH FLIGHTS')]")
    WebElement searchFlightsBtn;

    @FindBy(xpath = "//span[text()='DURATION']")
    WebElement durationFilter;

    @FindBy(xpath = "(//button[text()='VIEW FARES'])[1]")
    WebElement firstRowViewFareButton;

    @FindBy(xpath = "(//input[@value='BOOK'])[1]")
    WebElement bookBtn;

    @FindBy(xpath = "//span[contains(text(), 'TICKET DETAILS')]")
    WebElement ticketDetails;

    @FindBy(xpath = "//div[contains(text(), 'FARE SUMMARY')]")
    WebElement fareSummary;

    @FindBy(id = "autoSuggest-list")
    WebElement autoSuggest;

    public FlightsPage() {
        PageFactory.initElements(webDriver.get(), this);
        driver = webDriver.get();
    }

    public void selectFromLocation(String location) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(fromCity));
        fromCity.click();
        fromTextBox.sendKeys(location);
        wait.until(ExpectedConditions.visibilityOf(autoSuggest));
        driver.findElement(By.xpath("//ul[@id='autoSuggest-list']//span[contains(text(),'"+location+"')]")).click();
    }

    public void selectToLocation(String location) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(toCity));
        toTextBox.sendKeys(location);
        wait.until(ExpectedConditions.visibilityOf(autoSuggest));
        driver.findElement(By.xpath("//ul[@id='autoSuggest-list']//span[contains(text(),'"+location+"')]")).click();
    }

    public void clickDoneOnCalender() {
        doneBtnOnCalender.click();
    }

    public void clickDoneOnTravellersAndClass() {
        doneBtnOnTravAndClass.click();
    }

    public void clickSearchFlightsBtn() {
        searchFlightsBtn.click();
    }

    public void selectFromDateOnCalender(String date) {
        driver.findElement(By.xpath("//div[contains(@aria-label,'"+ date +"')]")).click();
    }

    public void clickOnDurationFilter() {
        durationFilter.click();
    }

    public void clickOnViewFareButton() {
        firstRowViewFareButton.click();
    }

    public void clickOnBookBtn() {
        bookBtn.click();
    }

    public boolean isTicketDetailsDisplayed() {
        return ticketDetails.isDisplayed();
    }

    public boolean isFareSummaryDisplayed() {
        return fareSummary.isDisplayed();
    }

}
