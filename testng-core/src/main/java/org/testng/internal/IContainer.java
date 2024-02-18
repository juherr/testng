package org.testng.internal;

/** Represents the capabilities of a simple container to hold data */
public interface IContainer<M> {

  /** Returns data from the container */
  M[] getItems();

  /** Clears the container */
  void clearItems();

  /** Returns <code>true</code> if the container items were cleared. */
  boolean isCleared();
}
