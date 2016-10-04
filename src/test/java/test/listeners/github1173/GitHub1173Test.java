package test.listeners.github1173;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.annotations.Test;
import test.SimpleBaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class GitHub1173Test extends SimpleBaseTest {

    @Test
    public void test() {
        TestNG tng = create(FilterTestResultParamsSample.class);

        GitHub1173Reporter reporter = new GitHub1173Reporter();
        tng.addListener((ITestNGListener) reporter);

        tng.run();

        assertThat(reporter.invokedMethods).hasSize(1);
        assertThat(reporter.invokedMethods.get(0).getTestResult()
                .getParameters()).containsExactly(10, "String Param");
    }

    @Test
    public void testFilteredParams() {
        TestNG tng = create(FilterTestResultParamsSample.class);

        GitHub1173Reporter reporter = new GitHub1173Reporter();
        tng.addListener((ITestNGListener) reporter);
        tng.addListener((ITestNGListener) new FilterTestResultParamsListener());

        tng.run();

        assertThat(reporter.invokedMethods).hasSize(1);
        assertThat(reporter.invokedMethods.get(0).getTestResult()
                .getParameters()).containsExactly("Filtered: 10", "Filtered: String Param");
    }
}
