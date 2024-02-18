package org.testng.internal.annotations;

public interface IBaseBeforeAfterMethod extends IBaseBeforeAfter {
  /** Returns the list of groups the test method must belong to one of which. */
  String[] getGroupFilters();
}
