package com.amazon.identity.auth.device;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f176a = "com.amazon.identity.auth.device.aa";
    public static final WeakHashMap<Class<?>, Method> b = new WeakHashMap<>();

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        if (obj2 == null || obj2.getClass() != obj2.getClass()) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static <T> T a(T t) throws CloneNotSupportedException {
        T t2;
        if (t == null) {
            return null;
        }
        if (t instanceof pa) {
            return (T) ((pa) t).a();
        }
        if ((t instanceof String) || (t instanceof Integer)) {
            return t;
        }
        if (t instanceof Cloneable) {
            Cloneable cloneable = (Cloneable) t;
            synchronized (aa.class) {
                try {
                    Class<?> cls = cloneable.getClass();
                    WeakHashMap<Class<?>, Method> weakHashMap = b;
                    Method method = weakHashMap.get(cls);
                    if (method == null) {
                        method = cls.getMethod("clone", new Class[0]);
                        method.setAccessible(true);
                        weakHashMap.put(cls, method);
                    }
                    t2 = (T) method.invoke(cloneable, new Object[0]);
                } catch (Exception e) {
                    String str = String.format("Clone method failed on the object of type %s", cloneable.getClass().getName());
                    Log.e(ga.a(f176a), str, e);
                    throw new CloneNotSupportedException(str);
                }
            }
            return t2;
        }
        throw new CloneNotSupportedException(String.format("Clone not supported on type %s", t.getClass().getName()));
    }

    public static <T extends pa<T>> Map<String, T> a(Map<String, T> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            T value = entry.getValue();
            map2.put(entry.getKey(), value == null ? null : value.a());
        }
        return map2;
    }
}
