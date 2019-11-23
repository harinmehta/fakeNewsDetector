package com.filternews.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//created by Dangi 26-10-18
public class JsonUtill {
	private static ObjectMapper mapper;
	static {
		mapper=new ObjectMapper();
	}
//	/convertJavaToJson
	public static String convertJavaToJson(Object object) {
		String jsonResult="";
		try {
		jsonResult=mapper.writeValueAsString(object);
		}
		catch(JsonGenerationException e) {
			System.out.println("Exception occured while converting java object into json ");

		} catch (JsonMappingException e) {
			System.out.println("Exception occured while converting java object into json ");

		}
		 catch (IOException e) {
				System.out.println("Exception occured while converting java object into json ");

			}
		return jsonResult;
	}

	//convertJsonToJava
	public static <T> T convertJsonToJava(String jsonString, Class<T> cls) {
		T result=null;
		
		try {
			result=mapper.readValue(jsonString, cls);
		}
		 catch (JsonParseException e) {
				System.out.println("Exception occured while converting json  into java object ");
			}
		 catch (JsonMappingException e) {
				System.out.println("Exception occured while converting json into java object ");
			}
		catch (IOException e) {
			System.out.println("Exception occured while converting json  into java object ");
		}
		return result;
	
	}
	
	

	}


