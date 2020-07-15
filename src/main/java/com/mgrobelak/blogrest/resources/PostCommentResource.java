package com.mgrobelak.blogrest.resources;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mgrobelak.blogrest.ejb.PostCommentManager;
import com.mgrobelak.blogrest.ejb.PostManager;
import com.mgrobelak.blogrest.ejb.UserManager;
import com.mgrobelak.blogrest.entities.Post;
import com.mgrobelak.blogrest.entities.PostComment;
import com.mgrobelak.blogrest.entities.User;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostCommentResource {

	@Inject
	private PostCommentManager postCommentManager;

	@Inject
	private PostManager postManager;

	@Inject
	private UserManager userManager;

	@GET
	public List<PostComment> getPostComments() {
		return postCommentManager.getAll();
	}

	@GET
	@Path("/{commentId}")
	public PostComment getPost(@PathParam("commentId") Long id) {
		return postCommentManager.findById(id);
	}

	@POST
	public Response createPost(@PathParam("postId") Long postId, PostComment postComment) {
		if (postComment == null) {
			return Response.status(Status.NOT_FOUND).entity("Post comment JSON not found").build();
		}

		Post post = postManager.findById(postId);
		if (post == null) {
			return Response.status(Status.NOT_FOUND).entity("Post not found.").build();
		}
		postComment.setPost(post);

		if (postComment.getAuthor() == null) {
			return Response.status(Status.NOT_FOUND).entity("Author not found.").build();
		}

		User author = userManager.findById(postComment.getAuthor().getId());
		author.addPostComment(post, postComment);
		postComment.setCreationDate(LocalDateTime.now());

		return Response.status(Status.CREATED).entity(postCommentManager.create(postComment)).build();
	}

	@PUT
	@Path("/{commentId}")
	public PostComment updatePost(@PathParam("commentId") Long id, PostComment postComment) {
		postComment.setId(id);
		return postCommentManager.update(postComment);

	}

	@DELETE
	@Path("/{commentId}")
	public void deletePost(@PathParam("commentId") Long id) {
		PostComment postComment = postCommentManager.findById(id);
		postCommentManager.delete(postComment);
	}

}
