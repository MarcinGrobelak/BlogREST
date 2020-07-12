package com.mgrobelak.blogrest.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mgrobelak.blogrest.entities.User;

@Stateless
public class UserManager implements CRUD<User> {

	private static final long serialVersionUID = -2278111837707914050L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(User user) {
		if (user != null) {
			entityManager.persist(user);
		}
	}

	@Override
	public User update(User user) {
		if (user != null) {
			return entityManager.merge(user);
		}
		return user;
	}

	@Override
	public void delete(User user) {
		if (user != null) {
			entityManager.remove(user);
		}
	}

	@Override
	public List<User> getAll() {
		return entityManager.createNamedQuery("getUsers", User.class).getResultList();
	}

	@Override
	public User findById(Long id) {
		if (id != null) {
			return entityManager.find(User.class, id);
		}
		return null;
	}

}
