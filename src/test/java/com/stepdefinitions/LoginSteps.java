package com.stepdefinitions;

import com.factory.DriverFactory;
import com.pages.LoginPage;
import com.util.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginSteps {

	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private ExcelReader excelReader = new ExcelReader();
	private String excelFilePath = System.getProperty("user.dir")+"/src/test/resources/Credentials.xlsx";

	@Given("user is on login page")
	public void user_is_on_login_page() {

		DriverFactory.getDriver().get("https://uk.yahoo.com/");
	}

	@When("user accepts cookies")
	public void userAcceptsCookies () {

		loginPage.clickAcceptCookies();
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) {

		Assert.assertTrue(loginPage.getSignInPageTitle().contains(expectedTitleName));
	}

	@When("user clicks on sign in button")
	public void user_clicks_on_sign_in_button() {

		loginPage.clickSignIn();
	}

	@Then("user login form is displayed")
	public void user_login_form_is_displayed() {

		Assert.assertTrue(loginPage.isUserNameFiledDisplayed());
	}

	@When("user enters username {string}")
	public void user_enters_username(String username) {

		loginPage.enterUserName(username);
	}

	@And("user clicks on next button")
	public void user_clicks_on_next_button() {

		loginPage.clickNext();
	}

	@Then("password form is displayed")
	public void password_form_is_displayed() {

		Assert.assertTrue(loginPage.isPasswordFiledDisplayed());
	}

	@And("user enters password {string}")
	public void user_enters_password(String password) {

		loginPage.enterPassword(password);
	}

	@When("user enters username from {string} and {int}")
	public void user_enters_the_username_from_and(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException {

		List<Map<String, String>> userName = excelReader.getData(excelFilePath, sheetName);

		String uName = userName.get(rowNumber).get("username");
		System.out.println("Username from excel is: " + uName);

		loginPage.enterUserName(uName);

	}

	@When("user enters password from {string} and {int}")
	public void user_enters_the_password_from_and(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException {

		List<Map<String, String>> password = excelReader.getData(excelFilePath, sheetName);

		String pwd = password.get(rowNumber).get("password");
		System.out.println("Password from excel is: " + pwd);

		loginPage.enterPassword(pwd);

	}

	@When("user enters incorrect username from {string} and {int}")
	public void user_enters_incorrect_username_from_and(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException {

		List<Map<String, String>> userName = excelReader.getData(excelFilePath, sheetName);

		String uName = userName.get(rowNumber).get("username");
		System.out.println("Username from excel is: " + uName);

		loginPage.enterUserName(uName);

	}

	@Then("error message is displayed as {string}")
	public void error_message_is_displayed(String expectedErrorMessage) {

		String actualErrorMessage = loginPage.getSignInPageTitle();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}

}
