package org.testng;

import org.aspectj.lang.annotation.Before;
import org.testng.internal.Utils;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

public privileged aspect TestNGDeprecatedAspect {

  private static TestNG TestNG.m_instance;

  /**
   * Define whether this run will be run in parallel mode.
   * @deprecated Use #setParallel(XmlSuite.ParallelMode) instead
   */
  @Deprecated
  public void TestNG.setParallel(String parallel) {
    if (parallel == null) {
      setParallel(XmlSuite.ParallelMode.NONE);
    } else {
      setParallel(XmlSuite.ParallelMode.getValidParallel(parallel));
    }
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later /!\ Caution: IntelliJ is using it. Check with @akozlova before removing it
  @Deprecated
  public void TestNG.addListener(Object listener) {
    if (! (listener instanceof ITestNGListener)) {
      exitWithError("Listener " + listener
          + " must be one of ITestListener, ISuiteListener, IReporter, "
          + " IAnnotationTransformer, IMethodInterceptor or IInvokedMethodListener");
    }
    addListener((ITestNGListener) listener);
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later
  @Deprecated
  public void TestNG.addListener(IInvokedMethodListener listener) {
    if (!m_invokedMethodListeners.contains(listener)) {
      addListener((ITestNGListener) listener);
    }
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later
  @Deprecated
  public void TestNG.addListener(ISuiteListener listener) {
    if (!m_suiteListeners.contains(listener)) {
      addListener((ITestNGListener) listener);
    }
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later
  @Deprecated
  public void TestNG.addListener(ITestListener listener) {
    if (!m_testListeners.contains(listener)) {
      addListener((ITestNGListener) listener);
    }
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later
  @Deprecated
  public void TestNG.addListener(IClassListener listener) {
    if (!m_classListeners.contains(listener)) {
      addListener((ITestNGListener) listener);
    }
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later
  @Deprecated
  public void TestNG.addListener(IReporter listener) {
    if (!m_reporters.contains(listener)) {
      addListener((ITestNGListener) listener);
    }
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later
  @Deprecated
  public void TestNG.addInvokedMethodListener(IInvokedMethodListener listener) {
    if (!m_invokedMethodListeners.contains(listener)) {
      addListener((ITestNGListener) listener);
    }
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later
  @Deprecated
  public void TestNG.addAlterSuiteListener(IAlterSuiteListener l) {
    addListener((ITestNGListener) l);
  }

  /**
   * @deprecated Use addListener(ITestNGListener) instead
   */
  // TODO remove later
  @Deprecated
  public void TestNG.addExecutionListener(IExecutionListener l) {
    addListener((ITestNGListener) l);
  }

  /**
   * This method is invoked by Maven's Surefire, only remove it once
   * Surefire has been modified to no longer call it.
   *
   * @deprecated This method does nothing
   */
  @Deprecated
  public void TestNG.setSourcePath(String path) {
    // nop
  }

  /**
   * This method is invoked by Maven's Surefire to configure the runner,
   * do not remove unless you know for sure that Surefire has been updated
   * to use the new configure(CommandLineArgs) method.
   *
   * @deprecated use new configure(CommandLineArgs) method
   */
  @SuppressWarnings({"unchecked"})
  @Deprecated
  public void TestNG.configure(Map cmdLineArgs) {
    CommandLineArgs result = new CommandLineArgs();

    Integer verbose = (Integer) cmdLineArgs.get(CommandLineArgs.LOG);
    if (null != verbose) {
      result.verbose = verbose;
    }
    result.outputDirectory = (String) cmdLineArgs.get(CommandLineArgs.OUTPUT_DIRECTORY);

    String testClasses = (String) cmdLineArgs.get(CommandLineArgs.TEST_CLASS);
    if (null != testClasses) {
      result.testClass = testClasses;
    }

    String testNames = (String) cmdLineArgs.get(CommandLineArgs.TEST_NAMES);
    if (testNames != null) {
      result.testNames = testNames;
    }

    String useDefaultListeners = (String) cmdLineArgs.get(CommandLineArgs.USE_DEFAULT_LISTENERS);
    if (null != useDefaultListeners) {
      result.useDefaultListeners = useDefaultListeners;
    }

    result.groups = (String) cmdLineArgs.get(CommandLineArgs.GROUPS);
    result.excludedGroups = (String) cmdLineArgs.get(CommandLineArgs.EXCLUDED_GROUPS);
    result.testJar = (String) cmdLineArgs.get(CommandLineArgs.TEST_JAR);
    result.xmlPathInJar = (String) cmdLineArgs.get(CommandLineArgs.XML_PATH_IN_JAR);
    result.junit = (Boolean) cmdLineArgs.get(CommandLineArgs.JUNIT);
    result.mixed = (Boolean) cmdLineArgs.get(CommandLineArgs.MIXED);
    result.skipFailedInvocationCounts = (Boolean) cmdLineArgs.get(
        CommandLineArgs.SKIP_FAILED_INVOCATION_COUNTS);
    String parallelMode = (String) cmdLineArgs.get(CommandLineArgs.PARALLEL);
    if (parallelMode != null) {
      result.parallelMode = XmlSuite.ParallelMode.getValidParallel(parallelMode);
    }

    String threadCount = (String) cmdLineArgs.get(CommandLineArgs.THREAD_COUNT);
    if (threadCount != null) {
      result.threadCount = Integer.parseInt(threadCount);
    }

    // Not supported by Surefire yet
    Integer dptc = (Integer) cmdLineArgs.get(CommandLineArgs.DATA_PROVIDER_THREAD_COUNT);
    if (dptc != null) {
      result.dataProviderThreadCount = dptc;
    }
    String defaultSuiteName = (String) cmdLineArgs.get(CommandLineArgs.SUITE_NAME);
    if (defaultSuiteName != null) {
      result.suiteName = defaultSuiteName;
    }

    String defaultTestName = (String) cmdLineArgs.get(CommandLineArgs.TEST_NAME);
    if (defaultTestName != null) {
      result.testName = defaultTestName;
    }

    Object listeners = cmdLineArgs.get(CommandLineArgs.LISTENER);
    if (listeners instanceof List) {
      result.listener = Utils.join((List<?>) listeners, ",");
    } else {
      result.listener = (String) listeners;
    }

    String ms = (String) cmdLineArgs.get(CommandLineArgs.METHOD_SELECTORS);
    if (null != ms) {
      result.methodSelectors = ms;
    }

    String objectFactory = (String) cmdLineArgs.get(CommandLineArgs.OBJECT_FACTORY);
    if(null != objectFactory) {
      result.objectFactory = objectFactory;
    }

    String runnerFactory = (String) cmdLineArgs.get(CommandLineArgs.TEST_RUNNER_FACTORY);
    if (null != runnerFactory) {
      result.testRunnerFactory = runnerFactory;
    }

    String reporterConfigs = (String) cmdLineArgs.get(CommandLineArgs.REPORTER);
    if (reporterConfigs != null) {
      result.reporter = reporterConfigs;
    }

    String failurePolicy = (String)cmdLineArgs.get(CommandLineArgs.CONFIG_FAILURE_POLICY);
    if (failurePolicy != null) {
      result.configFailurePolicy = failurePolicy;
    }

    Object  suiteThreadPoolSize = cmdLineArgs.get(CommandLineArgs.SUITE_THREAD_POOL_SIZE);
    if (null != suiteThreadPoolSize) {
      if (suiteThreadPoolSize instanceof String){
        result.suiteThreadPoolSize=Integer.parseInt((String) suiteThreadPoolSize);
      }
      if (suiteThreadPoolSize instanceof Integer){
        result.suiteThreadPoolSize=(Integer) suiteThreadPoolSize;
      }
    }

    configure(result);
  }

  /**
   * @deprecated The TestNG version is now established at load time. This
   * method is not required anymore and is now a no-op.
   */
  @Deprecated
  public static void TestNG.setTestNGVersion() {
    LOGGER.info("setTestNGVersion has been deprecated.");
  }

  /**
   * Returns true if this is the JDK 1.4 JAR version of TestNG, false otherwise.
   *
   * @return true if this is the JDK 1.4 JAR version of TestNG, false otherwise.
   */
  @Deprecated
  public static boolean TestNG.isJdk14() {
    return false;
  }

  /**
   * @deprecated Use {@link #setConfigFailurePolicy(org.testng.xml.XmlSuite.FailurePolicy)} instead
   */
  @Deprecated
  public void TestNG.setConfigFailurePolicy(String failurePolicy) {
    setConfigFailurePolicy(XmlSuite.FailurePolicy.getValidPolicy(failurePolicy));
  }

  // DEPRECATED: to be removed after a major version change
  /**
   * @deprecated since 5.1
   */
  @Deprecated
  public static TestNG TestNG.getDefault() {
    return m_instance;
  }

  @Before("private void init(boolean useDefaultListeners)")
  private void TestNG.setDefault() {
    m_instance = this;
  }
}