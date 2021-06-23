package com.stepdefinitions;

import com.factory.DriverFactory;
import com.pages.LoginPage;
import com.pages.MarketDataPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class MarketDataSteps {

	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private MarketDataPage marketDataPage = new MarketDataPage(DriverFactory.getDriver());

	@Given("user has alrady logged into the application")
	public void user_is_logged_in (DataTable dataTable) {

		DriverFactory.getDriver().get("https://uk.yahoo.com/");
		List<Map<String, String>> credentials = dataTable.asMaps();
		System.out.println(credentials.get(0).get("username"));
		System.out.println(credentials.get(0).get("password"));

		loginPage.login(credentials.get(0).get("username"), credentials.get(0).get("password"));
	}

	@Given("user selects finance from menu category")
	public void user_selects_finance_from_menu_category () {

		marketDataPage.clickFinanceLink();
	}

	@When("user hover on to market data and select calendar")
	public void user_hover_on_to_market_data_and_select_calendar () {

		marketDataPage.clickCalendarFromMarketCategory();

		System.out.println("Hey i am on the calendar page !!!!");

	}

	@Then("user gets the market data of the {string}")
	public void user_gets_the_market_data_of_the_current_day (String currentDayInfo) {

		String actualEventInfo = marketDataPage.getCurrentDayMarketData(currentDayInfo);
		System.out.println("Hey this is today's list of Events: " + actualEventInfo);
		Assert.assertNotNull(actualEventInfo);
	}
}


