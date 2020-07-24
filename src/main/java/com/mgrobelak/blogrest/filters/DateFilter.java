package com.mgrobelak.blogrest.filters;

/**
 * @author Marcin Grobelak
 */

import javax.ws.rs.QueryParam;

public class DateFilter {

	private @QueryParam("year") Integer year;
	private @QueryParam("month") Integer month;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
