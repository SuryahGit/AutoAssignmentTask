package com.NaveenAutomationLabsTask.ZooplaProject.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.logger.LoggerHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.testBase.TestBase;


public class VerficationHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerficationHelper.class);

	public VerficationHelper(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			log.info("element is displayed " + element.getText());
			
			TestBase.logExtentReport("element is displayed " + element.getText());
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			log.error("element is not displayed ", e.getCause());
			TestBase.logExtentReport("element is not displayed " + e.getMessage());
			return false;
		}
	}

	public boolean isNotDisplayed(WebElement element) {
		try {
			log.info("element is displayed " + element.getText());
			TestBase.logExtentReport("element is displayed " + element.getText());
			element.isDisplayed();
			return false;
		} catch (Exception e) {
			log.error("element is not displayed");
			return true;
		}
	}

	public String getText(WebElement element) {
		if (null == element) {
			log.info("element is null");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("element text is " + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

	public String getTitle() {
		log.info("Page Title is " + driver.getTitle());
		return driver.getTitle();
	}
}
