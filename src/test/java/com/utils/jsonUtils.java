package com.utils;

import java.io.IOException;
import java.io.InputStream;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonUtils {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(jsonUtils.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	public static <T> T getTestData(String path,Class<T> type) throws IOException {
		try (InputStream stream = ResourceLoader.getResoruce(path)) {
			return mapper.readValue(stream, type);
		}catch(Exception e) {
			log.error("failed in getTestData method");
		}
		return null;
	}
}
