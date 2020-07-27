package com.mgrobelak.blogrest.entities;

/**
 * @author Marcin Grobelak
 */

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mgrobelak.blogrest.resources.PostCommentResource;
import com.mgrobelak.blogrest.utils.LocalDateTimeAdapter;

@NamedQueries({ @NamedQuery(name = "getAllPostComments", query = "SELECT c FROM PostComment c"),
		@NamedQuery(name = "getFilteredPostComments", query = "FROM PostComment c WHERE (:startId IS NULL OR c.id >= :startId) AND (:month IS NULL OR MONTH(c.creationDate) = :month) AND (:year IS NULL OR YEAR(c.creationDate) = :year) ORDER BY c.id") })

@XmlRootElement
@Entity
@Table(name = "POST_COMMENT")
public class PostComment extends BasicEntity implements Autorizable {

	private static final long serialVersionUID = 3981749360956772186L;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID")
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

	@Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHOR_ID")
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	@Transient
	@XmlTransient
	public Class<?> getResourceClass() {
		return PostCommentResource.class;
	}

}
