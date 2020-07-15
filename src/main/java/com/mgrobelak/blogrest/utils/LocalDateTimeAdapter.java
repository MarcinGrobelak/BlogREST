package com.mgrobelak.blogrest.utils;

/**
 * @author Marcin Grobelak
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	public static String dataPattern = "yyyy-MM-dd@HH:mm";

	@Override
	public LocalDateTime unmarshal(String dateString) throws Exception {
		return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(dataPattern));
	}

	@Override
	public String marshal(LocalDateTime localDateTime) throws Exception {
		return DateTimeFormatter.ofPattern(dataPattern).format(localDateTime);
	}
}
