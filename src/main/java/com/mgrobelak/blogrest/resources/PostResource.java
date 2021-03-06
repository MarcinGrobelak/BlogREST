package com.mgrobelak.blogrest.resources;

import java.util.HashMap;

/**
 * @author Marcin Grobelak
 */

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.mgrobelak.blogrest.ejb.GenericManager;
import com.mgrobelak.blogrest.entities.Post;
import com.mgrobelak.blogrest.filters.DateFilter;
import com.mgrobelak.blogrest.filters.PaginationFilter;
import com.mgrobelak.blogrest.hateoas.HateoasHelper;

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

	@Inject
	private GenericManager<Post> postManager;

	@Inject
	private PostCommentResource postCommentResource;

	@GET
	public List<Post> getPosts(@BeanParam DateFilter dateFilter, @BeanParam PaginationFilter pagination) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("startId", pagination.getStartId());
		parameters.put("month", dateFilter.getMonth());
		parameters.put("year", dateFilter.getYear());
		return postManager.performQueryParam(Post.class, "getFilteredPosts", parameters, pagination.getSize());
	}

	@GET
	@Path("/{postId}")
	public Post getPost(@PathParam("postId") Long id, @Context UriInfo uriInfo) {
		Post post = postManager.findById(Post.class, id);
		post.getLinks().clear();
		post.addLink(HateoasHelper.getSelfUri(uriInfo, post));
		post.addLink(HateoasHelper.getAuthorUri(uriInfo, post));
		post.addLink(HateoasHelper.getCommentsUri(uriInfo, post));
		return post;
	}

	@POST
	public Response createPost(Post post) {
		return Response.status(Status.CREATED).entity(postManager.create(post)).build();
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
		Post post = postManager.findById(Post.class, id);
		postManager.delete(post);
	}

	@Path("/{postId}/comments")
	public PostCommentResource getPostCommentResource() {
		return postCommentResource;
	}

}