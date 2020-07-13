package com.mgrobelak.blogrest.ejb;

import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.omnifaces.cdi.Startup;

import com.mgrobelak.blogrest.entities.Post;
import com.mgrobelak.blogrest.entities.User;

@Startup
public class SampleDataFactory implements Serializable {

	private static final long serialVersionUID = -8482592592856092112L;

	@Inject
	private PostManager postManager;

	@Inject
	private UserManager userManager;

	@PostConstruct
	public void createSampleDBEntries() {

		User marcin = new User("Marcin", "Grobelak", LocalDate.parse("2000-07-23"));
		marcin.addPost(new Post("Hello world!", "Hello REST world!"));
		marcin.addPost(new Post("It's with JPA now.", "Posts from H2 DB."));
		userManager.create(marcin);

		User marcela = new User("Marcela", "Grobelak", LocalDate.parse("2010-10-02"));
		userManager.create(marcela);
		Post firstPost = new Post("REST demo", "It is a nice day for a REST.");
		marcela.addPost(firstPost);
		postManager.create(firstPost);
		marcela.addPost(new Post("Thread safe app", "Stateless EJB to asure transactions"));
		marcela = userManager.update(marcela);
	}
}
