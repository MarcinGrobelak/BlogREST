package com.mgrobelak.blogrest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mgrobelak.blogrest.ejb.PostCommentManager;
import com.mgrobelak.blogrest.entities.PostComment;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostCommentResource {

	@Inject
	private PostCommentManager postCommentManager;

	@GET
	public List<PostComment> getPostComments() {
		return postCommentManager.getAll();
	}

}
