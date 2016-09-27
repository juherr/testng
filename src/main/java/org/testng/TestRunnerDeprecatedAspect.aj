package org.testng;

public privileged aspect TestRunnerDeprecatedAspect {

  /**
   * @deprecated addListener(ITestNGListener) should be used instead
   */
  // TODO remove it
  @Deprecated
  public void TestRunner.addListener(Object listener) {
    if (listener instanceof ITestNGListener) {
      addListener((ITestNGListener) listener);
    }
  }

  /**
   * @deprecated addListener(ITestNGListener) should be used instead
   */
  // TODO change public to package visibility
  @Deprecated
  public void TestRunner.addTestListener(ITestListener il) {
    m_testListeners.add(il);
  }

  @Deprecated
  public void TestRunner.setMethodInterceptor(IMethodInterceptor methodInterceptor){
    m_methodInterceptors.add(methodInterceptor);
  }
}
