package com.NaveenAutomationLabsTask.ZooplaProject.helper.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.logger.LoggerHelper;


public class WaitHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This is ImplicitWait Method
	 * 
	 * @param timeOuts
	 * @param unit
	 */
	public void setImplicitWait(long timeOuts, TimeUnit unit) {
		log.info("ImplicitWait has been set to :" + timeOuts);
		driver.manage().timeouts().implicitlyWait(timeOuts, unit);
	}

	/**
	 * This is help us to get WebDriverWait Object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMillSec
	 * @return
	 */
	public WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMillSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMillSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	/**
	 * This method will make sure element is visible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMillSec
	 */
	public void waitForWebElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
			int pollingEveryInMillSec) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMillSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForWebElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is clickable now");
	}

	/**
	 * This method will make sure invisibilityOf Element
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisible now");
		return status;
	}

	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is Available Now And Switched");

	}

	/**
	 * This method will give us FluentWait Object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMillSec
	 * @return
	 */
	private Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryInMillSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMillSec)).ignoring(NoSuchElementException.class);
		return fWait;
	}

	/**
	 * This method will make sure waitForElement in FluentWait
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMillSec
	 * @return
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMillSec) {
		Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMillSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	/**
	 * This method will make sure to wait till page load
	 * 
	 * @param timeOutInSeconds
	 * @param unit
	 */
	public void pageLoadTime(long timeOutInSeconds, TimeUnit unit) {
		log.info("Waiting for page to load for :" + timeOutInSeconds + " seconds");
		driver.manage().timeouts().pageLoadTimeout(timeOutInSeconds, unit);
		log.info("Page is loaded");
	}

	public void waitForWebElementVisible(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.getText() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");

	}
}
