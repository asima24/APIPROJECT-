package com.qa.gorest.utilities;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.framworkException.CustomException;
import com.qa.gorest.constants.APIConstants;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	public <T> T getItem(Response response,String jsonpath) {
		
		
		 try {
		return  JsonPath.read(response.getBody().asString(), jsonpath);
		 }
		 catch(PathNotFoundException e) {
			 e.printStackTrace();
			 throw new CustomException(jsonpath+APIConstants.JSONPATH_NOT_FOUND);
		 }
	}
	
	public <T> List<T> getList(Response response,String jsonpath) {
		
		
		 try {
		return  JsonPath.read(response.getBody().asString(), jsonpath);
		 }
		 catch(PathNotFoundException e) {
			 e.printStackTrace();
			 throw new CustomException(jsonpath+APIConstants.JSONPATH_NOT_FOUND);
		 }
	}
	
	
	public <T> List<Map<T,T>> getListofMaps(Response response,String jsonpath) {
		
		
		 try {
		return  JsonPath.read(response.getBody().asString(), jsonpath);
		 }
		 catch(PathNotFoundException e) {
			 e.printStackTrace();
			 throw new CustomException(jsonpath+APIConstants.JSONPATH_NOT_FOUND);
		 }
	}

}
