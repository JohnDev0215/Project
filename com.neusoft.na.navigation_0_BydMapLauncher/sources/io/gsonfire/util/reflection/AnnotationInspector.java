package io.gsonfire.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class AnnotationInspector<T extends AccessibleObject, M> {
    private final ConcurrentMap<Class, ConcurrentMap<Class<? extends Annotation>, Collection<M>>> cache = new ConcurrentHashMap();

    protected abstract T[] getDeclaredMembers(Class cls);

    protected abstract M map(T t);

    public Collection<M> getAnnotatedMembers(Class cls, Class<? extends Annotation> cls2) {
        if (cls != null) {
            Collection<M> fromCache = getFromCache(cls, cls2);
            if (fromCache != null) {
                return fromCache;
            }
            if (getFromCache(cls, cls2) == null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (AccessibleObject accessibleObject : getDeclaredMembers(cls)) {
                    if (accessibleObject.isAnnotationPresent(cls2)) {
                        accessibleObject.setAccessible(true);
                        linkedHashSet.add(map(accessibleObject));
                    }
                }
                linkedHashSet.addAll(getAnnotatedMembers(cls.getSuperclass(), cls2));
                for (Class<?> cls3 : cls.getInterfaces()) {
                    linkedHashSet.addAll(getAnnotatedMembers(cls3, cls2));
                }
                ConcurrentMap<Class<? extends Annotation>, Collection<M>> concurrentHashMap = new ConcurrentHashMap<>();
                ConcurrentMap<Class<? extends Annotation>, Collection<M>> concurrentMapPutIfAbsent = this.cache.putIfAbsent(cls, concurrentHashMap);
                if (concurrentMapPutIfAbsent != null) {
                    concurrentHashMap = concurrentMapPutIfAbsent;
                }
                concurrentHashMap.put(cls2, linkedHashSet);
                return linkedHashSet;
            }
        }
        return Collections.emptyList();
    }

    private Collection<M> getFromCache(Class cls, Class<? extends Annotation> cls2) {
        Collection<M> collection;
        ConcurrentMap<Class<? extends Annotation>, Collection<M>> concurrentMap = this.cache.get(cls);
        if (concurrentMap == null || (collection = concurrentMap.get(cls2)) == null) {
            return null;
        }
        return collection;
    }
}
