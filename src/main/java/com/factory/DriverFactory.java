package com.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the ThreadLocal driver on the basis of given browser
	 * @param browser
	 * @return this will return tlDriver
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("Browser value is: " + browser);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		} else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		return getDriver();
	}

	/**
	 * This is used to get the driver with ThreadLocal
	 * @return tlDriver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
