package com.mgrobelak.blogrest.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.mgrobelak.blogrest.entities.Post;

@Stateless
@LocalBean
public class PostManager implements CRUD<Post> {

	private static final long serialVersionUID = 3698321353274450896L;

	@Inject
	private EntityManager entityManager;

	@Override
	public void create(Post post) {
		if (post != null) {
			entityManager.persist(post);
		}
	}

	@Override
	public Post update(Post post) {
		if (post != null) {
			return entityManager.merge(post);
		}
		return post;
	}

	@Override
	public void delete(Post post) {
		if (post != null) {
			entityManager.remove(post);
		}
	}

	@Override
	public List<Post> getAll() {
		return entityManager.createNamedQuery("getPosts", Post.class).getResultList();
	}

	@Override
	public Post findById(Long id) {
		if (id != null) {
			entityManager.find(Post.class, id);
		}
		return null;
	}
}
