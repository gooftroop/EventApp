package com.event.app.resource;

import java.util.Properties;

/**
 * Created by goof_troop on 3/8/14.
 */
public final class PropertiesReader {

	private static final String propFile = "";
	private static Properties properties;

	static {

		properties = new Properties();

		// read in properties

	}

	public static String getPropert(String name, String defaultValue) {

		return properties.getProperty(name, defaultValue);
	}

	public static String getProperty(String name) {

		return properties.getProperty(name);
	}

	public static void setProperty(String name, String value) {

		properties.setProperty(name, value);
	}

	public static void save() {

	}

}
