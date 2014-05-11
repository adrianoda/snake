package com.kof.snake.properties;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.kof.snake.exceptions.SomethingsWrongException;

public class PropertiesLoader {	
	
	private static ResourceBundle properties = null;
	
	private PropertiesLoader(){		
	}

	/**
	 * Load specified property value
	 * @param propKey
	 * @return
	 * @throws SomethingsWrongException
	 */
	public static String getProperties(PropertiesKey propKey) throws SomethingsWrongException {
		if(properties == null) {
			try {
				properties = ResourceBundle.getBundle(PropConst.FILE_BASE_NAME);
			} catch (MissingResourceException ex){
				throw new SomethingsWrongException("PropertiesLoader - Missing property resource <" + PropConst.FILE_RESOURCE_NAME + ">.");
			}
		}
		
		if(!properties.containsKey(propKey.getKeyValue())) {
			throw new SomethingsWrongException("PropertiesLoader - Missing property <" + propKey.getKeyValue() + "> into resource <" + PropConst.FILE_RESOURCE_NAME + ">");
		}
		
		return properties.getString(propKey.getKeyValue());
	}


}
