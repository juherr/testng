package org.testng;

import java.io.Serializable;


/**
 * This class represents the result of a suite run.
 */
public interface ISuiteResult extends Serializable {

  /**
   * @return The name of the property file for these tests.
   *
   * @deprecated Not used, will always return null
   */
  @Deprecated
  String getPropertyFileName();

  /**
   * @return The testing context for these tests.
   */
  ITestContext getTestContext();

}
