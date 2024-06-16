package com.vinsguru.pages.flightsReseveration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.vinsguru.pages.AbstractPage;

public class FlightSearchPage extends AbstractPage {

	public FlightSearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "passengers")
	private WebElement passengersSelect;

	@FindBy(id = "search-flights")
	private WebElement searchFlightsButton;

	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.passengersSelect));
		return this.passengersSelect.isDisplayed();
	}

	public void selectPassenger(String numberOfpassengers) {

		Select select = new Select(this.passengersSelect);
		select.selectByValue(numberOfpassengers);

	}

	public void searchForFlights() {
		this.searchFlightsButton.click();
	}
}
