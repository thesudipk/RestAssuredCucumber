package com.kundu.testauto.stepdefinitions;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.github.tomakehurst.wiremock.WireMockServer;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TicketBooking {

	WireMockServer server;
	RequestSpecification reqspec;
	Response response;
	String contentType;
	Scenario tempscenario;
	ContentType ctype = null;

	@Before
	public void init(Scenario scenario) {
		try {
			server = new WireMockServer(8889);
			configureFor("localhost", 8889);
			server.start();

			stubFor(post(urlEqualTo("/ticket/booking/ticketid")).withHeader("Accept", equalTo("application/json"))
					.willReturn(aResponse().withStatus(201)));

			tempscenario = scenario;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	void stopserver() {
		server.stop();
	}

	@When("^user wants to get info by passing content type is\"([^\"]*)\"$")
	public void user_wants_to_get_info_by_passing_content_type_is(String contentType) throws Exception {
		if (contentType.equalsIgnoreCase("json")) {
			ctype = ContentType.JSON;
		} else {
			ctype = ContentType.JSON;
		}

	}

	@When("^base url \"([^\"]*)\"$")
	public void base_url(String baseurl) throws Exception {

		ByteArrayOutputStream requestwriter = new ByteArrayOutputStream();
		PrintStream requestCapture = new PrintStream(requestwriter);

		ByteArrayOutputStream responsewriter = new ByteArrayOutputStream();
		PrintStream responseCapture = new PrintStream(responsewriter);

		String requestjson = "{\"id\":\"123456\"}";
		RestAssured.baseURI = "http://localhost:8889/" + baseurl;
		reqspec = RestAssured.given().accept("application/json").contentType(ctype).body(requestjson)
				.filter(new RequestLoggingFilter(requestCapture));

		response = reqspec.when().filter(new ResponseLoggingFilter(responseCapture)).post("/ticketid");
		tempscenario.write(baseurl);
	}

	@Then("^expected status is \"([^\"]*)\"$")
	public void expected_status_is(String status) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(status, String.valueOf(response.getStatusCode()));
		tempscenario.write(status);

	}

	@When("^content type is\"([^\"]*)\"$")
	public void content_type_is(String contenttype) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("i am here");
		contentType = contenttype;
		tempscenario.write(contenttype);

	}

}
