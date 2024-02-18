package org.testng.thread;

import java.util.List;

/** A runnable object that is used by {@code GraphThreadPoolExecutor} to execute tasks */
public interface IWorker<T> extends Runnable, Comparable<IWorker<T>> {

  /** Returns a list of tasks this worker is working on. */
  List<T> getTasks();

  /** Returns the maximum time allowed for the worker to complete the task. */
  long getTimeOut();

  /** Returns the priority of this task. */
  int getPriority();

  default long getCurrentThreadId() {
    return -1;
  }

  default void setThreadIdToRunOn(long threadIdToRunOn) {}

  default long getThreadIdToRunOn() {
    return -1;
  }

  default boolean completed() {
    return true;
  }
}
