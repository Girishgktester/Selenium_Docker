package com.vinsguru.pages.flightsReseveration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vinsguru.pages.AbstractPage;

public class RegestrationPage extends AbstractPage {

	public RegestrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "firstName")
	private WebElement firstNameInput;

	@FindBy(id = "lastName")
	private WebElement lastNameInput;

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(name = "street")
	private WebElement streetInput;

	@FindBy(name = "city")
	private WebElement cityInput;

	@FindBy(name = "zip")
	private WebElement zipInput;

	@FindBy(id = "register-btn")
	private WebElement registerButton;

	public void getUrl(String url) {
		this.driver.get(url);
	}

	public void enterUserNameAndPassword(String username, String passowrd) {
		this.firstNameInput.sendKeys(username);
		this.lastNameInput.sendKeys(passowrd);
	}

	public void enterCred(String email, String password) {
		this.emailInput.sendKeys(email);
		this.passwordInput.sendKeys(password);
	}

	public void enterAddress(String street, String city, String zip) {
		this.streetInput.sendKeys(street);
		this.cityInput.sendKeys(city);
		this.zipInput.sendKeys(zip);
	}

	public void register() {
		this.registerButton.click();
	}

	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.firstNameInput));
		return this.firstNameInput.isDisplayed();
	}

}
