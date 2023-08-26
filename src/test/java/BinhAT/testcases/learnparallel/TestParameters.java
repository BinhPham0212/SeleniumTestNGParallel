package BinhAT.testcases.learnparallel;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParameters {

    @Test
    @Parameters({"number1", "number2"})
    public int Cong2So(int num1, int num2) {
        System.out.println(num1 + num2);
        return(num1 + num2);
    }
}
