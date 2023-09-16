package BinhAT.dataproviders;

import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.SystemsHelper;
import org.testng.annotations.DataProvider;

public class DataLogin {
    @DataProvider(name = "dataProviderLoginCRM", parallel = true)
    public Object[][] dataLoginCRM() {
        return new Object[][]{
                {"admin@example.com", "123456"},
                {"admin@example.com", "123456"},
                {"admin@example.com", "123456"}
        };
    }

    @DataProvider(name = "dataProviderLoginCMS")
    public Object[][] dataLoginCMS() {
        return new Object[][]{
                {"admin@example.com", "123456", 123},
                {"admin@example.com", "123456", 1234}
        };
    }

    @DataProvider(name = "data_provider_login_excel", parallel = true)
    public Object[][] dataLoginHRMFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData(SystemsHelper.getCurrentDir() + "src/test/Resources/datatest/CRM.xlsx", "Login");
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_custom_row", parallel = true)
    public Object[][] dataLoginCRMFromExcelCustomRow() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemsHelper.getCurrentDir() + "src/test/Resources/datatest/CRM.xlsx", "Login",3,4);
        System.out.println("Login Data from Excel: " + data);
        return data;
    }
}
