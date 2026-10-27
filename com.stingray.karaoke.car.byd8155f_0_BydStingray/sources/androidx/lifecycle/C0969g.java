package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: androidx.lifecycle.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0969g {
    public static final C0969g c = new C0969g();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f11987a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f11988b = new HashMap();

    public static void b(HashMap map, C0968f c0968f, EnumC0980s enumC0980s, Class cls) {
        EnumC0980s enumC0980s2 = (EnumC0980s) map.get(c0968f);
        if (enumC0980s2 == null || enumC0980s == enumC0980s2) {
            if (enumC0980s2 == null) {
                map.put(c0968f, enumC0980s);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + c0968f.f11985b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + enumC0980s2 + ", new value " + enumC0980s);
    }

    public final C0967e a(Class cls, Method[] methodArr) {
        int i7;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        HashMap map2 = this.f11987a;
        if (superclass != null) {
            C0967e c0967eA = (C0967e) map2.get(superclass);
            if (c0967eA == null) {
                c0967eA = a(superclass, null);
            }
            map.putAll(c0967eA.f11981b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            C0967e c0967eA2 = (C0967e) map2.get(cls2);
            if (c0967eA2 == null) {
                c0967eA2 = a(cls2, null);
            }
            for (Map.Entry entry : c0967eA2.f11981b.entrySet()) {
                b(map, (C0968f) entry.getKey(), (EnumC0980s) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e10) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e10);
            }
        }
        boolean z6 = false;
        for (Method method : methodArr) {
            L l = (L) method.getAnnotation(L.class);
            if (l != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i7 = 0;
                } else {
                    if (!A.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i7 = 1;
                }
                EnumC0980s enumC0980sValue = l.value();
                if (parameterTypes.length > 1) {
                    if (!EnumC0980s.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (enumC0980sValue != EnumC0980s.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i7 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                b(map, new C0968f(i7, method), enumC0980sValue, cls);
                z6 = true;
            }
        }
        C0967e c0967e = new C0967e(map);
        map2.put(cls, c0967e);
        this.f11988b.put(cls, Boolean.valueOf(z6));
        return c0967e;
    }
}
