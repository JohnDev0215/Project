package b.c.a.r;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Primitives.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<Class<?>, Class<?>> f2240a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map<Class<?>, Class<?>> f2241b;

    static {
        HashMap map = new HashMap(16);
        HashMap map2 = new HashMap(16);
        a(map, map2, Boolean.TYPE, Boolean.class);
        a(map, map2, Byte.TYPE, Byte.class);
        a(map, map2, Character.TYPE, Character.class);
        a(map, map2, Double.TYPE, Double.class);
        a(map, map2, Float.TYPE, Float.class);
        a(map, map2, Integer.TYPE, Integer.class);
        a(map, map2, Long.TYPE, Long.class);
        a(map, map2, Short.TYPE, Short.class);
        a(map, map2, Void.TYPE, Void.class);
        f2240a = Collections.unmodifiableMap(map);
        f2241b = Collections.unmodifiableMap(map2);
    }

    public static void a(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    public static boolean b(Type type) {
        return f2240a.containsKey(type);
    }

    public static <T> Class<T> c(Class<T> cls) {
        Class<T> cls2 = (Class) f2240a.get(a.b(cls));
        return cls2 == null ? cls : cls2;
    }
}
