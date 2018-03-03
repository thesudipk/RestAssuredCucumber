package com.kundu.testauto.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber","json:target/JSON/CucumberReport.json" }, 
features = "classpath:features/ticketBookingService.feature",
glue = {"com.kundu.testauto.stepdefinitions"},
monochrome=true)
public class Step {
}
