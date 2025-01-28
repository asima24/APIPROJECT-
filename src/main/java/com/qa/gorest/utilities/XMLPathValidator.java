package com.qa.gorest.utilities;

import java.util.List;
import java.util.Map;

import com.qa.framworkException.CustomException;
import com.qa.gorest.constants.APIConstants;

import io.restassured.common.exception.PathException;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.exception.XmlPathException;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathValidator {
	 XmlPath xmlPath ;
	 public <T> T readXML(Response response, String xmlPathExpression) {
	    xmlPath =new XmlPath(response.getBody().asString());
	    try {
	        return xmlPath.get(xmlPathExpression);
	    }
	      catch (PathException e) {
	    	  e.printStackTrace();
	           throw new CustomException( xmlPathExpression+ APIConstants.XMLPATH_NOT_FOUND);
	    }
	    }
	 
	 public <T> List<T> readXMLList(Response response, String xmlPathExpression) {
		    xmlPath =new XmlPath(response.getBody().asString());
		    try {
		        return xmlPath.getList(xmlPathExpression);
		    }
		      catch (PathException e) {
		    	  e.printStackTrace();
		           throw new CustomException( xmlPathExpression+APIConstants.XMLPATH_NOT_FOUND);
		    }
		    }
	 
	 public <T> List<Map<T,T>> readXMLListOfMaps(Response response, String xmlPathExpression) {
		
		    try {
		        return readXMLList(response,  xmlPathExpression);
		    }
		      catch (PathException e) {
		    	  e.printStackTrace();
		           throw new CustomException( xmlPathExpression+APIConstants.XMLPATH_NOT_FOUND);
		    }
}}
