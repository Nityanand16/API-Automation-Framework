package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    // Thread-safe test object (VERY IMPORTANT for parallel execution)
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize report
    public static ExtentReports getExtent() {

        if (extent == null) {

            ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-report.html");

            reporter.config().setReportName("API Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Framework", "RestAssured + TestNG");
            extent.setSystemInfo("Author", "Nitya");
        }

        return extent;
    }

    // Create test
    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getExtent().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    // Get current test
    public static ExtentTest getTest() {
        return test.get();
    }
}