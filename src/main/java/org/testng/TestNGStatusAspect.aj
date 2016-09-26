package org.testng;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.testng.internal.IResultListener2;

import java.io.FileNotFoundException;

import static org.testng.TestNG.HAS_FAILURE;
import static org.testng.TestNG.HAS_FSP;
import static org.testng.TestNG.HAS_SKIPPED;

public aspect TestNGStatusAspect {

  static final int TestNG.HAS_FAILURE = 1;
  static final int TestNG.HAS_SKIPPED = 2;
  static final int TestNG.HAS_FSP = 4;
  static final int TestNG.HAS_NO_TEST = 8;

  private int TestNG.m_status;
  private boolean TestNG.m_hasTests = false;

  public int TestNG.getStatus() {
    return m_status;
  }

  /**
   * @deprecated Internal usage only
   */
  @Deprecated
  void TestNG.setStatus(int status) {
    m_status |= status;
  }

  /**
   * @deprecated Internal usage only
   */
  @Deprecated
  void TestNG.setHasRunTests() {
    m_hasTests = true;
  }

  /**
   * The TestNG entry point for command line execution.
   *
   * @param argv the TestNG command line parameters.
   * @throws FileNotFoundException
   */
  public static void TestNG.main(String[] argv) {
    TestNG testng = privateMain(argv, null);
    System.exit(testng.getStatus());
  }

  @After("public void TestNG.run()")
  private void TestNG.aspectRun() {
    if(!m_hasTests) {
      setStatus(TestNG.HAS_NO_TEST);
      if (TestRunner.getVerbose() > 1) {
        System.err.println("[TestNG] No tests found. Nothing was run");
        TestNG.usage();
      }
    }
  }

  /**
     * @deprecated since 5.1
     */
  @Deprecated
  public void TestNG.setHasFailure(boolean hasFailure) {
    setStatus(HAS_FAILURE);
  }

  /**
   * @deprecated since 5.1
   */
  @Deprecated
  public void TestNG.setHasFailureWithinSuccessPercentage(boolean hasFailureWithinSuccessPercentage) {
    setStatus(HAS_FSP);
  }

  /**
   * @deprecated since 5.1
   */
  @Deprecated
  public void TestNG.setHasSkip(boolean hasSkip) {
    setStatus(HAS_SKIPPED);
  }

  /**
   * @return true if at least one test failed.
   */
  public boolean TestNG.hasFailure() {
    return (getStatus() & HAS_FAILURE) == HAS_FAILURE;
  }

  /**
   * @return true if at least one test failed within success percentage.
   */
  public boolean TestNG.hasFailureWithinSuccessPercentage() {
    return (getStatus() & HAS_FSP) == HAS_FSP;
  }

  /**
   * @return true if at least one test was skipped.
   */
  public boolean TestNG.hasSkip() {
    return (getStatus() & HAS_SKIPPED) == HAS_SKIPPED;
  }

  @Before("private void initializeDefaultListeners()")
  private void TestNG.addExistCodeListener() {
    m_testListeners.add(new ExitCodeListener(this));
  }

  public static class TestNG.ExitCodeListener implements IResultListener2 {
    private TestNG m_mainRunner;

    public ExitCodeListener(TestNG runner) {
      m_mainRunner = runner;
    }

    @Override
    public void beforeConfiguration(ITestResult tr) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
      setHasRunTests();
      m_mainRunner.setStatus(HAS_FAILURE);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
      setHasRunTests();
      if ((m_mainRunner.getStatus() & HAS_FAILURE) != 0) {
        m_mainRunner.setStatus(HAS_SKIPPED);
      }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
      setHasRunTests();
      m_mainRunner.setStatus(HAS_FSP);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
      setHasRunTests();
    }

    @Override
    public void onStart(ITestContext context) {
      setHasRunTests();
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {
      setHasRunTests();
    }

    private void setHasRunTests() {
      m_mainRunner.setHasRunTests();
    }

    /**
     * @see org.testng.IConfigurationListener#onConfigurationFailure(org.testng.ITestResult)
     */
    @Override
    public void onConfigurationFailure(ITestResult itr) {
      m_mainRunner.setStatus(HAS_FAILURE);
    }

    /**
     * @see org.testng.IConfigurationListener#onConfigurationSkip(org.testng.ITestResult)
     */
    @Override
    public void onConfigurationSkip(ITestResult itr) {
      m_mainRunner.setStatus(HAS_SKIPPED);
    }

    /**
     * @see org.testng.IConfigurationListener#onConfigurationSuccess(org.testng.ITestResult)
     */
    @Override
    public void onConfigurationSuccess(ITestResult itr) {
    }
  }
}
