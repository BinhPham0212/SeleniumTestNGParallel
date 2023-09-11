package BinhAT.keywords;

import BinhAT.drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static BinhAT.drivers.DriverManager.*;

public class WebUI {
    /**
     * Hàm xây dựng để truyền Driver từ ngoài vào để có giá trị. Và k cần truyền vào cho từng hàm nữa ( Static WebgetDriver())
     * Khởi tạo driver toàn cục
     */
    private static int EXPLICIT_WAIT_TIMEOUT = 10;
    private static int WAIT_PAGE_LOADED_TIMEOUT = 30;

    public static WebElement getWebElement(By by) {
        return getDriver().findElement(by);
    }

    public static void logConsole(String message) {
        System.out.println(message);
    }

    public static void openURL(String URL) {
        getDriver().get(URL);
        waitForPageLoaded();
        logConsole("Open URL: " + URL);
    }

    /* Actions class
     */

    public static String getCurrentUrl() {
        waitForPageLoaded();
        logConsole("Get Current URL: " + getDriver().getCurrentUrl());
        return getDriver().getCurrentUrl();
    }

    public static void clickElement(By by) {
        waitForElementVisible(by);
        getWebElement(by).click();
        logConsole("Clicked on element " + by);
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        logConsole("Set text " + value + " on element " + by);
    }

    public static void setTextEnter(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value,Keys.ENTER);
        logConsole("Set text " + value + " on element " + by);
    }
    public static String getTextELement(By by) {
        waitForElementVisible(by);
        logConsole("Get text element " + by);
        logConsole("==> Text: " + getWebElement(by).getText());
        return getWebElement(by).getText();
    }

    public static String getAttributeELement(By by, String attributeName) {
        waitForElementVisible(by);
        logConsole("Get attribute value of element " + by);
        logConsole("==> Attribute value: " + getWebElement(by).getAttribute(attributeName));
        return getWebElement(by).getAttribute(attributeName);
    }

    public static void hoverOnElement(By by) {
        Actions action = new Actions(getDriver());
        action.moveToElement(getWebElement(by));
        logConsole("Hover on element " + by);
    }

    public static void rightClickElement(By by) {
        Actions action = new Actions(getDriver());
        action.contextClick(getWebElement(by));
        logConsole("Right click on element " + by);
    }

    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    public static void scrollToElementWithJS(By by) {
        //JavascriptExecutor
        //Find Element trong DOM wait for Element trong DOM
        waitForElementPresent(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
//        logConsole("Scroll to element " + by);
        //Dùng action class
    }

//    public static void scrollToElementWithAction(By by) {
//        Actions action = new Actions(getDriver());
//        action.moveToElement(getWebElement(by)).build().perform();
//    }

    /* Actions class====
     */

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Check Element tồn tại trong DOM và hiển thị trên UI
    public static void waitForElementVisible(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //Check xem Element tồn tại
    public static void waitForElementPresent(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementClickable(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));

        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean verifyElementVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
        //truyền object , chờ đợi time nếu quá time báo cache. tìm thấy true (thoát khỏi vòng), false trả về hàm này
    }

    public static boolean verifyElementNotVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verifyElementNotPresent(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = getDriver().findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " existing in DOM.");
            return true;
        } else {
            System.out.println("Element " + by + " NOT exist in DOM.");
            return false;
        }
    }

    public static Boolean checkElementExist(String xpath) {
        List<WebElement> listElement = getDriver().findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing in DOM.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist in DOM.");
            return false;
        }
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LOADED_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver)  //driver này là tên của tham số
            {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }


}

