package com.mgrobelak.blogrest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

/**
 * @author Marcin Grobelak
 */

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mgrobelak.blogrest.ejb.PostManager;
import com.mgrobelak.blogrest.entities.Post;

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

	@Inject
	private PostManager postManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getPosts() {
		return postManager.getAll();
	}

	@GET
	@Path("/{postId}")
	public Post getPost(@PathParam("postId") Long id) {
		return postManager.findById(id);
	}

	@POST
	public Post createPost(Post post) {
		return postManager.create(post);
	}

	@PUT
	@Path("/{postId}")
	public Post updatePost(@PathParam("postId") Long id, Post post) {
		post.setId(id);
		return postManager.update(post);

	}

	@DELETE
	@Path("/{postId}")
	public void deletePost(@PathParam("postId") Long id) {
		Post post = postManager.findById(id);
		postManager.delete(post);
	}

}
