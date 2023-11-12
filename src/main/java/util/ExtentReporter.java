package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	public static ExtentReports getExtentReport() {
		
		String ExtentReportFilePath=System.getProperty("user.dir")+"\\reports\\extentReport.html";
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(ExtentReportFilePath);
		sparkReporter.config().setReportName("Test Automation Results");
		sparkReporter.config().setDocumentTitle("NinjaTest Results");
		
		
		ExtentReports extentReport=new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Selenium Version", "4.4.0");
		extentReport.setSystemInfo("Operating System","Windows 11");
		extentReport.setSystemInfo("Executed By","Jithin");
		
		return extentReport;
		
	}

}
