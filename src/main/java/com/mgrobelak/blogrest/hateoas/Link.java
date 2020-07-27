package com.mgrobelak.blogrest.hateoas;

public class Link {

	private String rel;
	private String uri;

	public Link(String rel, String uri) {
		this.rel = rel;
		this.uri = uri;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rel == null) ? 0 : rel.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (rel == null) {
			if (other.getRel() != null)
				return false;
		} else if (!rel.equals(other.getRel()))
			return false;
		if (uri == null) {
			if (other.getUri() != null)
				return false;
		} else if (!uri.equals(other.getUri()))
			return false;
		return true;
	}
}
