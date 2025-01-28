package com.qa.gorest.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.utilities.JsonPathValidator;

import io.restassured.response.Response;

public class CircuitTest extends BaseTest {
	
	
	@BeforeMethod
	public void setup() {
		restClient=new RestClient(prop,baseURI);		
	}
	
	@Test
	public void getCiruitTest() {
		System.out.println("*********Ciruit test********");
Response response=restClient.get("/api/f1/2017/circuits.json", true,false);
	
	response.then().log().all().statusCode(200);	 
	JsonPathValidator jpath=new JsonPathValidator();
	List<String> countryList=jpath.getList(response,"$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");
	System.out.println(countryList);
	Assert.assertTrue(countryList.contains("China"));
	
	}
	@Test
	public void getAllCountryCiruitTest() {
		System.out.println("*********Ciruit test********");
Response response=restClient.get("/api/f1/2017/circuits.json", true,false);
	
	response.then().log().all().statusCode(200);	 
	JsonPathValidator jpath=new JsonPathValidator();
	List<String> countryList=jpath.getList(response,"$.MRData.CircuitTable.Circuits[*].Location.country");
	System.out.println(countryList);
	Assert.assertTrue(countryList.contains("China"));
	
	}
}


