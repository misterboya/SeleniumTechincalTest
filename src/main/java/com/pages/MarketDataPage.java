package com.pages;

import com.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MarketDataPage {

	private WebDriver driver;

	//By locators
	private By financeLink = By.linkText("Finance");
	private By financeMenuList = By.xpath("//ul[@class='H(navHeight) Ov(h) Pstart(10px) Mstart(-10px) nr-applet-main-nav-right_Ov(inh)! H(itemHeight_uhMagDesign)!']/li");
	private By marketSubCategory = By.xpath("//li[@class='nr-applet-main-nav-item Pend(navPaddings) Whs(nw) Fl(start) H(itemHeight) H(itemHeight_uhMagDesign)! Pend(30px)! opened-subnav']/div[2]/ul/li");
	private By eventsList = By.xpath("//*[@class='W(100%) Pos(r) Mb(20px)']/ul/li");

	//Constructor of the page class
	public MarketDataPage (WebDriver driver) {
		this.driver = driver;
	}

	//Page actions: features(behaviour) of the page
	public void clickFinanceLink () {

		driver.findElement(financeLink).click();
	}


	public void clickCalendarFromMarketCategory () {

		List<WebElement> financeMenuItems = driver.findElements(financeMenuList);

		for(WebElement menuItem : financeMenuItems) {
			if (menuItem.getText().equals("Market Data")) {
				ElementUtil wait = new ElementUtil();
				wait.waitForLoad(driver);
				Actions action = new Actions(driver);
				action.moveToElement(menuItem).perform();
				//action.moveByOffset(983,87).perform();

				List<WebElement> marketMenuList = driver.findElements(marketSubCategory);

				for(WebElement marketCategory : marketMenuList) {
					if (marketCategory.isDisplayed()) {
						String category = marketCategory.getText();
						if (category.equalsIgnoreCase("Calendar")) {
							marketCategory.click();
							break;
						}
					}
				}
				break;
			}
		}
	}

	public String getCurrentDayMarketData (String currentDay) {

		List<WebElement> allEventsList = driver.findElements(eventsList);

		for(WebElement webElement : allEventsList) {
			if (webElement.getText().contains(currentDay))
				return webElement.getText();

		}

		return currentDay;
	}
}











