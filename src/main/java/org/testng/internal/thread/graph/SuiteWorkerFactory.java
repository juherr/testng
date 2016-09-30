package org.testng.internal.thread.graph;

import org.testng.ISuite;
import org.testng.SuiteRunnerWorker;
import org.testng.collections.Lists;
import org.testng.internal.SuiteRunnerMap;

import java.util.List;

/**
 * An {@code IThreadWorkerFactory} for {@code SuiteRunner}s
 */
public class SuiteWorkerFactory<S extends ISuite> implements IThreadWorkerFactory<S> {

  private final int m_verbose;
  private final String m_defaultSuiteName;
  private final SuiteRunnerMap<S> m_suiteRunnerMap;

  public SuiteWorkerFactory(SuiteRunnerMap<S> suiteRunnerMap,
      int verbose, String defaultSuiteName) {
    m_suiteRunnerMap = suiteRunnerMap;
    m_verbose = verbose;
    m_defaultSuiteName = defaultSuiteName;
  }

  /**
   * For each suite, creates a {@code SuiteRunnerWorker}
   * @param suites set of suite runners
   * @return list of suite runner workers
   */
  @Override
  public List<IWorker<S>> createWorkers(List<S> suites) {
    List<IWorker<S>> suiteWorkers = Lists.newArrayList();
    for (S suiteRunner : suites) {
      IWorker<S> worker = new SuiteRunnerWorker<>(suiteRunner, m_suiteRunnerMap,
        m_verbose, m_defaultSuiteName);
      suiteWorkers.add(worker);
    }
    return suiteWorkers;
  }
}
