package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import org.slf4j.LoggerFactory;

import com.vinsguru.pages.flightsReseveration.FlightConfirmationPage;

public class ResourceLoader {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

	public static InputStream getResoruce(String path) throws IOException {

		log.info("Read resouce " + path);
		InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		if (Objects.nonNull(stream)) {
			return stream;
		}
		return Files.newInputStream(Path.of(path));
	}
	
	

}
