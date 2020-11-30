package org.testng;

import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.Collections;

import org.testng.annotations.Guice;
import org.testng.internal.annotations.AnnotationHelper;
import org.testng.xml.XmlTest;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

/**
 * This class defines a test context which contains all the information for a given test run. An
 * instance of this context is passed to the test listeners so they can query information about
 * their environment.
 *
 * @author Cedric Beust, Aug 6, 2004
 */
public interface ITestContext extends IAttributes {

  /** @return The name of this test. */
  String getName();

  /** @return When this test started running. */
  Date getStartDate();

  /** @return When this test stopped running. */
  Date getEndDate();

  /** @return A list of all the tests that run successfully. */
  IResultMap getPassedTests();

  /** @return A list of all the tests that were skipped */
  IResultMap getSkippedTests();

  /**
   * @return A list of all the tests that failed but are being ignored because annotated with a
   *     successPercentage.
   */
  IResultMap getFailedButWithinSuccessPercentageTests();

  /**
   * @return A map of all the tests that passed, indexed by their ITextMethor.
   * @see org.testng.ITestNGMethod
   */
  IResultMap getFailedTests();

  /** @return All the groups that are included for this test run. */
  String[] getIncludedGroups();

  /** @return All the groups that are excluded for this test run. */
  String[] getExcludedGroups();

  /** @return Where the reports will be generated. */
  String getOutputDirectory();

  /** @return The Suite object that was passed to the runner at start-up. */
  ISuite getSuite();

  /** @return All the test methods that were run. */
  ITestNGMethod[] getAllTestMethods();

  /**
   * @return The host where this test was run, or null if it was run locally. The returned string
   *     has the form: host:port
   */
  String getHost();

  /** @return All the methods that were not included in this test run. */
  Collection<ITestNGMethod> getExcludedMethods();

  /** @return The information about the successful configuration method invocations. */
  IResultMap getPassedConfigurations();

  /** @return The information about the skipped configuration method invocations. */
  IResultMap getSkippedConfigurations();

  /** @return The information about the failed configuration method invocations. */
  IResultMap getFailedConfigurations();

  /** @return the current XmlTest. */
  XmlTest getCurrentXmlTest();

  @Deprecated
  List<Module> getGuiceModules(Class<? extends Module> cls);

  @Deprecated
  default void addGuiceModule(Module module) {}

  @Deprecated
  default List<Module> getAllGuiceModules() {
    return Collections.emptyList();
  }

  @Deprecated
  Injector getInjector(List<Module> moduleInstances);

  @Deprecated
  Injector getInjector(IClass iClass);

  default <T> T getInstance(IClass iClass, Class<? extends T> targetClass, Supplier<T> fallback) {
    Guice guice = AnnotationHelper.findAnnotationSuperClasses(Guice.class, iClass.getRealClass());
    if (guice == null) {
      return fallback.get();
    } else {
      Injector injector = getInjector(iClass);
      return injector.getInstance(targetClass);
    }
  }

  @Deprecated
  void addInjector(List<Module> moduleInstances, Injector injector);
}
