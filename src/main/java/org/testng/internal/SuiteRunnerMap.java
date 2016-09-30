package org.testng.internal;

import org.testng.ISuite;
import org.testng.TestNGException;
import org.testng.collections.Maps;
import org.testng.xml.XmlSuite;

import java.util.Collection;
import java.util.Map;

public class SuiteRunnerMap<S extends ISuite> {

  private final Map<String, S> m_map = Maps.newHashMap();

  public void put(XmlSuite xmlSuite, S suite) {
    final String name = xmlSuite.getName();
    if (m_map.containsKey(name)) {
      throw new TestNGException("SuiteRunnerMap already have runner for suite " + name);
    }
    m_map.put(name, suite);
  }

  public S get(XmlSuite xmlSuite) {
    return m_map.get(xmlSuite.getName());
  }

  public Collection<S> values() {
    return m_map.values();
  }
}
