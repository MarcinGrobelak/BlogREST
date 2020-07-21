package com.mgrobelak.blogrest.filters;

import javax.ws.rs.QueryParam;

public class PaginationFilter {

	private @QueryParam(value = "start") int start;
	private @QueryParam(value = "size") int size;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
