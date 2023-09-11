package BinhAT.testcases.learndataprovider;

import BinhAT.dataproviders.DataLogin;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataProvider {
    @Test(dataProvider = "dataProviderLoginCRM", dataProviderClass = DataLogin.class)
    public void testLoginCRM(String email, String password) {
        System.out.println(email + " - " + password);
    }

    @Test(dataProvider = "dataProviderLoginCMS", dataProviderClass = DataLogin.class)
    public void testLoginCMS(String email, String password, int pin) {
        System.out.println(email + " - " + password + "-" + pin);
    }
}
