package test.factory;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DPClassGitHub892 {

  private static String[] ACCOUNT_LIST;

  @Parameters("accounts")
  public DPClassGitHub892(@Optional("sun_company1,sun2_company2") String accountList) {
    System.out.println(
        "DPClassGitHub892(" + accountList + ") Thread:" + Thread.currentThread().getName());
    ACCOUNT_LIST = accountList.split(",");
  }

  @DataProvider(name = "getAccounts")
  public static Iterator<Object[]> getAccounts() {
    System.out
        .println("DPClassGitHub892#getAccounts:" + Arrays.asList(ACCOUNT_LIST) + " Thread:" + Thread
            .currentThread().getName());
    ArrayList<Object[]> companies = new ArrayList<>();
    for (String account : ACCOUNT_LIST) {
      companies.add(new Object[]{account});
    }
    return companies.iterator();
  }

  @Test
  public void tests() {
    System.out.println("DPClassGitHub892:tests Thread:" + Thread.currentThread().getName());
  }
}
