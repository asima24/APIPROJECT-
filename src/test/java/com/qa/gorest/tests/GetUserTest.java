package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

import io.restassured.builder.RequestSpecBuilder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetUserTest extends BaseTest {
	

	@BeforeMethod
	public void setup() {
		restClient=new RestClient(prop,baseURI);		
	}
	@Test
	public void getUsersTest() {
		System.out.println("*********Get All Users********");
		restClient.get("/public/v2/users/", true,true)
		.then().log().all()
		.assertThat().statusCode(200);	          
	}

	@Test
	public void getSpecificUsersTest() {
		System.out.println("*********Get Specific User********");
		restClient =new RestClient(prop,baseURI);
		restClient.get("/public/v2/users/7639949", true,true)
		.then().log().all()
		.assertThat().statusCode(200);	          
	}


	@Test
	public void getUsersWithQueryParameterTest() {
		System.out.println("*********Get Users with Active status(query)********");
		Map<String,Object> queryParam=new HashMap<String,Object>();
		//queryParam.put("name", "bhushan");
		queryParam.put("status", "active");

		restClient.get("/public/v2/users?status='active'",  null,queryParam, true,true)
		.then().log().all()
		.assertThat().statusCode(200);



	}
}
