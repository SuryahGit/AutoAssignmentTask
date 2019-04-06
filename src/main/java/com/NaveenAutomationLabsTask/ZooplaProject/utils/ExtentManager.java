package com.NaveenAutomationLabsTask.ZooplaProject.utils;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.resource.ResourceHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstatnce() {
		if (extent == null) {
			return createInstance(ResourceHelper.getResourcePath("/src/main/resources/reports/extent.html"));
		} else {
			return extent;
		}

	}

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}
}