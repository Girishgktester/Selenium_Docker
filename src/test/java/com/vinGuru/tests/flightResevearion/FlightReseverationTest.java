package com.vinGuru.tests.flightResevearion;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.vinsguru.pages.flightsReseveration.FlightConfirmationPage;
import com.vinsguru.pages.flightsReseveration.FlightSearchPage;
import com.vinsguru.pages.flightsReseveration.FlightSelectionPage;
import com.vinsguru.pages.flightsReseveration.RegestrationPage;
import com.vinsguru.pages.flightsReseveration.RegistrationConfirmationPage;

public class FlightReseverationTest {

	private WebDriver driver;
	private String noOfPassengers;
	private String expectedPrice;

	@BeforeTest
	@Parameters({ "noOfPassengers", "expectedPrice" })
	public void setDriver(@Optional String noOfPassengers, @Optional String expectedPrice) {

		this.noOfPassengers = noOfPassengers;
		this.expectedPrice = expectedPrice;
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();

	}

	@Test
	public void userRegistaraionTest() {
		RegestrationPage regstrationPage = new RegestrationPage(driver);
		regstrationPage.getUrl("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html#");
		assertTrue(regstrationPage.isAt());
		regstrationPage.enterUserNameAndPassword("Test", "Docker");
		regstrationPage.enterCred("Selenium@Docker.com", "Test@123");
		regstrationPage.enterAddress("12abc", "Gulbarga", "585102");
		regstrationPage.register();
	}

	@Test(dependsOnMethods = "userRegistaraionTest")
	public void registartionConfirmationPage() {
		RegistrationConfirmationPage regitsrationConfirmarion = new RegistrationConfirmationPage(driver);
		assertTrue(regitsrationConfirmarion.isAt());
		regitsrationConfirmarion.gotoFlightSearch();

	}

	@Test(dependsOnMethods = "registartionConfirmationPage")
	public void flightSearchTest() {
		FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
		assertTrue(flightSearchPage.isAt());
		System.out.println(noOfPassengers);
		flightSearchPage.selectPassenger("1");
		flightSearchPage.searchForFlights();

	}

	@Test(dependsOnMethods = "flightSearchTest")
	public void flightSelectionTest() {
		FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
		assertTrue(flightSelectionPage.isAt());
		flightSelectionPage.selectFlights();
		flightSelectionPage.confirmFlights();

	}

	@Test(dependsOnMethods = "flightSelectionTest")
	public void flightReseverationConfirmationTest() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		assertTrue(flightConfirmationPage.isAt());
		assertEquals(flightConfirmationPage.getPrice(),expectedPrice);
	}

	@AfterTest
	public void quiteDriver() {
		this.driver.quit();
	}
}
