package test.reports;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReporterLogSampleTest2 {

  @Test
  public void test1() {
    Reporter.log("Log from test method from ReporterLogSampleTest2");
  }
}