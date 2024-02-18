package org.testng;

/**
 * An interface representing a method that has been invoked by TestNG.
 *
 * <p>This interface is internal.
 */
public interface IInvokedMethod {

  /** Returns true if this method is a test method */
  boolean isTestMethod();

  /** Returns true if this method is a configuration method (@BeforeXXX or @AfterXXX) */
  boolean isConfigurationMethod();

  /** Returns the test method */
  ITestNGMethod getTestMethod();

  ITestResult getTestResult();

  /** Returns the date when this method was run */
  long getDate();
}
