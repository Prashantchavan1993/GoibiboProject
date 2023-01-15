package com.gobibo;

import com.gobibo.pages.HotelPage;
import com.gobibo.testBase.TestBase;
import com.gobibo.testUtils.TestUtilities;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC01_ValidateInternationalHotelBooking  extends TestBase {


    @Test
    public  void clickingOnHetelPage(){
        extentTest = extentReports.startTest("TC1_ValidateTicketBooking");
        log.info("Start Test :: TC01 Validate international  Hotel  booking");
        initialize();

        HotelPage hotelPage=new HotelPage();

        hotelPage.clickonbutton();
        log.info("hotel page selected");
       // log.info("sucessfully");
        hotelPage.clickOnInternationalRadioButton();
        hotelPage.clickOnInternationalRadioButton();
        log.info("International Radio Button Selected");
        hotelPage.searchHoteles();
        log.info("Expected Hotel Search");
        hotelPage.citylists();
        log.info("Expected Hotel List");
        hotelPage.Selectcity();
        log.info("Expected Hotel Select");
        hotelPage.bookinDateSelected();
        log.info("Expected Date Selected");
        hotelPage.searchHotelButton();
        log.info("click on hotel search button");
        hotelPage.selectFirstHotel();
        log.info("Expected Hotel Selected");
        hotelPage.switchingWindow();
        log.info("Window Switch");
        hotelPage.selectedRoom();
        log.info("Expected Room Selected");
     boolean result=  hotelPage.priceSummaryISDisplayed();
        Assert.assertTrue(result);
        log.info("Verifying Expected Price Summary text");


    }
//    @AfterMethod
//    public void testTearDown(ITestResult result) throws IOException {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            TestUtilities.takeScreenshot(result.getName());
//            extentTest.log(LogStatus.FAIL,"Test Failed");
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            extentTest.log(LogStatus.PASS,"Test Passed");
//        } else if (result.getStatus() == ITestResult.SKIP) {
//            extentTest.log(LogStatus.SKIP,"Test Skipped");
//        }
//        extentReports.endTest(extentTest);
//    }



}
