package com.mgrobelak.blogrest.services;

import java.time.LocalDate;

import javax.ejb.Startup;
import javax.inject.Inject;

import com.mgrobelak.blogrest.ejb.PostManager;
import com.mgrobelak.blogrest.ejb.UserManager;
import com.mgrobelak.blogrest.entities.Post;
import com.mgrobelak.blogrest.entities.User;

@Startup
public class SampleDataService {

	@Inject
	private PostManager postManager;

	@Inject
	private UserManager userManager;

	public void getPosts() {

		User marcin = new User(1L, "Marcin", "Grobelak", LocalDate.parse("2000-07-23"));
		userManager.create(marcin);
		User marcela = new User(2L, "Marcela", "Grobelak", LocalDate.parse("2010-10-02"));
		userManager.create(marcela);

		postManager.create(new Post(1L, "Hello world!", marcin));
		postManager.create(new Post(2L, "It is a nice day for a REST.", marcela));
		postManager.create(new Post(3L, "It's with JPA now.", marcin));
		postManager.create(new Post(4L, "It is a mock of db manager.", marcela));
	}
}
