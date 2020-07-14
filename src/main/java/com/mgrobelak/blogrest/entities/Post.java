package com.mgrobelak.blogrest.entities;

/**
 * @author Marcin Grobelak
 */

import java.io.Serializable;
import java.time.LocalDateTime;

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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mgrobelak.blogrest.utils.LocalDateTimeAdapter;

@NamedQueries({ @NamedQuery(name = "getPosts", query = "SELECT p FROM Post p") })

@XmlRootElement
@Entity
public class Post implements Serializable {

	private static final long serialVersionUID = -7012906026786780373L;

	private Long id;
	private String title;
	private String content;
	private LocalDateTime creationDate;
	private User author;

	public Post() {

	}

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
		this.creationDate = LocalDateTime.now();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CREATION_DATE")
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID")
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
