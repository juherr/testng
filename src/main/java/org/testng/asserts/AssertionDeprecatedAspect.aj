package org.testng.asserts;

public aspect AssertionDeprecatedAspect {

  /**
   * Invoked when an assert fails. Meant to be overridden by subclasses.
   *
   * @deprecated use onAssertFailure(IAssert assertCommand, AssertionError ex) instead of.
   */
  @Deprecated
  public void Assertion.onAssertFailure(IAssert<?> assertCommand) {
  }
}
