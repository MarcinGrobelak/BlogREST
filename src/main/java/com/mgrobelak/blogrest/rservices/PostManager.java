package com.mgrobelak.blogrest.rservices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mgrobelak.blogrest.entities.Post;
import com.mgrobelak.blogrest.entities.User;

public class PostManager {

	public List<Post> getPosts() {
		List<Post> posts = new ArrayList<>();
		User marcin = new User(1L, "Marcin", "Grobelak", LocalDate.parse("2000-07-23"));
		User marcela = new User(2L, "Marcela", "Grobelak", LocalDate.parse("2010-10-02"));

		posts.add(new Post(1L, "Hello world!", marcin));
		posts.add(new Post(2L, "It is a nice day for a REST.", marcela));
		posts.add(new Post(3L, "It will be JPA some day.", marcin));
		posts.add(new Post(4L, "It is a mock of db manager.", marcela));

		return posts;
	}
}
