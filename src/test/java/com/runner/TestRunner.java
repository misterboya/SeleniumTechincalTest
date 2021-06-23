package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features"} ,
		glue = {"com.stepdefinitions", "com.apphooks"},
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				})

public class TestRunner extends AbstractTestNGCucumberTests {

}


