package org.testng;

import org.testng.internal.ClassImpl;
import org.testng.internal.NoOpTestClass;
import org.testng.junit.JUnitTestClass;

public privileged aspect IClassDeprecatedAspect {

  @Deprecated
  public int ClassImpl.getInstanceCount() {
    return m_instanceCount;
  }

  @Deprecated
  public int JUnitTestClass.getInstanceCount() {
    return 1;
  }

  @Deprecated
  public int NoOpTestClass.getInstanceCount() {
    return m_instances.length;
  }

  @Deprecated
  public int TestClass.getInstanceCount() {
    return m_iClass.getInstanceCount();
  }
}
