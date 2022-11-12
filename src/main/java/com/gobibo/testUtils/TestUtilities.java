package com.gobibo.testUtils;

import com.gobibo.testBase.TestBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TestUtilities extends TestBase {

    public static void takeScreenshot(String testName) throws IOException {
        Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(currentDateTime);
        File source = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir")+"/ScreenShots/"+testName+timeStamp+".png");
        FileHandler.copy(source, destination);
    }


}
