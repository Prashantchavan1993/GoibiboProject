package com.gobibo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.gobibo.pages.BusPage;
import com.gobibo.pages.FlightsPage;
import com.gobibo.testBase.TestBase;
import com.gobibo.testUtils.TestUtilities;
import com.relevantcodes.extentreports.LogStatus;

public class TC2_VlidateBusTicketBooking_Test extends TestBase{
	
	 BusPage busPage;
     String fromLocation = "Pune, Maharashtra";
     String toLocation = "Mumbai, Maharashtra";
     String dateToSelect = "Feb 11 2022";
     
     
     @Test
     public void validateBusTicketBooking_TC2() {
         extentTest = extentReports.startTest("TC2_ValidateBusTicketBooking");
         log.info("Start Test :: TC2 Validate Bus ticket booking");
         initialize();
         busPage = new BusPage();
         
         busPage.busTab();
         log.info("Clicked On Bus Tab");
         
         busPage.selectFromLocation(fromLocation);
         log.info("Selected From Location");
         
         busPage.selectToLocation(toLocation);
         log.info("Selected To Location");
         
         busPage.selectFromDateOnCalender(dateToSelect);
         log.info("Selected journey Date");
         busPage.clickOnSearchBusBtn();
         log.info("Clicked on Search Bus button");
         busPage.clickOnFastestFilter();
         log.info("Clicked on FASTEST to filter Bus with Fastest Speed");
         busPage.clickOnSelectSeat();
         log.info("Clicked on Select Seat button");
         busPage.clickOnboardingPoint();
         log.info("Clicked on Boarding Point");
         busPage.clickOnDroppingPoint();
         log.info("Clicked on Dropping Point");
         busPage.clickOnSelectYourSeat();
         log.info("Clicked on Your Seat");
         busPage.clickOnContinue();
         log.info("Clicked on Continue");
         
         Assert.assertTrue(busPage.isTicketDetailsDisplayed());
         log.info("Successfully validated ticket details are displayed");
         Assert.assertTrue(busPage.isFareSummaryDisplayed());
         log.info("Successfully validated fare summary is displayed");
         log.info("End Test :: TC1 Validate ticket booking");
     }

     @AfterMethod
     public void testTearDown(ITestResult result) throws IOException {
         if (result.getStatus() == ITestResult.FAILURE) {
             TestUtilities.takeScreenshot(result.getName());
             extentTest.log(LogStatus.FAIL,"Test Failed");
         } else if (result.getStatus() == ITestResult.SUCCESS) {
             extentTest.log(LogStatus.PASS,"Test Passed");
         } else if (result.getStatus() == ITestResult.SKIP) {
             extentTest.log(LogStatus.SKIP,"Test Skipped");
         }
         extentReports.endTest(extentTest);
     }
	
	
}