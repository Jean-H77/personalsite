package org.john.application;

import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import io.javalin.Javalin;
import org.john.core.router.Router;

import java.util.Set;

public class App {

    public static void main(String[] args) {
        var injector = Guice.createInjector(new AppModule());
        var routers = injector.getInstance(Key.get(new TypeLiteral<Set<Router>>() {}));
        var javalin = injector.getInstance(Javalin.class);
        routers.forEach(router -> router.configure(javalin));

        javalin.start();
    }
}