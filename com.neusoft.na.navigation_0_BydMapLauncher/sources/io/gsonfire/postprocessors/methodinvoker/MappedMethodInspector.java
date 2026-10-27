package io.gsonfire.postprocessors.methodinvoker;

import io.gsonfire.annotations.ExposeMethodResult;
import io.gsonfire.util.reflection.AnnotationInspector;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class MappedMethodInspector extends AnnotationInspector<Method, MappedMethod> {
    MappedMethodInspector() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.gsonfire.util.reflection.AnnotationInspector
    public Method[] getDeclaredMembers(Class cls) {
        return cls.getDeclaredMethods();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.gsonfire.util.reflection.AnnotationInspector
    public MappedMethod map(Method method) {
        if (method.getParameterTypes().length > 0) {
            throw new IllegalArgumentException("The methods annotated with ExposeMethodResult should have no arguments");
        }
        ExposeMethodResult exposeMethodResult = (ExposeMethodResult) method.getAnnotation(ExposeMethodResult.class);
        return new MappedMethod(method, exposeMethodResult.value(), exposeMethodResult.conflictResolution());
    }
}
