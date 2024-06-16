package com.vinsguru.pages.flightsReseveration;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vinsguru.pages.AbstractPage;

public class FlightSelectionPage extends AbstractPage {

	public FlightSelectionPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "departure-flight")
	private List<WebElement> departureFlights;

	@FindBy(id = "arrival-flight")
	private List<WebElement> arrivalFlights;

	@FindBy(id = "confirm-flights")

	private WebElement confirmFlightsBtn;

	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsBtn));
		return this.confirmFlightsBtn.isDisplayed();
	}

	public void selectFlights() {
		int random = ThreadLocalRandom.current().nextInt(0, departureFlights.size());
		this.departureFlights.get(random);
		this.arrivalFlights.get(random);
	}

	public void confirmFlights() {
		this.confirmFlightsBtn.click();
	}

}
