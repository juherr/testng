package test.factory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FactoryDataProviderGitHub892Sample {

  private final String companyName;
  private List<Object[]> as;

  @Factory(dataProvider = "getAccounts", dataProviderClass = DPClassGitHub892.class)
  public FactoryDataProviderGitHub892Sample(String companyName) {
    System.out.println(
        "FactoryDataProviderGitHub892Sample(" + companyName + ") Thread:" + Thread.currentThread()
            .getName());
    this.companyName = companyName;
  }

  @BeforeClass
  public void beforeClass() {
    System.out.println(
        "FactoryDataProviderGitHub892Sample#beforeClass with:" + companyName + " Thread:" + Thread
            .currentThread().getName());
    as = Arrays.asList(new Object[]{companyName + " ENTRY 1"},
                       new Object[]{companyName + " ENTRY 2"});
  }

  @DataProvider
  public Iterator<Object[]> getValues() {
    return as.iterator();
  }

  @Test(dataProvider = "getValues")
  public void test1(String cname) {
    System.out.println(
        "FactoryDataProviderGitHub892Sample#test1: " + companyName + " IP: " + cname + " Thread:"
        + Thread.currentThread().getName());
  }

  @Test(dataProvider = "getValues")
  public void test2(String cname) {
    System.out.println(
        "FactoryDataProviderGitHub892Sample#test2: " + companyName + " IP: " + cname + " Thread:"
        + Thread.currentThread().getName());
  }

  @AfterClass
  public void afterClass() {
    System.out.println(
        "FactoryDataProviderGitHub892Sample#afterClass " + companyName + " Thread:" + Thread
            .currentThread().getName());
  }
}
