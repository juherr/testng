package test.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import test.SimpleBaseTest;

public abstract class ConfigurationBaseTest extends SimpleBaseTest {
  protected void testConfiguration(Class<?>... classes) {
    TestNG tng = create(classes);

    TestListenerAdapter tla = new TestListenerAdapter();
    tng.addListener((ITestNGListener) tla);
    tng.run();

    assertThat(tla.getConfigurationFailures())
        .withFailMessage(getFailedResultMessage(tla.getConfigurationFailures()))
        .hasSize(0);
    assertThat(tla.getFailedTests())
        .withFailMessage(getFailedResultMessage(tla.getFailedTests()))
        .hasSize(0);
    assertThat(tla.getSkippedTests())
        .withFailMessage(getFailedResultMessage(tla.getSkippedTests()))
        .hasSize(0);
    assertThat(tla.getPassedTests()).withFailMessage("All tests should pass").isNotEmpty();
  }
}
