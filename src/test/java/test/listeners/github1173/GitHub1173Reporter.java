package test.listeners.github1173;

import org.testng.IInvokedMethod;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class GitHub1173Reporter implements IReporter {

    public final List<IInvokedMethod> invokedMethods = new ArrayList<>();

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            invokedMethods.addAll(suite.getAllInvokedMethods());
        }
    }
}
