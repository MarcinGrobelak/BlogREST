package com.mgrobelak.blogrest.ejb;

import java.io.Serializable;
import java.util.List;

public interface CRUD<T> extends Serializable {

	void create(T instance);

	T update(T instance);

	void delete(T instance);

	List<T> getAll();

	T findById(Long id);
}
