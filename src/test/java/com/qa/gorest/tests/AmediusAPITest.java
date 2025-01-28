package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

import io.restassured.response.Response;

public class AmediusAPITest extends BaseTest {
private String accessToken;
	
	@Parameters({"baseURI", "grantType", "clientId", "clientSecret"})
	@BeforeMethod
	public void flightAPiSetup(String baseURI, String grantType, String clientId, String clientSecret) {
		restClient = new RestClient(prop, baseURI);
	    accessToken= restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT, grantType, clientId, clientSecret);             
	      System.out.println(accessToken);
	}
	
	
	@Test
	public void getFlightInfoTest() {
		
		RestClient restClientFlight = new RestClient(prop, baseURI);
		
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("origin", "PAR");
		queryParams.put("maxPrice", 200);

		Map<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Authorization", "Bearer "+ accessToken);
		
		Response flightDataResponse = restClientFlight.get(AMADEUS_FLIGHTBOKKING_ENDPOINT, headersMap, queryParams, true, false)
				 .then().log().all()
					.assertThat()
						.statusCode(200)
							.and()
								.extract()
									.response();
		
	io.restassured.path.json.JsonPath js = flightDataResponse.jsonPath();
	String type = js.get("data[0].type");
	System.out.println(type);//flight-destination
		
	}
	
	
}
