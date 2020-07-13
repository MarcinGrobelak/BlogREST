package com.mgrobelak.blogrest.resources;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Marcin Grobelak
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mgrobelak.blogrest.ejb.PostManager;
import com.mgrobelak.blogrest.entities.Post;

@Path("/posts")
public class PostResource {

	@Inject
	private PostManager postManager;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Post> getPosts() {
		return postManager.getAll();
	}

	@GET
	@Path("/{postId}")
	@Produces(MediaType.APPLICATION_XML)
	public Post getPost(@PathParam("postId") Long id) {
		return postManager.findById(id);
	}

}
