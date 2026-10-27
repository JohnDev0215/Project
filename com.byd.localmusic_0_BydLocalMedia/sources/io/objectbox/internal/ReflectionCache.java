package io.objectbox.internal;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class ReflectionCache {
    private static final ReflectionCache instance = new ReflectionCache();
    private final Map<Class<?>, Map<String, Field>> fields = new HashMap();

    public static ReflectionCache getInstance() {
        return instance;
    }

    @Nonnull
    public synchronized Field getField(Class<?> cls, String str) {
        Field declaredField;
        Map<String, Field> map = this.fields.get(cls);
        if (map == null) {
            map = new HashMap<>();
            this.fields.put(cls, map);
        }
        declaredField = map.get(str);
        if (declaredField == null) {
            try {
                declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                map.put(str, declaredField);
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException(e);
            }
        }
        return declaredField;
    }
}
