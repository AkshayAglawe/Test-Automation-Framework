package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.assignment.base.BaseTest;

	public class MyListeners extends BaseTest implements ITestListener {
		
		ExtentReports extentReport = ExtentReporter.getExtentReport();
		ExtentTest extentTest;
		

		@Override
		public void onTestStart(ITestResult result) {

			
	        extentTest = extentReport.createTest(result.getName());
	        System.out.println("Test Started: " + result.getName());
	      

			
		}

		@Override
		public void onTestSuccess(ITestResult result) {
			
			extentTest.log(Status.PASS,"Test got passed");
//			ScreenshotUtil.captureScreenshot(page,result.getName()); 
			
		}

		@Override
		public void onTestFailure(ITestResult result) {
			
			extentTest.log(Status.FAIL,"Test got failed");
			extentTest.fail(result.getThrowable());
			ScreenshotUtil.captureScreenshot(page,result.getName());
			
		}

		@Override
		public void onTestSkipped(ITestResult result) {
			
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
		}

		@Override
		public void onTestFailedWithTimeout(ITestResult result) {
			
		}

		@Override
		public void onStart(ITestContext context) {
			
		}

		@Override
		public void onFinish(ITestContext context) {
			
			extentReport.flush();
			
		}
		
	}


