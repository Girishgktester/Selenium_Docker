package com.vinGuru.tests.flightResevearion;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.tests.BaseTest;
import com.vinsguru.pages.flightsReseveration.FlightConfirmationPage;
import com.vinsguru.pages.flightsReseveration.FlightSearchPage;
import com.vinsguru.pages.flightsReseveration.FlightSelectionPage;
import com.vinsguru.pages.flightsReseveration.RegestrationPage;
import com.vinsguru.pages.flightsReseveration.RegistrationConfirmationPage;

public class FlightReseverationTest extends BaseTest {

	private String noOfPassengers;
	private String expectedPrice;
	RegestrationPage regstrationPage;
	RegistrationConfirmationPage registrationConfirmationPage;
	FlightSearchPage flightSearchPage;
	FlightSelectionPage flightSelectionPage;
	FlightConfirmationPage flightConfirmationPage;

	@BeforeTest
	@Parameters({ "noOfPassengers", "expectedPrice"})
	public void setPage(String noOfPassengers, String expectedPrice) {

		this.noOfPassengers = noOfPassengers;
		this.expectedPrice = expectedPrice;
		this.regstrationPage = new RegestrationPage(driver);
		this.registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		this.flightSearchPage = new FlightSearchPage(driver);
		this.flightSelectionPage = new FlightSelectionPage(driver);
		this.flightConfirmationPage = new FlightConfirmationPage(driver);
	}

	@Test
	public void userRegistaraionTest() {
		regstrationPage = new RegestrationPage(driver);
		regstrationPage.getUrl("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html#");
		assertTrue(regstrationPage.isAt());
		regstrationPage.enterUserNameAndPassword("Test", "Docker");
		regstrationPage.enterCred("Selenium@Docker.com", "Test@123");
		regstrationPage.enterAddress("12abc", "Gulbarga", "585102");
		regstrationPage.register();
	}

	@Test(dependsOnMethods = "userRegistaraionTest")
	public void registartionConfirmationPage() {
		registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		assertTrue(registrationConfirmationPage.isAt());
		registrationConfirmationPage.gotoFlightSearch();
	}

	@Test(dependsOnMethods = "registartionConfirmationPage")
	public void flightSearchTest() {
		flightSearchPage = new FlightSearchPage(driver);
		assertTrue(flightSearchPage.isAt());
		System.out.println(noOfPassengers);
		flightSearchPage.selectPassenger("1");
		flightSearchPage.searchForFlights();

	}

	@Test(dependsOnMethods = "flightSearchTest")
	public void flightSelectionTest() {
		flightSelectionPage = new FlightSelectionPage(driver);
		assertTrue(flightSelectionPage.isAt());
		flightSelectionPage.selectFlights();
		flightSelectionPage.confirmFlights();
	}

	@Test(dependsOnMethods = "flightSelectionTest")
	public void flightReseverationConfirmationTest() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		assertTrue(flightConfirmationPage.isAt());
		assertEquals(flightConfirmationPage.getPrice(), expectedPrice);
	}
}