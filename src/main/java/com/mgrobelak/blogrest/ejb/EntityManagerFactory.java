package com.mgrobelak.blogrest.ejb;

/**
 * @author Marcin Grobelak
 */

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful
@ApplicationScoped
@LocalBean
public class EntityManagerFactory implements Serializable {

	private static final long serialVersionUID = -1139322602770740119L;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
