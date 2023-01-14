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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC1_ValidateUserNavigationToSearchPage_Test extends TestBase {
        FlightsPage flightsPage;
        String fromLocation = "Pune";
        String toLocation = "Hyderabad";
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
            boolean filtersDisplayed = flightsPage.isFiltersDisplayed();
            Assert.assertTrue(filtersDisplayed);
            System.out.println("Test Results: Successfully validated filters are displayed on flight search page");
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
            webDriver.get().quit();
        }
}
