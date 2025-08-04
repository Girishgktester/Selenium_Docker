package com.vinsguru.pages.flightsReseveration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vinsguru.pages.AbstractPage;

public class FlightSelectionPage extends AbstractPage {

	public FlightSelectionPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "(//input[@name='departure-flight'])[1]")
	private WebElement departureFlights;

	@FindBy(xpath = "(//input[@name='arrival-flight'])[1]")
	private WebElement arrivalFlights;

	@FindBy(id = "confirm-flights")
	private WebElement confirmFlightsBtn;

	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsBtn));
		return this.confirmFlightsBtn.isDisplayed();
	}

	public void selectFlights() {
		this.departureFlights.click();
		this.arrivalFlights.click();
	}

	public void confirmFlights() {
		this.confirmFlightsBtn.click();
	}

}
