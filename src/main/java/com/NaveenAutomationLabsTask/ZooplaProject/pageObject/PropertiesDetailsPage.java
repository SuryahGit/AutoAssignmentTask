package com.NaveenAutomationLabsTask.ZooplaProject.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.logger.LoggerHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.wait.WaitHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.testBase.TestBase;
import com.aventstack.extentreports.Status;

public class PropertiesDetailsPage {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(PropertiesDetailsPage.class);
	WaitHelper wait;
	
	@FindBy(xpath="//*[@id='dp-sticky-element']/div/div[1]/a/div[2]/h4")
	WebElement propertiesDetailsPageAgentName;
	
	@FindBy(xpath="//*[@id='main-content']/div[2]/div[4]/section[1]/div/a/div[2]/address")
	WebElement propertiesDetailsPageAgentAddress;
	
	public PropertiesDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
		TestBase.logExtentReport("PropertiesDetailsPage Object Created");
	}
	
	public String getPropertiesDetailsPageAgentName()
	{
		log.info("Properties Details Page Agent Name is "+propertiesDetailsPageAgentName.getText());
		logExtentReport("Properties Details Page Agent Name is "+propertiesDetailsPageAgentName.getText());
		System.out.println("Properties Details Page Agent Name is "+propertiesDetailsPageAgentName.getText());
		return propertiesDetailsPageAgentName.getText();
	}
		
	public String getpropertiesDetailsPageAgentAddress()
	{
		log.info("Properties Details Page Agent Address is "+propertiesDetailsPageAgentAddress.getText());
		logExtentReport("Properties Details Page Agent Address is "+propertiesDetailsPageAgentAddress.getText());
		System.out.println("Properties Details Page Agent Address is "+propertiesDetailsPageAgentAddress.getText());
		return propertiesDetailsPageAgentAddress.getText();
	}
	
	public AgentDetailsPage clickOnPropertiesDetailsPageAgentName()
	{
		log.info("clicking on the properties details page agent name ");
		logExtentReport("clicking on the properties details page agent name");
		propertiesDetailsPageAgentName.click();
		return new AgentDetailsPage(driver);
	}
	
	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}
}
