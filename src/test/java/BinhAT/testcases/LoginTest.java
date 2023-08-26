package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.drivers.DriverManager;
import BinhAT.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //Khởi tạo đối tượng dùng chung cho toàn bộ class
    LoginPage loginPage;

    @Test
    public void loginTestSuccess() {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();

        //gọi hàm "Login"
        loginPage.login("admin@example.com", "123456");
    }

    @Test
    public void loginTestInvalidEmail() {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();
        loginPage.loginInvalidEmail("admin@example123.com", "123456");
    }
}


