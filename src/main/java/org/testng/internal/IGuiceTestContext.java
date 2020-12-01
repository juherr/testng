package org.testng.internal;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.testng.IClass;
import org.testng.annotations.Guice;
import org.testng.internal.annotations.AnnotationHelper;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public interface IGuiceTestContext {

    @Deprecated
    List<Module> getGuiceModules(Class<? extends Module> cls);

    @Deprecated
    default void addGuiceModule(Module module) {}

    @Deprecated
    default List<Module> getAllGuiceModules() {
        return Collections.emptyList();
    }

    @Deprecated
    Injector getInjector(List<Module> moduleInstances);

    @Deprecated
    Injector getInjector(IClass iClass);

    default <T> T getInstance(IClass iClass, Class<? extends T> targetClass, Supplier<T> fallback) {
        Guice guice = AnnotationHelper.findAnnotationSuperClasses(Guice.class, iClass.getRealClass());
        if (guice == null) {
            return fallback.get();
        } else {
            Injector injector = getInjector(iClass);
            return injector.getInstance(targetClass);
        }
    }

    @Deprecated
    void addInjector(List<Module> moduleInstances, Injector injector);
}
