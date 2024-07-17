package com.tests;

import java.time.Duration;
import java.net.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeTest
	public void setDriver() throws MalformedURLException {
		if (Boolean.getBoolean("seleniuum.grid.enabled")) {
			this.driver = getRemoteDriver();
		} else {
			this.driver = getWebDriver();
		}
	}

	@SuppressWarnings("deprecation")
	private WebDriver getRemoteDriver() throws MalformedURLException {
		org.openqa.selenium.Capabilities capbilities;
		if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
			capbilities = new ChromeOptions();
		} else {
			capbilities = new FirefoxOptions();
		}
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