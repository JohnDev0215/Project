package b.b.h.f.i;

import b.b.h.f.g.f;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;

/* JADX INFO: compiled from: ColumnUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashSet<Class<?>> f1897a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashSet<Class<?>> f1898b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final HashSet<Class<?>> f1899c;

    static {
        HashSet<Class<?>> hashSet = new HashSet<>(2);
        f1897a = hashSet;
        HashSet<Class<?>> hashSet2 = new HashSet<>(2);
        f1898b = hashSet2;
        HashSet<Class<?>> hashSet3 = new HashSet<>(4);
        f1899c = hashSet3;
        hashSet.add(Boolean.TYPE);
        hashSet.add(Boolean.class);
        hashSet2.add(Integer.TYPE);
        hashSet2.add(Integer.class);
        hashSet3.addAll(hashSet2);
        hashSet3.add(Long.TYPE);
        hashSet3.add(Long.class);
    }

    public static Object a(Object obj) {
        return obj != null ? f.a(obj.getClass()).c(obj) : obj;
    }

    public static Method b(Class<?> cls, String str) {
        if (!str.startsWith("is")) {
            str = "is" + str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            b.b.m.c.a.a(b.b.h.b.f1850a, str + " not exist");
            return null;
        }
    }

    public static Method c(Class<?> cls, String str, Class<?> cls2) {
        String str2;
        if (str.startsWith("is")) {
            str2 = "set" + str.substring(2, 3).toUpperCase() + str.substring(3);
        } else {
            str2 = "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str2, cls2);
        } catch (NoSuchMethodException unused) {
            b.b.m.c.a.a(b.b.h.b.f1850a, str2 + " not exist");
            return null;
        }
    }

    public static Method d(Class<?> cls, Field field) {
        if (Object.class.equals(cls)) {
            return null;
        }
        String name = field.getName();
        Method methodB = g(field.getType()) ? b(cls, name) : null;
        if (methodB == null) {
            String str = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                methodB = cls.getDeclaredMethod(str, new Class[0]);
            } catch (NoSuchMethodException unused) {
                b.b.m.c.a.a(b.b.h.b.f1850a, str + " not exist");
            }
        }
        return methodB == null ? d(cls.getSuperclass(), field) : methodB;
    }

    public static Method e(Class<?> cls, Field field) {
        if (Object.class.equals(cls)) {
            return null;
        }
        String name = field.getName();
        Class<?> type = field.getType();
        Method methodC = g(type) ? c(cls, name, type) : null;
        if (methodC == null) {
            String str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                methodC = cls.getDeclaredMethod(str, type);
            } catch (NoSuchMethodException unused) {
                b.b.m.c.a.a(b.b.h.b.f1850a, str + " not exist");
            }
        }
        return methodC == null ? e(cls.getSuperclass(), field) : methodC;
    }

    public static boolean f(Class<?> cls) {
        return f1899c.contains(cls);
    }

    public static boolean g(Class<?> cls) {
        return f1897a.contains(cls);
    }

    public static boolean h(Class<?> cls) {
        return f1898b.contains(cls);
    }
}
