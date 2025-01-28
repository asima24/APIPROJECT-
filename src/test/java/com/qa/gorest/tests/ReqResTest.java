package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class ReqResTest extends BaseTest{
	
	
	@BeforeMethod
	public void setup() {
		restClient=new RestClient(prop,baseURI);		
	}
	@Test
	public void getReqRestTest() {
		System.out.println("*********Req res Test********");
        restClient.get("/api/users/2", true, false)
		          .then().log().all()
		          .assertThat()
		          .statusCode(200);
	}

}
