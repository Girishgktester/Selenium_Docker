package com.listners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.utils.Constants;

public class TestListners implements ITestListener{

	public void onTestFaliure(ITestResult result) {

		TakesScreenshot driver = (TakesScreenshot) result.getTestContext().getAttribute(Constants.DRIVER);
		String screenshot = driver.getScreenshotAs(OutputType.BASE64);
		String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
		String htmlImage = String.format(htmlImageFormat, screenshot);
		Reporter.log(htmlImage);

	}
}