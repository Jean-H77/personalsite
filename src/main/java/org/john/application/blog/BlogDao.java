package org.john.application.blog;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BlogDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS blog (" +
            "id SERIAL PRIMARY KEY, " +
            "author VARCHAR, " +
            "title VARCHAR, " +
            "content VARCHAR, " +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "updated_at TIMESTAMP DEFAULT NULL," +
            "deleted_at TIMESTAMP DEFAULT NULL)")
    void createTable();

    @SqlUpdate("INSERT INTO blog (author, title, content) VALUES (:author, :title, :content)")
    @GetGeneratedKeys
    @RegisterBeanMapper(BlogEntity.class)
    BlogEntity insertBean(@BindBean BlogEntity blogEntity);

    @SqlUpdate("UPDATE blog SET title = :title, content = :content, updated_at = CURRENT_TIMESTAMP WHERE id = :id")
    void updateBean(@BindBean BlogEntity blogEntity);

    @SqlUpdate("UPDATE blog SET deleted_at = CURRENT_TIMESTAMP WHERE id = :id")
    void deleteBean(@Bind("id") int id);

    @SqlQuery("SELECT * FROM blog WHERE id = :id AND deleted_at IS NULL")
    @RegisterBeanMapper(BlogEntity.class)
    BlogEntity getBean(@Bind("id") int blogId);

    @SqlQuery("SELECT * FROM blog WHERE deleted_at IS NULL")
    @RegisterBeanMapper(BlogEntity.class)
    List<BlogEntity> getAllActiveBlogs();
}
