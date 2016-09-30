package org.testng;

import org.testng.xml.XmlSuite;

import java.util.Collection;
import java.util.Map;

public class StatisticSuiteListener implements ISuiteListener {

  @Override
  public void onStart(ISuite suite) {
  }

  @Override
  public void onFinish(ISuite suite) {
    XmlSuite xmlSuite = suite.getXmlSuite();
    //
    // Display the final statistics
    //
    if (xmlSuite.getVerbose() > 0) {
      SuiteResultCounts counts = calculateResultCounts(suite);

      StringBuffer bufLog = new StringBuffer("\n===============================================\n")
          .append(xmlSuite.getName());
      bufLog.append("\nTotal tests run: ")
          .append(counts.m_total).append(", Failures: ").append(counts.m_failed)
          .append(", Skips: ").append(counts.m_skipped);
      if(counts.m_confFailures > 0 || counts.m_confSkips > 0) {
        bufLog.append("\nConfiguration Failures: ").append(counts.m_confFailures)
            .append(", Skips: ").append(counts.m_confSkips);
      }
      bufLog.append("\n===============================================\n");
      System.out.println(bufLog.toString());
    }
  }

  private static SuiteResultCounts calculateResultCounts(ISuite iSuite) {
    SuiteResultCounts result = new SuiteResultCounts();
    if (iSuite != null) {
      Map<String, ISuiteResult> results = iSuite.getResults();
      if (results != null) {
        Collection<ISuiteResult> tempSuiteResult = results.values();
        for (ISuiteResult isr : tempSuiteResult) {
          ITestContext ctx = isr.getTestContext();
          int skipped = ctx.getSkippedTests().size();
          int failed = ctx.getFailedTests().size() + ctx.getFailedButWithinSuccessPercentageTests().size();
          result.m_skipped += skipped;
          result.m_failed += failed;
          result.m_confFailures += ctx.getFailedConfigurations().size();
          result.m_confSkips += ctx.getSkippedConfigurations().size();
          result.m_total += ctx.getPassedTests().size() + failed + skipped;
        }
      }
    }
    return result;
  }

  /**
   * Class to help calculate result counts for tests run as part of a suite and
   * its children suites
   */
  private static class SuiteResultCounts {
    int m_total = 0;
    int m_skipped = 0;
    int m_failed = 0;
    int m_confFailures = 0;
    int m_confSkips = 0;

  }
}
