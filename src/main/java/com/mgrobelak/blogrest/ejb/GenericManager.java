package com.mgrobelak.blogrest.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
@LocalBean
public class GenericManager<T> implements Serializable {

	private static final long serialVersionUID = 384003256642194407L;

	@Inject
	private EntityManager entityManager;

	public T create(T t) {
		if (t != null) {
			entityManager.persist(t);
		}
		return t;
	}

	public T update(T t) {
		if (t != null) {
			return entityManager.merge(t);
		}
		return t;
	}

	public void delete(T t) {
		if (t != null) {
			entityManager.remove(t);
		}
	}

	public List<T> performQuery(Class<T> type, String namedQuery) {
		return entityManager.createNamedQuery(namedQuery, type).getResultList();
	}

	public T findById(Class<T> type, Long id) {
		if (id != null) {
			return entityManager.find(type, id);
		}
		return null;
	}
}
