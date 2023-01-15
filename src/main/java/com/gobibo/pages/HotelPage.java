package com.gobibo.pages;

import com.gobibo.testBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HotelPage extends TestBase {

    WebDriver driver;

    @FindBy(xpath = "//ul[@class='happy-nav']//child::li//child::a//following::li/a[1]")
    WebElement hotelbutton;


    @FindBy(xpath = "//div[contains(@class,SearchBlockUIstyles__RadioBu)]//following-sibling::div//child::input//following::div//child::input[1]")
    WebElement Internationalbutton;


    @FindBy(xpath = "  //input[@data-testid='home-autosuggest-input']")
    WebElement inputboxofinternational;
    //input[@data-testid='home-autosuggest-input']


    @FindBys(@FindBy(xpath = "//input[@data-testid='home-autosuggest-input']/following-sibling::ul/li"))
    List<WebElement> citylist;

    @FindBy(xpath = "//input[@data-testid='home-autosuggest-input']/following-sibling::ul/li")
    WebElement citylist2;

    @FindBy(xpath = "//div[text()='Check-in']//following-sibling::h4")
    WebElement datebotton;

    @FindBy(xpath = "//span[@data-testid='date_27_10_2022']")
    WebElement fromdateselect;


    @FindBy(xpath = "//span[@data-testid='date_1_11_2022']")
    WebElement todateselect;


    @FindBy(xpath = "//button[@data-testid='searchHotelBtn']")
    WebElement searchhotelbutton;


    @FindBy(xpath = "//div[contains(@class,'Hotel')]/child::h4[text()='DoubleTree by Hilton Hotel Los Angeles Downtown']")
    WebElement selectHotel;

    @FindBy(xpath = "/html/head/title/text()")
    WebElement titleofselectedhotel;

@FindBy (xpath = "  //*[@id=\"200199032\"]/div/div[2]/div[1]/div[3]/div[2]/button")
WebElement selectroom;

@FindBy(xpath = "//div[contains(@class,'dwebCommonstyles__CenteredSpaceWrap-sc-112ty3f-0 Repri')]//child::h3")
WebElement pricesummary;
    public HotelPage() {
        PageFactory.initElements(webDriver.get(), this);
        driver = webDriver.get();
    }

    public void clickonbutton() {
        hotelbutton.click();
    }

    public void clickOnInternationalRadioButton() {
        Internationalbutton.click();

    }

    public void searchHoteles() {


        inputboxofinternational.sendKeys("california");
    }


    public void citylists() {
        for (WebElement element : citylist) {
            System.out.println(element.getText());

            String st = element.getText();
            if (st.contains("Los Angeles, California, US ,City")) {
                element.click();
                break;
            }

        }
    }

    public void Selectcity() {

        Actions action = new Actions(driver);
        action.moveToElement(citylist2).click().build().perform();
    }


    public void bookinDateSelected() {
        datebotton.click();
        fromdateselect.click();
        todateselect.click();

    }

    public void searchHotelButton() {

        searchhotelbutton.click();
    }

    public void selectFirstHotel() {

        selectHotel.click();
    }


     public void switchingWindow() {

         WebDriverWait wait= new WebDriverWait(driver,30);
         wait.until(ExpectedConditions.numberOfWindowsToBe(2));
         String mainwindowaddress = driver.getWindowHandle();

         Set<String> allwindoaddress = driver.getWindowHandles();

         Iterator<String> itr = allwindoaddress.iterator();

         while (itr.hasNext()) {
             String windows = itr.next();
             if (mainwindowaddress.equals(windows)) {

             } else {
                 driver.switchTo().window(windows);
                 System.out.println("window switch successfully");
                 String title = driver.getTitle();

                 if (title.contains("DoubleTree by Hilton Hotel Los")) {
                     System.out.println("windo switch");
                     break;
                 }

//    public  void swichSelectedhotel() {
//
//        String hotelpagewindow = driver.getWindowHandle();
//
//        Set<String> selectedhotelpagewindow = driver.getWindowHandles();
//
//        for (selectedhotelpagewindow loop:hotelpagewindow) {
//            if (selectedhotelpagewindow.equals(hotelpagewindow)){
//
//                System.out.println("do noting");
//            }else {
//                driver.switchTo().window();
//
//            }
//        }
//}
             }
             }

         }

         public void selectedRoom(){

        selectroom.click();
         }

         public boolean priceSummaryISDisplayed(){

        return pricesummary.isDisplayed();
         }

     }