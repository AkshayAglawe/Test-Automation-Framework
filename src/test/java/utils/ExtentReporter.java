package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
public static ExtentReports getExtentReport() {
		
	String extentReportPath = "./reports/extentreport.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportPath);
		sparkReporter.config().setReportName("Assignment Automation Results");	
		sparkReporter.config().setDocumentTitle("Assignment Automation Test Results");
	
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Playwright Version","1.45.0");
		extentReport.setSystemInfo("Operating System","Windows 11");
		extentReport.setSystemInfo("Executed By","Akshay Aglawe");
		
		return extentReport;
	
	}
}
