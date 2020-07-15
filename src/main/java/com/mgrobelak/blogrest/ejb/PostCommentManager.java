package com.mgrobelak.blogrest.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.mgrobelak.blogrest.entities.PostComment;

@Stateless
@LocalBean
public class PostCommentManager implements CRUD<PostComment> {

	private static final long serialVersionUID = -7266556512847436674L;

	@Inject
	private EntityManager entityManager;

	@Override
	public PostComment create(PostComment postComment) {
		if (postComment != null) {
			entityManager.persist(postComment);
		}
		return postComment;
	}

	@Override
	public PostComment update(PostComment postComment) {
		if (postComment != null) {
			return entityManager.merge(postComment);
		}
		return postComment;
	}

	@Override
	public void delete(PostComment postComment) {
		if (postComment != null) {
			entityManager.remove(postComment);
		}
	}

	@Override
	public List<PostComment> getAll() {
		return entityManager.createNamedQuery("getPostComments", PostComment.class).getResultList();
	}

	@Override
	public PostComment findById(Long id) {
		if (id != null) {
			return entityManager.find(PostComment.class, id);
		}
		return null;
	}
}
