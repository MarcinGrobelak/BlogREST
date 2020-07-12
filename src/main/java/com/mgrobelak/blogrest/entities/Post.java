package com.mgrobelak.blogrest.entities;

/**
 * @author Marcin Grobelak
 */

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = "getPosts", query = "SELECT p FROM Post p") })

@XmlRootElement
@Entity
public class Post implements Serializable {

	private static final long serialVersionUID = -7012906026786780373L;

	@Id
	@GeneratedValue
	private Long id;

	private String content;

	@Column(name = "CREATION_DATE")
	private LocalDate creationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID")
	private User author;

	public Post() {

	}

	public Post(Long id, String content, User author) {
		this.id = id;
		this.content = content;
		this.creationDate = LocalDate.now();
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
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

		Post other = (Post) obj;

		if (author == null) {
			if (other.getAuthor() != null)
				return false;
		} else if (!author.equals(other.getAuthor()))
			return false;
		if (content == null) {
			if (other.getContent() != null)
				return false;
		} else if (!content.equals(other.getContent()))
			return false;
		if (creationDate == null) {
			if (other.getCreationDate() != null)
				return false;
		} else if (!creationDate.equals(other.getCreationDate()))
			return false;
		return true;
	}
}
