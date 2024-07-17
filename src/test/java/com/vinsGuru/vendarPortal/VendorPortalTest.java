package com.vinsGuru.vendarPortal;

import com.tests.BaseTest;
import com.utils.jsonUtils;
import com.vinsguru.pages.vendorportal.DashboardPage;
import com.vinsguru.pages.vendorportal.LoginPage;
import model.VendorPortalTestData;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends BaseTest {

	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private VendorPortalTestData testData;

	@BeforeTest
	@Parameters("testDataPath")
	public void setPage(String testDataPath) throws IOException {

		this.loginPage = new LoginPage(driver);
		this.dashboardPage = new DashboardPage(driver);
		this.testData = jsonUtils.getTestData(testDataPath, VendorPortalTestData.class);

	}

	@Test
	public void loginTest() {
		loginPage = new LoginPage(driver);
		loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
		Assert.assertTrue(loginPage.isAt());
		loginPage.login(testData.getUserName(), testData.getUserName());
	}

	@Test(dependsOnMethods = "loginTest")
	public void dashBoardTest() {
		dashboardPage = new DashboardPage(driver);
		assertTrue(dashboardPage.isAt());
		dashboardPage.searchOrderHistoryBy("adams");
		System.out.println(dashboardPage.getSearchResultsCount());
//		assertEquals(dashboardPage.getSearchResultsCount(),"8");
	}

	@Test(dependsOnMethods = "dashBoardTest")
	public void logout() {
		dashboardPage.logout();
	}
}