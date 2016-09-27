package org.testng.collections;

import java.util.*;

public privileged aspect CollectionsDeprecatedAspect {

  @Deprecated
  public List<K> MultiMap.getKeys() {
    return new ArrayList<>(keySet());
  }

  @Deprecated
  public int MultiMap.getSize() {
    return size();
  }

  @Deprecated
  public C MultiMap.remove(K key) {
    return removeAll(key);
  }

  @Deprecated
  public Set<Map.Entry<K, C>> MultiMap.getEntrySet() {
    return entrySet();
  }

  @Deprecated
  public Collection<C> MultiMap.getValues() {
    return values();
  }

  @Deprecated
  public static <K, V> ListMultiMap<K, V> ListMultiMap.create() {
    return Maps.newListMultiMap();
  }
}
