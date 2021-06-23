package com.pages;

import com.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	//By locators
	private By signInButton = By.linkText("Sign in");
	private By userName = By.id("login-username");
	private By nextButton = By.id("login-signin");
	private By password = By.id("login-passwd");
	private By errorMessage = By.xpath("//form[@id='login-username-form']/p");
	private By acceptCookies = By.name("agree");


	//Constructor of the page class
	 public LoginPage(WebDriver driver) {
	 	this.driver = driver;
	 }

	 //Page actions: features(behaviour) of the page
	public String getSignInPageTitle() {
	 	return driver.getTitle();
	}

	public void clickAcceptCookies() {
		driver.findElement(acceptCookies).click();
	}

	public boolean isUserNameFiledDisplayed() {
	 	return driver.findElement(userName).isDisplayed();
	}

	public boolean isPasswordFiledDisplayed() {
		return driver.findElement(password).isDisplayed();
	}

	public boolean isErrorMessageDisplayed() {

	 	return driver.findElement(errorMessage).isDisplayed();
	}

	public String getSignInErrorMessage() {

		return driver.findElement(errorMessage).getText();
	}

	public void enterUserName(String username) {
	 	driver.findElement(userName).sendKeys(username);
	}

	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}

	public void clickSignIn() {
		driver.findElement(signInButton).click();
	}

	public void clickNext() {
	 	driver.findElement(nextButton).click();
		ElementUtil wait = new ElementUtil();
		wait.waitForLoad(driver);
	}

	public void login(String username, String password) {
	 	clickAcceptCookies();
	 	clickSignIn();
	 	enterUserName(username);
	 	clickNext();
	 	enterPassword(password);
	 	clickNext();
	}

}
