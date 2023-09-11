package BinhAT.testcases.learnScreenshot;

import BinhAT.common.BaseTest;
import BinhAT.drivers.DriverManager;
import BinhAT.helpers.CaptureHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestScreenShot extends BaseTest {

    @Test
    public void testHomePage1(Method method) {
        DriverManager.getDriver().get("https://anhtester.com");     //Khởi tạo từ drivermanager threadlocal
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Login");
    }

    @Test
    public void testHomePage2(Method method) {
        CaptureHelper.startRecord(method.getName());
        DriverManager.getDriver().get("https://anhtester.com");     //Khởi tạo từ drivermanager threadlocal
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Login");
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.FAILURE) {
            //Take screenshot
            // Tạo tham chiếu của TakesScreenshot
            CaptureHelper.captureScreenshot(iTestResult.getName());
        }
        CaptureHelper.stopRecord();
    }

}
