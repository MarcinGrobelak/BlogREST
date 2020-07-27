package com.mgrobelak.blogrest.entities;

import java.lang.reflect.Method;
import java.util.List;

public interface Commentable {

	Long getId();

	List<PostComment> getPostComments();

	Class<?> getResourceClass();

	Class<?> getCommentResourceClass();

	Method getCommentSubresourceMethod();
}
