package com.NaveenAutomationLabsTask.ZooplaProject.testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.BrowserTypes;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.ChromeBrowser;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.FirefoxBrowser;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.IExploreBrowser;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.config.ObjectReader;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.config.PropertyReader;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.logger.LoggerHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.resource.ResourceHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.wait.WaitHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestBase {
	public static WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static ExtentReports extentReport;
	public static ExtentTest test;
	public static File reportDirectory;

	@BeforeSuite
	public void beforeSuite() {
		extentReport = ExtentManager.getInstatnce();
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("/src/main/resources/screenShots"));
		setUpDriver(ObjectReader.reader.getBrowserTypes());
		test = extentReport.createTest(getClass().getSimpleName());
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + "**************test started***************");
		log.info("**************" + method.getName() + " Started***************");
	}

	@AfterMethod
	public void afterMthod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName() + " Failed");
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreenShot(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getName() + " Skipped");
			test.log(Status.SKIP, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " Passed");
			/*
			 * String imagePath = captureScreenShot(result.getName(), driver);
			 * test.addScreenCaptureFromPath(imagePath);
			 */
		}
		extentReport.flush();
	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			WaitHelper wait = new WaitHelper(driver);
			wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
			// driver.quit();
		}
	}

	public WebDriver getBrowserObject(BrowserTypes btype) throws Exception {
		try {
			switch (btype) {
			case Firefox:
				FirefoxBrowser firefox = new FirefoxBrowser();
				return firefox.getFirefoxDriver(firefox.getFirefoxOptions());
			case Chrome:
				ChromeBrowser chrome = new ChromeBrowser();
				return chrome.getChromeDriver(chrome.getChromeOptions());
			case Iexplorer:
				IExploreBrowser ie = new IExploreBrowser();
				return ie.getIExplorerDriver(ie.getIExplorerCapabilities());
			default:
				throw new Exception("driver not found " + btype.name());
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	public void setUpDriver(BrowserTypes btype) throws Exception {
		driver = getBrowserObject(btype);
		log.info("initialize webdriver " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public String captureScreenShot(String fileName, WebDriver driver) {

		if (driver == null) {
			log.info("driver is null");
			return null;
		}
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(reportDirectory + "/" + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			Files.copy(scrFile.toPath(), destFile.toPath());
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'></a>");
		} catch (Exception e) {

		}
		return destFile.toString();
	}

	public void getNavigationScreen(WebDriver driver) {
		log.info("Capturing UI navigation screen...");
		String screen = captureScreenShot("", driver);
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void logExtentReport(String s1) {
		test.log(Status.INFO, s1);
	}

	public void getApplicationUrl(String url) {
		log.info("Navigating Url is " + url);
		logExtentReport("Navigating Url is " + url);
		driver.get(url);
	}
}
