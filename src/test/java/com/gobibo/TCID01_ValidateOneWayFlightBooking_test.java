package com.gobibo;

import com.gobibo.pages.FlightsPage;
import com.gobibo.testBase.TestBase;
import com.gobibo.testUtils.TestUtilities;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCID01_ValidateOneWayFlightBooking_test extends TestBase {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
    LocalDateTime now = LocalDateTime.now();
    String dateToSelect =  dtf.format(now);
    String fromCity;
    String toCity;

    @BeforeClass
    public void before() throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/FlightBooking.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        fromCity = workbook.getSheet("TCID01").getRow(1).getCell(0).getStringCellValue();
        toCity = workbook.getSheet("TCID01").getRow(1).getCell(1).getStringCellValue();
    }

    @Test
    public void validateOneWayFlightBooking_TCID01() {
        extentTest = extentReports.startTest("TCID01- Validate OneWay Flight Booking1");
        log.info("Start Test :: TCID01 Validate OneWay Flight Booking1");
        initialize();

        FlightsPage flightsPage = new FlightsPage();
        flightsPage.selectFromLocation(fromCity);
        flightsPage.selectToLocation(toCity);
        flightsPage.clickDoneOnCalender();
        new Actions(webDriver.get()).sendKeys(Keys.ESCAPE);
        flightsPage.clickDoneOnTravellersAndClass();
        flightsPage.clickSearchFlightsBtn();
        log.info("Clicked on search fligh btn");

        boolean isFilterDisplayed = flightsPage.isFiltersDisplayed();
        Assert.assertTrue(isFilterDisplayed);
        log.info("Successfully validated user placed on search flight page");

        flightsPage.clickOnViewFareButton();
        flightsPage.clickOnBookBtn();

        boolean isTicketDetailsDisplayed = flightsPage.isTicketDetailsDisplayed();
        Assert.assertTrue(isTicketDetailsDisplayed);
        log.info("Successfully validated ticket details are displayed");
        boolean isFareSummaryDisplayed = flightsPage.isFareSummaryDisplayed();
        Assert.assertTrue(isFareSummaryDisplayed);
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
        webDriver.get().quit();
    }
}
