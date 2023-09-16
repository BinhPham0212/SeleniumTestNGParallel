package BinhAT.pages;

import BinhAT.common.BaseTest;

import static BinhAT.keywords.WebUI.*;

import BinhAT.helpers.PropertiesHelper;
import BinhAT.keywords.WebUI;
import org.bouncycastle.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginPage {

    private String PAGETEXT = "Login";

    //Lưu Object của trang Login
    //Dùng đối tượng By trong Selenium để khai báo tên Object cùng giá trị locator tương ứng
    By headerPage = By.xpath("//h1");
    By inputEmail = By.xpath("//input[@id='email']");
    By inputPassword = By.xpath("//input[@id='password']");
    By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    By messageErrorEmail = By.xpath("//div[contains(@class,'alert-danger')]");

    //Hàm xây dựng để tryền vào driver

    //Viết hàm xử lý cho trang Login
    public void verifyHeaderPage() {
        Assert.assertEquals(getTextELement(headerPage), "Login", "FAIL. Header not match");
    }

    public void verifyErrorMessageDisplays() {
        Assert.assertTrue(getWebElement(messageErrorEmail).isDisplayed(),"FAIL. Error message not displayed");
        Assert.assertEquals(getTextELement(messageErrorEmail),"Invalid email or password", "FAIL. Message error not match" );
    }

    public void enterEmail(String email) {
//        driver.findElement(inputEmail).sendKeys(email);
        setText(inputEmail,email);
    }

    public void enterPassword(String password) {
//        driver.findElement(inputPassword).sendKeys(password);
        setText(inputPassword,password);
    }

    public void clickOnLoginButton() {
        clickElement(buttonLogin);
    }
    /**page
     * */
    public DashboardPage login(String email, String password) {
        openURL(PropertiesHelper.getValue("url"));
        verifyHeaderPage();
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
        Assert.assertTrue(verifyElementNotPresent(messageErrorEmail,5),"Login không thành công");
        return new DashboardPage();
    }

    public void loginInvalidEmail(String email, String password) {
        openURL(PropertiesHelper.getValue("url"));
        verifyHeaderPage();
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
        //Verify message error is displayed correctly
        verifyErrorMessageDisplays();
    }


}
