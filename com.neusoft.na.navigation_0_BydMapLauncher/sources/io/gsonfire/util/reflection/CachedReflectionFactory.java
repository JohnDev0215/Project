package io.gsonfire.util.reflection;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public class CachedReflectionFactory implements Factory {
    private final ConcurrentMap<Class, SoftReference<Object>> cache = new ConcurrentHashMap();

    @Override // io.gsonfire.util.reflection.Factory
    public <T> T get(Class<T> cls) {
        T t;
        SoftReference<Object> softReference = this.cache.get(cls);
        if (softReference != null && (t = (T) softReference.get()) != null) {
            return t;
        }
        try {
            T tNewInstance = cls.newInstance();
            this.cache.putIfAbsent(cls, new SoftReference<>(tNewInstance));
            return tNewInstance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        }
    }
}
