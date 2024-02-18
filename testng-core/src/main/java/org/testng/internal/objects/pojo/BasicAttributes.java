package org.testng.internal.objects.pojo;

import org.testng.IClass;

/** Represents the basic attributes associated with object creation. */
public class BasicAttributes {

  private final IClass iClass;
  private final Class<?> clazz;

  public BasicAttributes(IClass iClass, Class<?> clazz) {
    this.iClass = iClass;
    this.clazz = clazz;
  }

  /** Returns the actual {@link Class} */
  public Class<?> getRawClass() {
    return clazz;
  }

  /** Returns the wrapped {@link IClass} that represents a TestNG test class. */
  public IClass getTestClass() {
    return iClass;
  }
}
