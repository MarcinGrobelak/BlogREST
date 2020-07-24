package com.mgrobelak.blogrest.filters;

/**
 * @author Marcin Grobelak
 */

import javax.ws.rs.QueryParam;

public class PaginationFilter {

	private @QueryParam(value = "startId") Long startId;
	private @QueryParam(value = "size") Integer size;

	public Long getStartId() {
		return startId;
	}

	public void setStartId(Long startId) {
		this.startId = startId;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
