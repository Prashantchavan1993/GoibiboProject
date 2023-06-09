package com.gobibo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gobibo.testBase.TestBase;

public class BusPage extends TestBase{
	WebDriver driver;
	
	
	@FindBy(xpath="//span[@class='header-sprite nav-icon-bus gr-append-right5']")
	private WebElement busTab;
	
    @FindBy(xpath ="//label[text()='FROM']/following-sibling::input")
    private WebElement fromTextBox;
    
	@FindBy(xpath="//input[@id='autosuggestBusSRPSrcHome']")
    private WebElement fromCity;
	
	@FindBy(xpath="//input[@id='autosuggestBusSRPDestHome']")
	private WebElement toCity;
	
	@FindBy(xpath="//div[@class='SearchWidgetstyles__DateWrapper-sc-1mr4hwz-3 cuTZWV']")
	private WebElement travelDate;
	
	@FindBy(xpath="//button[contains(text(),'Search Bus')]")
	private WebElement searchBusBtn;
	
	@FindBy(xpath="//span[contains(text(),'FASTEST')]")
	private WebElement fastestFilter;
	
	@FindBy(xpath="SrpActiveCardstyles__TotalDurationWrapperDiv-sc-yk1110-20 bgtBCd")
	private WebElement firstRowViewFareButton;
	
	@FindBy(xpath="//span[contains(text(),'SELECT SEAT')][1]")
	private WebElement selectSeat;
	
	@FindBy(xpath="//label[@class='RadioButtonstyles__RadioLabel-sc-wz601o-0 cASToK'][1]")
	private WebElement boardingPoint;
	
	@FindBy(xpath="//label[@class='RadioButtonstyles__RadioLabel-sc-wz601o-0 cASToK'][2]")
	private WebElement droppingPoint;
	
	@FindBy(xpath="//div[@class='SeatWithTooltipstyles__BusSleeper-sc-dkrka-1 gygcLu'][1]")
	private WebElement selectYourSeat;
	
	@FindBy(xpath="//button[contains(text(),'CONTINUE')]")
	private WebElement continueForPayment;
	
	@FindBy(xpath="//header[@class='ReviewPagestyles__PageHeader-sc-fmjc42-2 ReviewPagestyles__BookingHeader-sc-fmjc42-3 zQaar']")
	private WebElement ticketDetails;
	
	@FindBy(xpath="//section[@class='ReviewPagestyles__RightWrapper-sc-fmjc42-7 cPXnIS']")
	private WebElement fairDetails;
	
	@FindBy(id = "autoSuggest-list")
	private WebElement autoSuggest;
	
	
	 public BusPage() {
	        PageFactory.initElements(webDriver.get(), this);
	        driver = webDriver.get();
	    }
        
	    public void busTab() {
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeClickable(busTab));	
	        busTab.click();
	    }
	    
	    public void selectFromLocation(String location) {
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeClickable(fromCity));
	        fromCity.click();
	        fromTextBox.sendKeys(location);
	        wait.until(ExpectedConditions.visibilityOf(autoSuggest));
	        driver.findElement(By.xpath("//ul[@id='autoSuggest-list']//div[contains(text(),'"+location+"')]")).click();
	     }

	    public void selectToLocation(String location) {
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.elementToBeClickable(toCity));
	        toCity.sendKeys(location);
	        wait.until(ExpectedConditions.visibilityOf(autoSuggest));
	        driver.findElement(By.xpath("//ul[@id='autoSuggest-list']//div[contains(text(),'"+location+"')]")).click();
	    }
	    
	    public void selectFromDateOnCalender(String date) {
	        driver.findElement(By.xpath("//div[contains(@aria-label,'"+ date +"')]")).click();
	    }
	    
	    public void clickOnSearchBusBtn() {
	    	searchBusBtn.click();
	    }
	    
	    public void clickOnFastestFilter() {
	    	fastestFilter.click();
	    }
	    
	    public void clickOnSelectSeat() {
	    	selectSeat.click();
	    }
	    
	    public void clickOnboardingPoint() {
	    	boardingPoint.click();
	    }
	    
	    public void clickOnDroppingPoint(){
	    	droppingPoint.click();
	    }
	    
	    public void clickOnSelectYourSeat() {
	    	selectYourSeat.click();
	    }
	    
	    public void clickOnContinue() {
	    	continueForPayment.click();
	    }
	    	   
	    public boolean isTicketDetailsDisplayed() {
	    	return ticketDetails.isDisplayed();
	    }
	    public boolean isFareSummaryDisplayed() {
	        return fairDetails.isDisplayed();
	    }
}
