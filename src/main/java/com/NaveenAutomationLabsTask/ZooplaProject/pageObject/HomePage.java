package com.NaveenAutomationLabsTask.ZooplaProject.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.config.ObjectReader;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.logger.LoggerHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.wait.WaitHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.testBase.TestBase;
import com.aventstack.extentreports.Status;

public class HomePage {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(HomePage.class);
	WaitHelper wait;

	@FindBy(xpath = "//*[@id='main-nav']/ul[1]/li[3]/a")
	WebElement signInBtn;

	@FindBy(xpath = "//*[@name='q']")
	WebElement searchBox;

	@FindBy(xpath = "//*[@id='search-submit']")
	WebElement searchBtn;

	@FindBy(xpath = "//*[@id='bhome']/div[1]/div/button")
	WebElement cookiesAcceptBtn;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		wait.waitForWebElementVisible(signInBtn, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("HomePage Object Created");
	}

	public void ClickcookiesAcceptBtn() {
		log.info("Clicked on the cookies Accept Button");
		logExtentReport("Clicked on the cookies Accept Button");
		cookiesAcceptBtn.click();
	}

	public void enterValuesInSearchBox(String cityName) {
		log.info("entering city name...." + cityName);
		logExtentReport("entering city name...." + cityName);
		searchBox.sendKeys(cityName);
	}

	public PropertSearchResultPage ClickSearchBtn() {
		log.info("Clicked on the Search Button");
		logExtentReport("Clicked on the Search Button");
		searchBtn.click();
		return new PropertSearchResultPage(driver);
	}

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

}
