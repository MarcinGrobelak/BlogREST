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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mgrobelak.blogrest.ejb.GenericManager;
import com.mgrobelak.blogrest.entities.User;
import com.mgrobelak.blogrest.filters.PaginationFilter;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	private GenericManager<User> userManager;

	@GET
	public List<User> getUsers(@BeanParam PaginationFilter pagination) {

		if (pagination.getStartId() > 0 && pagination.getSize() > 0) {
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("minId", Long.valueOf(pagination.getStartId()));
			return userManager.performQueryParam(User.class, "getUsersFromId", parameters, pagination.getSize());
		}

		return userManager.performQuery(User.class, "getUsers");
	}

	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") Long id) {
		return userManager.findById(User.class, id);
	}

	@POST
	public Response createUser(User user) {
		return Response.status(Status.CREATED).entity(userManager.create(user)).build();
	}

	@PUT
	@Path("/{userId}")
	public User updateUser(@PathParam("userId") Long id, User user) {
		user.setId(id);
		return userManager.update(user);

	}

	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam("userId") Long id) {
		User user = userManager.findById(User.class, id);
		userManager.delete(user);
	}

}