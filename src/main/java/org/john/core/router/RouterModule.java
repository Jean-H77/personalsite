package org.john.core.router;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;

import java.util.List;

public class RouterModule extends AbstractModule {

    @Override
    @SuppressWarnings("unchecked")
    protected void configure() {
        var binder = Multibinder.newSetBinder(binder(), Router.class);

        try (var scanResult = new ClassGraph().enableAllInfo()
                .whitelistPackages("org.john.application")
                .scan()) {

            ClassInfoList routerClasses = scanResult.getClassesImplementing("org.john.core.router.Router");
            List<Class<?>> routerClassRefs = routerClasses.loadClasses();

            for (Class<?> routerClass : routerClassRefs) {
                binder.addBinding().to((Class<? extends Router>) routerClass);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to scan and load Router subclasses", e);
        }
    }
}
