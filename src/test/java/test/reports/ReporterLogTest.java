package test.reports;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;

import test.SimpleBaseTest;

import java.util.List;

/**
 * Make sure that Reporter.log() in listeners don't get discarded.
 */
public class ReporterLogTest extends SimpleBaseTest {

  public static class MyTestListener extends TestListenerAdapter {
    @Override
    public void onTestSuccess(ITestResult result) {
      Reporter.log("Log from listener");
      super.onTestSuccess(result);
    }

    public List<String> getLog() {
      return Reporter.getOutput();
    }
  }


  @Test
  public void shouldLogFromListener() {
    TestNG tng = create(ReporterLogSampleTest.class, ReporterLogSampleTest2.class);
    MyTestListener listener = new MyTestListener();
    tng.addListener(listener);
    tng.setParallel("methods");
    tng.run();
    assertContains("Log from listener", listener.getLog());
    assertContains("Log from test method from ReporterLogSampleTest", listener.getLog());
    assertContains("Log from test method from ReporterLogSampleTest2", listener.getLog());
  }

  private static void assertContains(String expected, List<String> values) {
    boolean success = false;
    for(String s : values) {
      if (s.contains(expected)) {
        success = true;
        break;
      }
    }
    Assert.assertTrue(success);
  }

  @Test
  public void we_should_be_able_to_get_output_for_specific_results() {
    String expectedLog1 = "asdfasdf";
    String expectedLog2 = "ffffzzzzsdf";
    ITestResult result1 = new org.testng.internal.TestResult();
    ITestResult result2 = new org.testng.internal.TestResult();
    Reporter.setCurrentTestResult(result1);
    Reporter.log(expectedLog1);
    Reporter.setCurrentTestResult(result2);
    Reporter.log(expectedLog2);
    Assert.assertEquals(expectedLog1, Reporter.getOutput(result1).get(0));
    Assert.assertEquals(expectedLog2, Reporter.getOutput(result2).get(0));
  }
}
