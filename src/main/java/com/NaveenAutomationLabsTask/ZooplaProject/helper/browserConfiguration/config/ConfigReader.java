package com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.config;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.BrowserTypes;

public interface ConfigReader {
	public String getUrl();

	public int getImplicitWait();

	public int getExplicitWait();

	public int getPageLoadTime();

	public BrowserTypes getBrowserTypes();

	public String getSearchCityName();

}
