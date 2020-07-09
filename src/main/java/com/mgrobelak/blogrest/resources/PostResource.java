package com.mgrobelak.blogrest.resources;

import java.util.List;

/**
 * @author Marcin Grobelak
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mgrobelak.blogrest.entities.Post;
import com.mgrobelak.blogrest.rservices.PostManager;

@Path("/posts")
public class PostResource {

	private PostManager postManager = new PostManager();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Post> getPosts() {
		return postManager.getPosts();
	}

}
