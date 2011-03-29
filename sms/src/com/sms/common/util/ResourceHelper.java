package com.sms.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


public final class ResourceHelper {
	
	private ResourceHelper(){}
	
	private static final Logger logger = Logger.getLogger(ResourceHelper.class);
	
	private static final String APPLICATION_RESOURCE = "com/sms/resource/ApplicationResource";
	
	public static String getResources(String key){
		String keyValue = "";
		try{
			keyValue = ResourceBundle.getBundle(APPLICATION_RESOURCE).getString(key);
		}catch(MissingResourceException ex)
		{
			logger.debug("the key :"+key+", is not found or properties file not found");
			keyValue="";
		}
		catch(Exception ex)
		{
			logger.debug("Exception :"+ex.getMessage());
			keyValue="";
		}
		
		return keyValue;
	}

}
