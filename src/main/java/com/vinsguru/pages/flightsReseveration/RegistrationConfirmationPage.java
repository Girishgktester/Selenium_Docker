package com.vinsguru.pages.flightsReseveration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vinsguru.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {

	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "go-to-flights-search")
	private WebElement goToFlightsSearch;

	public void gotoFlightSearch() {
		this.goToFlightsSearch.click();
	}
	
	
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearch));
		return this.goToFlightsSearch.isDisplayed();
	}
}
