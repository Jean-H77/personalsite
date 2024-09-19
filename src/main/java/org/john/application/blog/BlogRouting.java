package org.john.application.blog;
import io.javalin.Javalin;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.john.core.router.Router;

@Singleton
public class BlogRouting implements Router {

    private final BlogController blogController;

    @Inject
    public BlogRouting(BlogController blogController) {
        this.blogController = blogController;
    }

    @Override
    public void configure(Javalin javalin) {
        javalin
                .get("/blog/{id}", blogController::getBlog)
                .get("/blog", blogController::getBlogs)

                .post("/blog", blogController::postBlog)

                .delete("/blog/{id}", blogController::deleteBlog)

                .put("/blog/{id}", blogController::updateBlog);
    }
}
