package test.listeners.github1173;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.util.Objects;

public class FilterTestResultParamsListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        Object[] params = testResult.getParameters();
        for (int i = 0; i < params.length; i++) {
            params[i] = "Filtered: " + Objects.toString(params[i]);
        }
    }
}
