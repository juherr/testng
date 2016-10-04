package test.listeners.github1173;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FilterTestResultParamsSample {

    @DataProvider
    private static Object[][] dp() {
        return new Object[][]{{10, "String Param"}};
    }

    @Test(dataProvider = "dp")
    public void test(Integer param1, String param2) {
    }
}
