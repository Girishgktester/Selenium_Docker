package com.tests;

import java.net.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.listners.TestListners;
import com.utils.Config;
import com.utils.Constants;

@Listeners({TestListners.class})
public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeSuite
	public void setupConfig() {
		Config.initialize();
	}

	@BeforeTest
	@Parameters({ "browser" })
	public void setDriver(ITestContext con, String browser) throws MalformedURLException {
//		this.driver = Boolean.getBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver(browser) : getWebDriver();
		
		if (Boolean.getBoolean("selenium.grid.enabled")) {
			this.driver = getRemoteDriver(browser);
		} else {
			this.driver = getWebDriver();
		}
		con.setAttribute(Constants.DRIVER, this.driver);
	}

	@SuppressWarnings("deprecation")
	private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
		org.openqa.selenium.Capabilities capbilities;
		if (browser.equalsIgnoreCase("chrome")) {
			capbilities = new ChromeOptions();
		} else {
			capbilities = new FirefoxOptions();
		}
		String urlFormat = com.utils.Config.get(Constants.GRID_URL_FORMAT);
		String hubHost = com.utils.Config.get(Constants.GRID_HUB_HOST);
		String url = String.format(urlFormat, hubHost);
		System.out.println(url);
		return new RemoteWebDriver(new URL(url), capbilities);
	}

	private WebDriver getWebDriver() {
		return new ChromeDriver();
	}

	@AfterTest
	public void quiteDriver() {
		this.driver.quit();
	}
}