package com.qa.gorest.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.codehaus.groovy.control.customizers.builder.InlinedASTCustomizerFactory;

import com.qa.framworkException.CustomException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	//private static final String BASE_URI="https://gorest.co.in";
	private Properties prop;
	private String baseURI;
//	private static final String TOKEN="e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6";
	private static RequestSpecBuilder specbuilder;
	private boolean isAuthorizationHeaderAdded =false;

//	static {
//		specbuilder= new RequestSpecBuilder();
//	}
	
  public RestClient(Properties prop,String baseURI) {
		specbuilder= new RequestSpecBuilder();
		this.prop=prop;
		this.baseURI=baseURI;
  }
	public void authorizationHeader() {
		if(!isAuthorizationHeaderAdded ) {
		specbuilder.addHeader("Authorization","Bearer "+prop.getProperty("token"));
		isAuthorizationHeaderAdded=true;
		}
	}

	public void addContentType(String contentType) {
        System.out.println(contentType);
		switch (contentType.toLowerCase()) {
		case "json": specbuilder.setContentType(ContentType.JSON);
		break;
		case "html": specbuilder.setContentType(ContentType.HTML);
		break;
		case "xml": specbuilder.setContentType(ContentType.XML);
		break;
		case "text": specbuilder.setContentType(ContentType.TEXT);
		break;
		case "multipart": specbuilder.setContentType(ContentType.MULTIPART);
		break;
		
		case "urlencode": specbuilder.setContentType(ContentType.URLENC);
		break;

		default :
			System.out.println("Please pass correct content type");
			throw new CustomException("InvalidContentType");
		}
	}
	private RequestSpecification createRequestSpec(boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
	
		authorizationHeader() ;}
			return specbuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String,String> mapsHeaders, boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
			
			authorizationHeader() ;}
		if( mapsHeaders!=null) {
			specbuilder.addHeaders(mapsHeaders).build();
		}

		return specbuilder.build();
	}


private RequestSpecification createRequestSpec(Map<String,String> mapsHeaders,Map<String,Object> queryParams, boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
			
			authorizationHeader() ;}
		if( mapsHeaders!=null) {
			specbuilder.addHeaders(mapsHeaders);
		}
		if(queryParams!=null) {
			specbuilder.addQueryParams(queryParams);
		}

		return specbuilder.build();
	}



private RequestSpecification createRequestSpec(Object pojo,String contentType,boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
			
			authorizationHeader() ;}
		if(contentType!=null) {
			addContentType(contentType);
		}
		if( pojo!=null) {
			specbuilder.setBody(pojo);
		}

		return specbuilder.build();
	}

private RequestSpecification createRequestSpec(Object pojo,String contentType, Map<String,String> mapHeaders, boolean includeAuth) {
		specbuilder.setBaseUri(baseURI);
		if(includeAuth) {
			
			authorizationHeader() ;}
		if(contentType!=null) {
			addContentType(contentType);
		}
		if( mapHeaders!=null) {
			specbuilder.addHeaders(mapHeaders);
		}
		if( pojo!=null) {
			specbuilder.setBody(pojo);
		}

		return specbuilder.build();
	}
private RequestSpecification createRequestSpec(Object pojo,String contentType, Map<String,String> mapHeaders,Map<String,String> formParams, boolean includeAuth) {
	specbuilder.setBaseUri(baseURI);
	if(includeAuth) {
		
		authorizationHeader() ;}
	if(contentType!=null) {
		addContentType(contentType);
	}
	if( mapHeaders!=null) {
		specbuilder.addHeaders(mapHeaders);
	}
	if( pojo!=null) {
		specbuilder.setBody(pojo);
	}
	if( formParams!=null) {
		specbuilder.addFormParams(formParams);
	}

	return specbuilder.build();
}
	
	
	public Response get(String serviceURL,boolean log, boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(includeAuth)).log().all()
		            .when().log().all()
		            .get(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(includeAuth))
		            .when()
		            .get(serviceURL);
		           	
	}
	public Response get(String serviceURL,Map<String,String> mapHeaders,boolean log,boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(mapHeaders,includeAuth)).log().all()
		            .when().log().all()
		            .get(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(mapHeaders,includeAuth))
		            .when()
		            .get(serviceURL);
		           	
	}
	
	public Response get(String serviceURL,Map<String,String> mapHeaders,Map<String,Object> queryParams,boolean log, boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(mapHeaders, queryParams,includeAuth)).log().all()
		            .when().log().all()
		            .get(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(mapHeaders, queryParams,includeAuth))
		            .when()
		            .get(serviceURL);
		           	
	}
	
	
	
	
	public Response post(String serviceURL,Object requestBody,String contentType,boolean log, boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
		            .when().log().all()
		            .post(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		            .when()
		            .post(serviceURL);
		           	
	}
	public Response post(String serviceURL,Object requestBody,String contentType,Map<String,String> headersMap, boolean log, boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
		            .when().log().all()
		            .post(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		            .when()
		            .post(serviceURL);
		           	
	}
	public Response post(String serviceURL,Object requestBody,String contentType,Map<String,String> headersMap,Map<String,String> formParams, boolean log, boolean includeAuth)
	{
		System.out.println(serviceURL +":"+ contentType +":" +formParams.toString());
		 if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,formParams,includeAuth)).log().all()
		            .when().log().all()
		            .post(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,formParams,includeAuth))
		            .when()
		            .post(serviceURL);
		           	
	}
	public Response put(String serviceURL,Object requestBody,String contentType,boolean log,boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
		            .when().log().all()
		            .put(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		            .when()
		            .put(serviceURL);
		           	
	}
	public Response put(String serviceURL,Object requestBody,String contentType,Map<String,String> headersMap, boolean log,boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
		            .when().log().all()
		            .put(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		            .when()
		            .put(serviceURL);
		           	
	}
	public Response patch(String serviceURL,Object requestBody,String contentType,boolean log,boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
		            .when().log().all()
		            .patch(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		            .when()
		            .patch(serviceURL);
		           	
	}
	public Response patch(String serviceURL,Object requestBody,String contentType,Map<String,String> headersMap, boolean log,boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
		            .when().log().all()
		            .patch(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		            .when()
		            .patch(serviceURL);
		           	
	}
	
	public Response delete(String serviceURL,boolean log, boolean includeAuth)
	{
		 if(log) {
		return RestAssured.given(createRequestSpec(includeAuth)).log().all()
		            .when().log().all()
		            .delete(serviceURL);
		            }
		 return RestAssured.given(createRequestSpec(includeAuth))
		            .when()
		            .delete(serviceURL);
		           	
	}
	
	public String getAccessToken(String serviceURL, String grantType, String clientId, String clientSecret  ) {
		//1. POST - get the access token
				RestAssured.baseURI = "https://test.api.amadeus.com";
				
				String accessToken = RestAssured. given().log().all()
					.contentType(ContentType.URLENC)
					.formParam("grant_type", grantType)
					.formParam("client_id", clientId)
					.formParam("client_secret", clientSecret)
				.when()
					.post(serviceURL)
				.then().log().all()
					.assertThat()
						.statusCode(200)
						.extract().path("access_token");
					
				System.out.println("access token: " + accessToken);
			return 	accessToken;
				
	}
	
	public String getnewAccessToken(String serviceURL, String grantType, String clientId, String clientSecret  ) {
		//1. POST - get the access token
				RestAssured.baseURI = "https://test.api.amadeus.com";
				Map<String,String> formParams=new HashMap<String,String>();
				formParams.put("grant_type", grantType);
				formParams.put("client_id", clientId);
				formParams.put("client_secret", clientSecret);
			
				String accessToken= post("/v1/security/oauth2/token", null,"URLEncode", null, formParams, true, false)
		                  .then().log().all()
						.assertThat()
							.statusCode(200)
							.extract().path("access_token");
					
				System.out.println("access token: " + accessToken);
			return 	accessToken;
				
	}
}
