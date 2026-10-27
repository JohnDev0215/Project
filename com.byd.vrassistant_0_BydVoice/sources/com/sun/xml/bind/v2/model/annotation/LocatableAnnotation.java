package com.sun.xml.bind.v2.model.annotation;

import com.sun.xml.bind.v2.runtime.Location;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class LocatableAnnotation implements InvocationHandler, Locatable, Location {
    private static final Map<Class, Quick> quicks = new HashMap();
    private final Annotation core;
    private final Locatable upstream;

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return this;
    }

    public static <A extends Annotation> A create(A a, Locatable locatable) {
        if (a == null) {
            return null;
        }
        Class<? extends Annotation> clsAnnotationType = a.annotationType();
        Map<Class, Quick> map = quicks;
        if (map.containsKey(clsAnnotationType)) {
            return map.get(clsAnnotationType).newInstance(locatable, a);
        }
        ClassLoader classClassLoader = SecureLoader.getClassClassLoader(LocatableAnnotation.class);
        try {
            return Class.forName(clsAnnotationType.getName(), false, classClassLoader) != clsAnnotationType ? a : (A) Proxy.newProxyInstance(classClassLoader, new Class[]{clsAnnotationType, Locatable.class}, new LocatableAnnotation(a, locatable));
        } catch (ClassNotFoundException | IllegalArgumentException unused) {
            return a;
        }
    }

    LocatableAnnotation(Annotation annotation, Locatable locatable) {
        this.core = annotation;
        this.upstream = locatable;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return this.upstream;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            if (method.getDeclaringClass() == Locatable.class) {
                return method.invoke(this, objArr);
            }
            if (Modifier.isStatic(method.getModifiers())) {
                throw new IllegalArgumentException();
            }
            return method.invoke(this.core, objArr);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() != null) {
                throw e.getTargetException();
            }
            throw e;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.Location
    public String toString() {
        return this.core.toString();
    }

    static {
        for (Quick quick : Init.getAll()) {
            quicks.put(quick.annotationType(), quick);
        }
    }
}
