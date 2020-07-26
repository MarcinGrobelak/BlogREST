package com.mgrobelak.blogrest.entities;

/**
 * @author Marcin Grobelak
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mgrobelak.blogrest.utils.LocalDateTimeAdapter;

@NamedQueries({ @NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u"),
		@NamedQuery(name = "getFilteredUsers", query = "SELECT u FROM User u WHERE (:startId IS NULL OR u.id >= :startId) ORDER BY u.id") })

@XmlRootElement
@Entity
public class User extends BasicEntity {

	private static final long serialVersionUID = 795149849279154668L;

	private String name;
	private String surname;
	private LocalDateTime birthDate;

	private List<Post> posts = new ArrayList<>();
	private List<PostComment> postComments = new ArrayList<>();

	public User() {

	}

	public User(String name, String surname, LocalDateTime birthDate) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "BIRTH_DATE")
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	@XmlTransient
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addPost(Post post) {
		if (post != null) {
			posts.add(post);
			post.setAuthor(this);
		}
	}

	public void removePost(Post post) {
		if (post != null) {
			posts.remove(post);
			post.setAuthor(null);
		}
	}

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	@XmlTransient
	public List<PostComment> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<PostComment> postComments) {
		this.postComments = postComments;
	}

	public void addPostComment(Post post, PostComment postComment) {
		if (postComment != null) {
			postComments.add(postComment);
			postComment.setAuthor(this);
			postComment.getPost().getPostComments().add(postComment);
			postComment.setPost(post);
		}
	}

	public void removePost(PostComment postComment) {
		if (postComment != null) {
			postComments.remove(postComment);
			postComment.setAuthor(null);
			postComment.getPost().getPostComments().remove(postComment);
			postComment.setPost(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
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

		User other = (User) obj;

		if (name == null) {
			if (other.getName() != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		if (surname == null) {
			if (other.getSurname() != null)
				return false;
		} else if (!surname.equals(other.getSurname()))
			return false;
		if (birthDate == null) {
			if (other.getBirthDate() != null)
				return false;
		} else if (!birthDate.equals(other.getBirthDate()))
			return false;
		return true;
	}
}
