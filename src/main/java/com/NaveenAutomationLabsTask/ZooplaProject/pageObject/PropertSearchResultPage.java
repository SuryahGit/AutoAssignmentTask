package com.NaveenAutomationLabsTask.ZooplaProject.pageObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.logger.LoggerHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.wait.WaitHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.testBase.TestBase;
import com.aventstack.extentreports.Status;

public class PropertSearchResultPage {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(PropertSearchResultPage.class);
	WaitHelper wait;

	@FindBy(xpath = "//*[@class='srp clearfix   ']/div/div[2]/a")
	List<WebElement> projectList;

	public PropertSearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		TestBase.logExtentReport("PropertSearchResultPage Object Created");
	}

	public void printPriceInDescendingOrder() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		log.info("Printing Price in DescendingOrder");
		logExtentReport("Printing Price in DescendingOrder");
		System.out.println("Printing Price in DescendingOrder");
		for (int i = 0; i < projectList.size(); i++) {
			String projectPrice = projectList.get(i).getText().replace("£", "").replace(",", "");
			if (!projectPrice.equalsIgnoreCase("POA")) {
				String[] arrOfStr = projectPrice.split(" ", 2);
				list.add(Integer.parseInt(arrOfStr[0]));
			}
		}
	//	System.out.println("Array list size " + list.size());
		Collections.sort(list, Collections.reverseOrder());
		for (int s : list) {
			System.out.println("£" + s);
		}
		System.out.println("Printed Price in DescendingOrder");
	}

	public PropertiesDetailsPage clickFifthProperty() {

		for (int i = 0; i < projectList.size(); i++) {
			System.out.println(projectList.get(i).getText());
			if (i == 4) {
				log.info("Cliking the 5th Property");
				logExtentReport("Cliking the 5th Property");
				Actions act = new Actions(driver);
				act.moveToElement(projectList.get(i)).build().perform();
				projectList.get(i).click();
				break;
			}
		}
		return new PropertiesDetailsPage(driver);
	}

	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}
}
