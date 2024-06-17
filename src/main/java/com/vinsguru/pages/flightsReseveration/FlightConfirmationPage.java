package com.vinsguru.pages.flightsReseveration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.LoggerFactory;

import com.vinsguru.pages.AbstractPage;

import ch.qos.logback.classic.Logger;

public class FlightConfirmationPage extends AbstractPage {

	public FlightConfirmationPage(WebDriver driver) {
		super(driver);
	}

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

	@FindBy(xpath = "(//div[normalize-space()='Flight Confirmation #']/following::div)[1]")
	private WebElement flightConfirmation;

	@FindBy(xpath = "(//div[normalize-space()='Total Price']/following::div)[1]")
	private WebElement totalPrice;

	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.totalPrice));
		return this.totalPrice.isDisplayed();
	}

	public String getPrice() {
		String price  = this.totalPrice.getText(); 
		String confirmation  = this.flightConfirmation.getText(); 
		log.info("Total price " + totalPrice.getText());
		return price;
	}

	public boolean getFlightConfirmation() {
		return this.flightConfirmation.isDisplayed();
	}

}
