package com.NaveenAutomationLabsTask.testScripts.testCases;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.assertion.AssertionHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.config.ObjectReader;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.logger.LoggerHelper;
import com.NaveenAutomationLabsTask.ZooplaProject.pageObject.AgentDetailsPage;
import com.NaveenAutomationLabsTask.ZooplaProject.pageObject.HomePage;
import com.NaveenAutomationLabsTask.ZooplaProject.pageObject.PropertSearchResultPage;
import com.NaveenAutomationLabsTask.ZooplaProject.pageObject.PropertiesDetailsPage;
import com.NaveenAutomationLabsTask.ZooplaProject.testBase.TestBase;

public class TestCase_001_AgentNameVerification extends TestBase {

	private final Logger log = LoggerHelper.getLogger(TestCase_001_AgentNameVerification.class);
	HomePage homePage;
	PropertSearchResultPage propertSearchResultPage;
	PropertiesDetailsPage propertiesDetailsPage;
	AgentDetailsPage agentDetailsPage;

	@BeforeClass
	public void beforeClass() {
			getApplicationUrl(ObjectReader.reader.getUrl());
			homePage = new HomePage(driver);
			propertSearchResultPage = new PropertSearchResultPage(driver);
			propertiesDetailsPage = new PropertiesDetailsPage(driver);
			agentDetailsPage = new AgentDetailsPage(driver);
	}

	@Test
	public void testSearchCity() {
		homePage.ClickcookiesAcceptBtn();
		homePage.enterValuesInSearchBox(ObjectReader.reader.getSearchCityName());
		homePage.ClickSearchBtn();
		propertSearchResultPage.printPriceInDescendingOrder();
		propertSearchResultPage.clickFifthProperty();
		String PropertiesDetailsPageAgentName = propertiesDetailsPage.getPropertiesDetailsPageAgentName();
		String propertiesDetailsPageAgentAddress = propertiesDetailsPage.getpropertiesDetailsPageAgentAddress();
		propertiesDetailsPage.clickOnPropertiesDetailsPageAgentName();
		String agentDetailsPageAgentName = agentDetailsPage.getagentDetailsPageAgentName();
		String agentDetailsPageAgentAddress = agentDetailsPage.getagentDetailsPageAgentAddress();
		AssertionHelper.verifyText(PropertiesDetailsPageAgentName, agentDetailsPageAgentName);
		AssertionHelper.verifyText(propertiesDetailsPageAgentAddress, agentDetailsPageAgentAddress);		
	}
}
