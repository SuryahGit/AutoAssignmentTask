package com.NaveenAutomationLabsTask.ZooplaProject.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.logger.LoggerHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.wait.WaitHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.testBase.TestBase;
import com.aventstack.extentreports.Status;

public class AgentDetailsPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AgentDetailsPage.class);
	WaitHelper wait;

	@FindBy(xpath = "//*[@id='content']/div/h1/b[1]")
	WebElement agentDetailsPageAgentName;

	@FindBy(xpath = "//*[@id='listings-agent']/div/p[1]/span")
	WebElement agentDetailsPageAgentAddress;

	public AgentDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		TestBase.logExtentReport("PropertiesDetailsPage Object Created");
	}

	public String getagentDetailsPageAgentName() {
		log.info("Agent Details Page Agent Name is " + agentDetailsPageAgentName.getText());
		logExtentReport("Agent Details Page Agent Name is " + agentDetailsPageAgentName.getText());
		System.out.println("Agent Details Page Agent Name is " + agentDetailsPageAgentName.getText());
		return agentDetailsPageAgentName.getText();
	}

	public String getagentDetailsPageAgentAddress() {
		log.info("Agent Details Page Agent address is " + agentDetailsPageAgentAddress.getText());
		logExtentReport("Agent Details Page Agent address is " + agentDetailsPageAgentAddress.getText());
		System.out.println("Agent Details Page Agent address is " + agentDetailsPageAgentAddress.getText());
		return agentDetailsPageAgentAddress.getText();
	}
	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}
}
