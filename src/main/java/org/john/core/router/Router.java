package org.john.core.router;

import io.javalin.Javalin;

public interface Router {
    void configure(Javalin javalin);
}
