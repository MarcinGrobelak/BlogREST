package com.mgrobelak.blogrest.ejb;

/**
 * @author Marcin Grobelak
 */

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mgrobelak.blogrest.entities.BasicEntity;

@Stateless
@LocalBean
public class GenericManager<T extends BasicEntity> implements Serializable {

	private static final long serialVersionUID = 384003256642194407L;

	@Inject
	protected EntityManager entityManager;

	public T create(T entity) {
		if (entity != null) {
			entityManager.persist(entity);
		}
		return entity;
	}

	public T update(T entity) {
		if (entity != null) {
			return entityManager.merge(entity);
		}
		return entity;
	}

	public void delete(T entity) {
		if (entity != null) {
			entityManager.remove(entity);
		}
	}

	public List<T> performQuery(Class<T> clazz, String namedQuery) {
		return entityManager.createNamedQuery(namedQuery, clazz).getResultList();
	}

	public List<T> performQuery(Class<T> clazz, String namedQuery, Integer resultLimit) {
		return entityManager.createNamedQuery(namedQuery, clazz).setMaxResults(resultLimit).getResultList();
	}

	public List<T> performQueryParam(Class<T> clazz, String namedQuery, Map<String, Object> parameters,
			Integer resultLimit) {
		Query query = entityManager.createNamedQuery(namedQuery, clazz);
		for (Entry<String, Object> param : parameters.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		if (resultLimit != null) {
			query.setMaxResults(resultLimit);
		}
		return query.getResultList();
	}

	public List<T> performQueryParam(Class<T> clazz, String namedQuery, Map<String, Object> parameters) {
		return performQueryParam(clazz, namedQuery, parameters, null);
	}

	public T findById(Class<T> clazz, Long id) {
		if (id != null) {
			return entityManager.find(clazz, id);
		}
		return null;
	}
}
