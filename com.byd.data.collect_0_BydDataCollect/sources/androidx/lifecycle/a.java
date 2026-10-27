package androidx.lifecycle;

import androidx.lifecycle.d;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ClassesInfoCache.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static a f1594a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class<?>, C0022a> f1595b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Class<?>, Boolean> f1596c = new HashMap();

    /* JADX INFO: renamed from: androidx.lifecycle.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class C0022a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Map<d.a, List<b>> f1597a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Map<b, d.a> f1598b;

        C0022a(Map<b, d.a> map) {
            this.f1598b = map;
            for (Map.Entry<b, d.a> entry : map.entrySet()) {
                d.a value = entry.getValue();
                List<b> arrayList = this.f1597a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f1597a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        private static void b(List<b> list, g gVar, d.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(gVar, aVar, obj);
                }
            }
        }

        void a(g gVar, d.a aVar, Object obj) {
            b(this.f1597a.get(aVar), gVar, aVar, obj);
            b(this.f1597a.get(d.a.ON_ANY), gVar, aVar, obj);
        }
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f1599a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Method f1600b;

        b(int i2, Method method) {
            this.f1599a = i2;
            this.f1600b = method;
            method.setAccessible(true);
        }

        void a(g gVar, d.a aVar, Object obj) {
            try {
                int i2 = this.f1599a;
                if (i2 == 0) {
                    this.f1600b.invoke(obj, new Object[0]);
                } else if (i2 == 1) {
                    this.f1600b.invoke(obj, gVar);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    this.f1600b.invoke(obj, gVar, aVar);
                }
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Failed to call observer method", e3.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.f1599a == bVar.f1599a && this.f1600b.getName().equals(bVar.f1600b.getName());
        }

        public int hashCode() {
            return (this.f1599a * 31) + this.f1600b.getName().hashCode();
        }
    }

    a() {
    }

    private C0022a a(Class<?> cls, Method[] methodArr) {
        int i2;
        C0022a c0022aC;
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (c0022aC = c(superclass)) != null) {
            map.putAll(c0022aC.f1598b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, d.a> entry : c(cls2).f1598b.entrySet()) {
                e(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            n nVar = (n) method.getAnnotation(n.class);
            if (nVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i2 = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(g.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i2 = 1;
                }
                d.a aVarValue = nVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(d.a.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (aVarValue != d.a.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i2 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(map, new b(i2, method), aVarValue, cls);
                z = true;
            }
        }
        C0022a c0022a = new C0022a(map);
        this.f1595b.put(cls, c0022a);
        this.f1596c.put(cls, Boolean.valueOf(z));
        return c0022a;
    }

    private Method[] b(Class<?> cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    private void e(Map<b, d.a> map, b bVar, d.a aVar, Class<?> cls) {
        d.a aVar2 = map.get(bVar);
        if (aVar2 == null || aVar == aVar2) {
            if (aVar2 == null) {
                map.put(bVar, aVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f1600b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
    }

    C0022a c(Class<?> cls) {
        C0022a c0022a = this.f1595b.get(cls);
        return c0022a != null ? c0022a : a(cls, null);
    }

    boolean d(Class<?> cls) {
        Boolean bool = this.f1596c.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] methodArrB = b(cls);
        for (Method method : methodArrB) {
            if (((n) method.getAnnotation(n.class)) != null) {
                a(cls, methodArrB);
                return true;
            }
        }
        this.f1596c.put(cls, Boolean.FALSE);
        return false;
    }
}
