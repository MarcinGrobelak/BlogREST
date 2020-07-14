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

import com.mgrobelak.blogrest.ejb.UserManager;
import com.mgrobelak.blogrest.entities.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	private UserManager userManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return userManager.getAll();
	}

	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") Long id) {
		return userManager.findById(id);
	}

	@POST
	public User createUser(User user) {
		return userManager.create(user);
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
		User user = userManager.findById(id);
		userManager.delete(user);
	}

}
