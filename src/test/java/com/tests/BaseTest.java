package com.tests;

import java.net.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.github.dockerjava.api.model.Config;
import com.utils.Constants;

public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeSuite
	public void setupConfig() {
		com.utils.Config.initialize();
	}

	@BeforeTest
	@Parameters({ "browser" })
	public void setDriver(String browser) throws MalformedURLException {
		System.out.println(Boolean.getBoolean("selenium.grid.enabled"));
		System.out.println(browser);
		if (Boolean.getBoolean("selenium.grid.enabled")) {
			this.driver = getRemoteDriver(browser);
		} else {
			this.driver = getWebDriver();
		}
	}

	private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
		org.openqa.selenium.Capabilities capbilities;
		if (browser.equalsIgnoreCase("chrome")) {
			capbilities = new ChromeOptions();
		} else {
			capbilities = new FirefoxOptions();
		}
//		String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
//		String hubHost = Config.get(Constants.GRID_HUB_HOST);
//		String url = String.format(urlFormat, hubHost)
		return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capbilities);
	}

	private WebDriver getWebDriver() {
		return new ChromeDriver();
	}

	@AfterTest
	public void quiteDriver() {
		this.driver.quit();
	}
}