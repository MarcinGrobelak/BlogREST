package com.mgrobelak.blogrest.hateoas;

import java.lang.reflect.Method;
import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.mgrobelak.blogrest.entities.Autorizable;
import com.mgrobelak.blogrest.entities.BasicEntity;
import com.mgrobelak.blogrest.entities.Commentable;
import com.mgrobelak.blogrest.resources.UserResource;

public class HateoasHelper {

	public static final String REL_SELF = "self";
	public static final String REL_AUTHOR = "author";
	public static final String REL_COMMENTS = "comments";

	public static Link getSelfUri(UriInfo uriInfo, BasicEntity entity) {
		URI selfUri = uriInfo.getBaseUriBuilder().path(entity.getResourceClass()).path(String.valueOf(entity.getId()))
				.build();
		return new Link(REL_SELF, selfUri.toString());
	}

	public static Link getAuthorUri(UriInfo uriInfo, Autorizable autorizable) {
		URI authorUri = uriInfo.getBaseUriBuilder().path(UserResource.class)
				.path(String.valueOf(autorizable.getAuthor().getId())).build();
		return new Link(REL_AUTHOR, authorUri.toString());
	}

	public static Link getCommentsUri(UriInfo uriInfo, Commentable entity) {
		Method commentSubResourceGetter = entity.getCommentSubresourceMethod();
		if (commentSubResourceGetter != null) {
			URI commentsUri = uriInfo.getBaseUriBuilder().path(entity.getResourceClass())
					.path(entity.getResourceClass(), commentSubResourceGetter.getName())
					.path(entity.getCommentResourceClass()).resolveTemplate("commentID", entity.getId()).build();
			return new Link(REL_COMMENTS, commentsUri.toString());

		}
		return null;
	}
}
