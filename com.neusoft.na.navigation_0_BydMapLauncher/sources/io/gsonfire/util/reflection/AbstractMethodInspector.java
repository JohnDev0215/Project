package io.gsonfire.util.reflection;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractMethodInspector<M> extends AnnotationInspector<Method, M> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.gsonfire.util.reflection.AnnotationInspector
    public Method[] getDeclaredMembers(Class cls) {
        return cls.getDeclaredMethods();
    }
}
