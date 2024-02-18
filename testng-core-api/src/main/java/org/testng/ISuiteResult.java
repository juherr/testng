package org.testng;

/** This class represents the result of a suite run. */
public interface ISuiteResult {

  /** Returns the testing context for these tests. */
  ITestContext getTestContext();
}
