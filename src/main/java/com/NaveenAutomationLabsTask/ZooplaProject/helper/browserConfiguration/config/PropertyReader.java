package com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.NaveenAutomationLabsTask.ZooplaProject.helper.browserConfiguration.BrowserTypes;
import com.NaveenAutomationLabsTask.ZooplaProject.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {
	private static FileInputStream file;
	public static Properties or;

	public PropertyReader() {

		try {
			String filePath = ResourceHelper.getResourcePath("/src/main/resources/configfile/config.properties");
			file = new FileInputStream(new File(filePath));
			or = new Properties();
			or.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImplicitWait() {
		return Integer.parseInt(or.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(or.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(or.getProperty("pageloadtime"));
	}

	public BrowserTypes getBrowserTypes() {
		return BrowserTypes.valueOf(or.getProperty("browsertype"));
	}

	public String getUrl() {
		if (System.getProperty("url") != null) {
			return System.getProperty("url");
		}
		return or.getProperty("url");
	}

	public String getSearchCityName() {

		return or.getProperty("searchCity");
	}

}
