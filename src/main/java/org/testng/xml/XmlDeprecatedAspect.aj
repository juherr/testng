package org.testng.xml;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public privileged aspect XmlDeprecatedAspect {

  /**
   * @deprecated Use #setParallel(XmlSuite.ParallelMode) instead
   */
  @Deprecated
  public void XmlSuite.setParallel(String parallel) {
    if (parallel == null) {
      setParallel((XmlSuite.ParallelMode)null);
    } else {
      setParallel(XmlSuite.ParallelMode.getValidParallel(parallel));
    }
  }

  /**
   * @deprecated Use {@link #setPreserveOrder(Boolean)} instead
   */
  @Deprecated
  public void XmlSuite.setPreserveOrder(String f) {
    setPreserveOrder(Boolean.valueOf(f));
  }

  /**
   * Sets the XML Classes.
   * @param classes The classes to set.
   * @deprecated use setXmlClasses
   */
  @Deprecated
  public void XmlTest.setClassNames(List<XmlClass> classes) {
    m_xmlClasses = classes;
  }

  /**
   * @deprecated Use {@code getLocalParameters()} or {@code getAllParameters()}
   */
  @Deprecated
  public Map<String, String> XmlTest.getParameters() {
    return getAllParameters();
  }

  /**
   * @deprecated Use {@code getLocalParameters()} instead
   *
   * @return the parameters defined on this <test> tag only
   */
  @Deprecated
  public Map<String, String> XmlTest.getTestParameters() {
    return getLocalParameters();
  }

  /**
   * @deprecated Use {@link #setPreserveOrder(Boolean)} instead
   */
  @Deprecated
  public void XmlTest.setPreserveOrder(String preserveOrder) {
    setPreserveOrder(Boolean.valueOf(preserveOrder));
  }

  /**
   * @deprecated Use {@code getLocalParameters()} or {@code getAllParameters()}
   */
  @Deprecated
  public Map<String, String> XmlClass.getParameters() {
    return getAllParameters();
  }

  /**
   * @deprecated Use {@code getLocalParameters()} or {@code getAllParameters()}
   */
  @Deprecated
  public Map<String, String> XmlInclude.getParameters() {
    return getAllParameters();
  }

  /**
   * @deprecated use {@link #createSuite(String, java.util.Collection, java.util.Map,
   * java.util.Collection, java.util.Map, String, int)} instead.
   */
  @Deprecated
  public static LaunchSuite SuiteGenerator.createCustomizedSuite(String projectName,
                                                  Collection<String> packageNames, Collection<String> classNames,
                                                  Collection<String> methodNames, Collection<String> groupNames,
                                                  Map<String, String> parameters, String annotationType, int logLevel) {
    if ((null != groupNames) && !groupNames.isEmpty()) {
      return new LaunchSuite.ClassListSuite(projectName, packageNames, classNames, groupNames,
          parameters, annotationType, logLevel);
    } else if ((classNames != null && classNames.size() > 1) || packageNames != null
        && packageNames.size() > 0) {
      return new LaunchSuite.ClassListSuite(projectName, packageNames, classNames, groupNames,
          parameters, annotationType, logLevel);
    } else {
      return new LaunchSuite.MethodsSuite(projectName, classNames.iterator().next(), methodNames,
          parameters, annotationType, logLevel);
    }
  }
}
