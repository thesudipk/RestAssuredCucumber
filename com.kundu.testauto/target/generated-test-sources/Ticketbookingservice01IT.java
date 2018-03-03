import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
    features = {"classpath:features/ticketBookingService.feature"},
    format = {"json:C:/Workspace/RestAssured/com.kundu.testauto/target/JSON/1.json", "pretty"},
    monochrome = true,
    tags = {"@test"},
    glue = { "com.kundu.testauto.stepdefinitions" })
public class Ticketbookingservice01IT {
}