package org.john.application.blog;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@Singleton
public class BlogService {

    private final Jdbi jdbi;

    @Inject
    public BlogService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public BlogEntity postBlog(BlogEntity blogEntity) {
        jdbi.onDemand(BlogDao.class).createTable(); // @Todo remove later
        return jdbi.onDemand(BlogDao.class).insertBean(blogEntity);
    }

    public BlogEntity getBlog(int id) {
        return jdbi.onDemand(BlogDao.class).getBean(id);
    }

    public List<BlogEntity> getBlogs() {
        return jdbi.onDemand(BlogDao.class).getAllActiveBlogs();
    }

    public void deleteBlog(int id) {
        jdbi.onDemand(BlogDao.class).deleteBean(id);
    }

    public void updateBlog(BlogEntity blogEntity) {
        jdbi.onDemand(BlogDao.class).updateBean(blogEntity);
    }
}
