package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class APISchemaValidationTest extends BaseTest {

	@BeforeMethod
	public void setup() {
		restClient=new RestClient(prop,baseURI);		
	}
	
	@Test
	public void getUserAPIschemaValidationTest() {
		restClient.get("/public/v2/users/", true,true)
		.then().log().all()
		.assertThat().statusCode(200)
		.body(matchesJsonSchemaInClasspath("GetUserSchema.json"));
		
	}
}
