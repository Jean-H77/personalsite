package org.john.application.blog;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class BlogController {

    private final BlogService blogService;

    @Inject
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    public void postBlog(Context ctx) {
        var blog = ctx.bodyAsClass(BlogEntity.class);
        ctx.json(blogService.postBlog(blog));
    }

    public void getBlog(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("blog-id"));
        ctx.json(blogService.getBlog(id));
    }

    public void updateBlog(Context ctx) {
        blogService.updateBlog(ctx.bodyAsClass(BlogEntity.class));
        ctx.status(HttpStatus.OK);
    }

    public void deleteBlog(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("blog-id"));
        blogService.deleteBlog(id);
        ctx.status(HttpStatus.NO_CONTENT);
    }

    public void getBlogs(Context ctx) {
        ctx.json(blogService.getBlogs());
    }
}
