package com.vinsGuru.vendarPortal;

import com.vinsguru.pages.vendorportal.DashboardPage;
import com.vinsguru.pages.vendorportal.LoginPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class VendorPortalTest {

	private WebDriver driver;

	@BeforeTest
	public void setDriver(@Optional String noOfPassengers, @Optional String expectedPrice) {

		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();

	}

	@Test
	public void loginTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
		Assert.assertTrue(loginPage.isAt());
		loginPage.login("sam", "sam");
	}

	@Test(dependsOnMethods = "loginTest")
	public void dashBoardTest() {
		DashboardPage dashboardPage = new DashboardPage(driver);
		assertTrue(dashboardPage.isAt());
		dashboardPage.searchOrderHistoryBy("sam");
//		assertEquals(dashboardPage.getSearchResultsCount(), "8");
		dashboardPage.logout();
	}
}