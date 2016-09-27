package org.testng;

public privileged aspect TestNGAntTaskDeprecatedAspect {

  /**
   * @deprecated Use "listeners"
   */
  @Deprecated
  public void TestNGAntTask.setListener(String listener) {
    m_listeners.add(listener);
  }

}
