package org.testng;

import org.testng.collections.Objects;
import org.testng.internal.SuiteRunnerMap;
import org.testng.internal.Utils;
import org.testng.internal.thread.graph.IWorker;
import org.testng.xml.XmlSuite;

import java.util.Collections;
import java.util.List;

/**
 * An {@code IWorker} that is used to encapsulate and run Suite Runners
 */
public class SuiteRunnerWorker<S extends ISuite> implements IWorker<S> {

  private final S m_suiteRunner;
  private final int m_verbose;
  private final String m_defaultSuiteName;
  private final SuiteRunnerMap<S> m_suiteRunnerMap;

  public SuiteRunnerWorker(S suiteRunner,
      SuiteRunnerMap<S> suiteRunnerMap,
      int verbose,
      String defaultSuiteName) {
    m_suiteRunnerMap = suiteRunnerMap;
    m_suiteRunner = suiteRunner;
    m_verbose = verbose;
    m_defaultSuiteName = defaultSuiteName;
  }

  /*
   * Runs a suite
   * suiteRunnerMap -> map of suiteRunners that are updated with test results
   * xmlSuite -> XML suites to run
   */
  @Override
  public void run() {
    XmlSuite xmlSuite = m_suiteRunner.getXmlSuite();

    if (m_verbose > 0) {
      String allFiles = "Running:\n  " +
          (xmlSuite.getFileName() != null ? xmlSuite.getFileName() : m_defaultSuiteName) +
          '\n';
      Utils.log("TestNG", 0, allFiles);
    }

    m_suiteRunnerMap.get(xmlSuite).run();
  }

  @Override
  public int compareTo(IWorker<S> arg0) {
    /*
     * Dummy Implementation
     *
     * Used by IWorkers to prioritize execution in parallel. Not required by
     * this Worker in current implementation
     */
    return 0;
  }

  @Override
  public List<S> getTasks() {
    return Collections.<S>singletonList(m_suiteRunner);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(getClass())
        .add("name", m_suiteRunner.getName())
        .toString();
  }

  @Override
  public long getTimeOut()
  {
    return m_suiteRunner.getXmlSuite().getTimeOut(Long.MAX_VALUE);
  }

  @Override
  public int getPriority() {
    // this class doesnt support priorities yet
    return 0;
  }
}

