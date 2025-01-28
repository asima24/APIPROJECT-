package com.qa.gorest.tests;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utilities.StringUtils;
public class CreateUserTest extends BaseTest {

	 RestClient restClient;
	 User user;
	 static Integer newID;
	 
	 @BeforeMethod
		public void setup() {
			restClient=new RestClient(prop,baseURI);		
		}
	 
	 @DataProvider
	 public Object[][] getUserData() {
		 
		 return new Object[][] {
			 {"luka","male","active"},
			 {"ranu","female","inactive"},
			 {"lovely","female","active"},
			 {"lovekumar","male","active"},
			 
			 
		 };
	 }
	 
	 @Test(dataProvider="getUserData")
	 public void createUserTest(String name,String gender,String status) {
		 System.out.println("*********Create User********");
		 user=new User(name,StringUtils.getRandomEmailid(),gender,status);
		  newID=restClient.post("/public/v2/users",user,"json",true,true)
		           .then().log().all()
		           .assertThat()
		           .statusCode(201)
		           .and()
		           .extract().jsonPath().get("id");
		 
		 System.out.println(newID);
		 
		 System.out.println("***Retreive ****"+ newID+"***********");
		 
		 
		 RestClient restClient=new RestClient(prop,baseURI);
		 restClient.get("/public/v2/users/"+newID, true,true)
		           .then().log().all()
		           .assertThat()
		           .statusCode(200)
		           .and()
		           .assertThat().body("id", equalTo(newID));
		           
	 }
	 
	 @Test
	 public void createUsergetTest() {
 System.out.println("****** Get new ID************");
		 
		 restClient.get("/public/v2/users/"+newID, true,true)
		           .then().log().all()
		           .assertThat()
		           .statusCode(200)
		           .and()
		           .assertThat().body("id", equalTo(newID));
	 }
	 
}
