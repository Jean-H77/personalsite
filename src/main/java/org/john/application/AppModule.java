package org.john.application;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.javalin.Javalin;
import jakarta.inject.Singleton;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.john.core.router.RouterModule;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new RouterModule());
    }

    @Provides
    Javalin createJavalin() {
        return Javalin.create();
    }

    @Provides
    @Singleton
    Jdbi createJdbi() {
        //Todo Replace to configuration module and setup with application.yaml
        String jdbcUrl = "jdbc:postgresql://localhost:5432/personalsite";
        String username = "postgres";
        String password = "password";
        var jdbi = Jdbi.create(jdbcUrl, username, password);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}
