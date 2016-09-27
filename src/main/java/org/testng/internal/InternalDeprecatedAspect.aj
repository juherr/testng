package org.testng.internal;

import org.testng.internal.annotations.IAnnotationFinder;

import java.lang.reflect.Method;
import java.util.Map;

public privileged aspect InternalDeprecatedAspect {

  /**
   * @deprecated use #ConfigurationMethod(ConstructorOrMethod,...) instead.
   */
  @Deprecated
  public ConfigurationMethod.new(Method method,
                             IAnnotationFinder annotationFinder,
                             boolean isBeforeSuite,
                             boolean isAfterSuite,
                             boolean isBeforeTest,
                             boolean isAfterTest,
                             boolean isBeforeClass,
                             boolean isAfterClass,
                             boolean isBeforeMethod,
                             boolean isAfterMethod,
                             String[] beforeGroups,
                             String[] afterGroups,
                             Object instance)
  {
    this(new ConstructorOrMethod(method), annotationFinder, isBeforeSuite, isAfterSuite, isBeforeTest, isAfterTest,
        isBeforeClass, isAfterClass, isBeforeMethod, isAfterMethod, beforeGroups, afterGroups, instance);
  }


  @Deprecated
  public Map BaseClassFinder.getExistingClasses() {
    return m_classes;
  }
}
