package org.testng.reporters;

import org.testng.ITestContext;

public aspect FailedReporterDeprecatedAspect {

  /**
   * Do not rely on this method. The class is used as <code>IReporter</code>.
   *
   * @see org.testng.TestListenerAdapter#onFinish(org.testng.ITestContext)
   * @deprecated this class is used now as IReporter
   */
  @Deprecated
  public void FailedReporter.onFinish(ITestContext context) {
    // Delete the previous file
//    File f = new File(context.getOutputDirectory(), getFileName(context));
//    f.delete();

    // Calculate the methods we need to rerun :  failed tests and
    // their dependents
//    List<ITestResult> failedTests = getFailedTests();
//    List<ITestResult> skippedTests = getSkippedTests();
  }
}
