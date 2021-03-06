package com.mgrobelak.blogrest.entities;

import java.lang.reflect.Method;

/**
 * @author Marcin Grobelak
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mgrobelak.blogrest.resources.PostCommentResource;
import com.mgrobelak.blogrest.resources.PostResource;
import com.mgrobelak.blogrest.utils.LocalDateTimeAdapter;

@NamedQueries({ @NamedQuery(name = "getAllPosts", query = "SELECT p FROM Post p"),
		@NamedQuery(name = "getFilteredPosts", query = "FROM Post p WHERE (:startId IS NULL OR p.id >= :startId) AND (:month IS NULL OR MONTH(p.creationDate) = :month) AND (:year IS NULL OR YEAR(p.creationDate) = :year) ORDER BY p.id") })

@XmlRootElement
@Entity
public class Post extends BasicEntity implements Autorizable, Commentable {

	private static final long serialVersionUID = -7012906026786780373L;

	private String title;
	private String content;
	private LocalDateTime creationDate;
	private User author;

	private List<PostComment> postComments = new ArrayList<>();

	public Post() {

	}

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
		this.creationDate = LocalDateTime.now();
	}

	public Post(String title, String content, LocalDateTime createionDate) {
		this.title = title;
		this.content = content;
		this.creationDate = createionDate;
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

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	@XmlTransient
	public List<PostComment> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<PostComment> postComments) {
		this.postComments = postComments;
	}

	public void addPostComment(PostComment postComment, User author) {
		if (postComment != null) {
			postComments.add(postComment);
			postComment.setPost(this);
			postComment.getAuthor().getPostComments().add(postComment);
			postComment.setAuthor(author);

		}
	}

	public void removePost(PostComment postComment) {
		if (postComment != null) {
			postComments.remove(postComment);
			postComment.setPost(null);
			postComment.getAuthor().getPostComments().remove(postComment);
			postComment.setAuthor(null);
		}
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

	@Override
	@Transient
	@XmlTransient
	public Class<?> getResourceClass() {
		return PostResource.class;
	}

	@Override
	@Transient
	@XmlTransient
	public Class<?> getCommentResourceClass() {
		return PostCommentResource.class;
	}

	@Override
	@Transient
	@XmlTransient
	public Method getCommentSubresourceMethod() {
		Method[] methods = PostResource.class.getMethods();
		for (Method method : methods) {
			if (method.getReturnType().equals(PostCommentResource.class)) {
				return method;
			}
		}
		return null;
	}
}
