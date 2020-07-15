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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mgrobelak.blogrest.utils.LocalDateTimeAdapter;

@NamedQueries({ @NamedQuery(name = "getPostComments", query = "SELECT c FROM PostComment c") })

@XmlRootElement
@Entity
@Table(name = "POST_COMMENT")
public class PostComment implements Serializable {

	private static final long serialVersionUID = 3981749360956772186L;

	private Long id;
	private Post post;
	private String content;
	private LocalDateTime creationDate;
	private User author;

	public PostComment() {

	}

	public PostComment(Post post, String content) {
		this.post = post;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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

}