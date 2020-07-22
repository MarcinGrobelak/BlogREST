package com.mgrobelak.blogrest.filters;

/**
 * @author Marcin Grobelak
 */

import javax.ws.rs.QueryParam;

public class PaginationFilter {

	private @QueryParam(value = "startId") int startId;
	private @QueryParam(value = "size") int size;

	public int getStartId() {
		return startId;
	}

	public void setStartId(int startId) {
		this.startId = startId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
