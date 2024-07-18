package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import io.opentelemetry.api.logs.Logger;

public class Config {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(Config.class);
	private static final String DEFULAT_PROPERTIES = "config/default.properties";

	private static Properties properties;

	public static void initialize() throws IOException {

		properties = loadProperties();

		for (String key : properties.stringPropertyNames()) {
			if (System.getProperties().contains(key)) {
				properties.setProperty(key, System.getProperty(key));
			}
		}

		log.info("Test prop");
		for (String key : properties.stringPropertyNames()) {
			log.info("----", key, properties.setProperty(key, System.getProperty(key)));

		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	private static Properties loadProperties() throws IOException {
		Properties properties = new Properties();
		try (InputStream stream = ResourceLoader.getResoruce(DEFULAT_PROPERTIES)) {
			properties.load(stream);
		} catch (Exception e) {
			log.error("unable to read property file ", DEFULAT_PROPERTIES, e);
		}
		return properties;
	}
}