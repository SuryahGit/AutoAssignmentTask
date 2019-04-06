package com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.resource.ResourceHelper;



public class FirefoxBrowser {
	public FirefoxOptions getFirefoxOptions() {

		DesiredCapabilities firefox = DesiredCapabilities.firefox();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionette", true);

		FirefoxOptions firefoxOptions = new FirefoxOptions(firefox);
		// Linux
		if (System.getProperty("os.name").contains("Linux")) {
			firefoxOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return firefoxOptions;
	}

	public WebDriver getFirefoxDriver(FirefoxOptions cap) {
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.gecko.driver",
					ResourceHelper.getResourcePath("/src/main/resources/drivers/geckodriver"));
			return new FirefoxDriver(cap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.gecko.driver",
					ResourceHelper.getResourcePath("/src/main/resources/drivers/geckodriver.exe"));
			return new FirefoxDriver(cap);
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath("/usr/bin/chrome"));
			return new FirefoxDriver(cap);
		}
		return null;
	}

	public static void main(String[] args) {
		FirefoxBrowser firefox = new FirefoxBrowser();
		WebDriver driver = firefox.getFirefoxDriver(firefox.getFirefoxOptions());
		driver.manage().window().maximize();
		driver.get("https://www.housingman.com/");

	}
}
