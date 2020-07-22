package com.mgrobelak.blogrest.filters;

/**
 * @author Marcin Grobelak
 */

import javax.ws.rs.QueryParam;

public class DateFilter {

	private @QueryParam("year") int year;
	private @QueryParam("month") int month;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
