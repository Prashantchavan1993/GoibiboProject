package com.gobibo;

import com.gobibo.pages.FlightsPage;
import com.gobibo.testBase.TestBase;
import com.gobibo.testUtils.TestUtilities;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TC1_ValidateTicketBooking_Test extends TestBase {
        FlightsPage flightsPage;
        String fromLocation = "Pune";
        String toLocation = "Hyderabad";
       // String dateToSelect = "May 19 2022";
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateToSelect =  dtf.format(now);

        @BeforeClass
        public void before() {
            //reading
        }
    @Test
        public void validateTicketBooking_TC1() {
            extentTest = extentReports.startTest("TC1_ValidateTicketBooking");
            log.info("Start Test :: TC1 Validate ticket booking");
            initialize();
            flightsPage = new FlightsPage();
            Assert.fail();
            flightsPage.selectFromLocation(fromLocation);
            log.info("Selected From Location");
            flightsPage.selectToLocation(toLocation);
            log.info("Selected To Location");
            flightsPage.selectFromDateOnCalender(dateToSelect);
            log.info("Selected journey Date");
            flightsPage.clickDoneOnCalender();
            flightsPage.clickDoneOnTravellersAndClass();
            flightsPage.clickSearchFlightsBtn();
            log.info("Clicked on Search Flights button");
            flightsPage.clickOnDurationFilter();
            log.info("Clicked on Duration to filter flight with shortest duration");
            flightsPage.clickOnViewFareButton();
            log.info("Clicked on View Fare button");
            flightsPage.clickOnBookBtn();
            log.info("Clicked on Book button");
            Assert.assertTrue(flightsPage.isTicketDetailsDisplayed());
            log.info("Successfully validated ticket details are displayed");
            Assert.assertTrue(flightsPage.isFareSummaryDisplayed());
            log.info("Successfully validated fare summary is displayed");
            log.info("End Test :: TC1 Validate ticket booking");
        }

//        @Test
//        public void dateTest() {
//
//            System.out.println(date);
//        }

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
