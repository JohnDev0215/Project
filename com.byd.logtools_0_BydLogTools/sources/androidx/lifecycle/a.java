package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
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
    static a f1651a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class, C0021a> f1652b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Class, Boolean> f1653c = new HashMap();

    /* JADX INFO: renamed from: androidx.lifecycle.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class C0021a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Map<Lifecycle.Event, List<b>> f1654a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Map<b, Lifecycle.Event> f1655b;

        C0021a(Map<b, Lifecycle.Event> map) {
            this.f1655b = map;
            for (Map.Entry<b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<b> arrayList = this.f1654a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f1654a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        private static void b(List<b> list, f fVar, Lifecycle.Event event, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(fVar, event, obj);
                }
            }
        }

        void a(f fVar, Lifecycle.Event event, Object obj) {
            b(this.f1654a.get(event), fVar, event, obj);
            b(this.f1654a.get(Lifecycle.Event.ON_ANY), fVar, event, obj);
        }
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f1656a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Method f1657b;

        b(int i, Method method) {
            this.f1656a = i;
            this.f1657b = method;
            method.setAccessible(true);
        }

        void a(f fVar, Lifecycle.Event event, Object obj) {
            try {
                int i = this.f1656a;
                if (i == 0) {
                    this.f1657b.invoke(obj, new Object[0]);
                } else if (i == 1) {
                    this.f1657b.invoke(obj, fVar);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.f1657b.invoke(obj, fVar, event);
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
            return this.f1656a == bVar.f1656a && this.f1657b.getName().equals(bVar.f1657b.getName());
        }

        public int hashCode() {
            return (this.f1656a * 31) + this.f1657b.getName().hashCode();
        }
    }

    a() {
    }

    private C0021a a(Class cls, Method[] methodArr) {
        int i;
        C0021a c0021aC;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (c0021aC = c(superclass)) != null) {
            map.putAll(c0021aC.f1655b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, Lifecycle.Event> entry : c(cls2).f1655b.entrySet()) {
                e(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            m mVar = (m) method.getAnnotation(m.class);
            if (mVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(f.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                Lifecycle.Event eventValue = mVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(Lifecycle.Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (eventValue != Lifecycle.Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(map, new b(i, method), eventValue, cls);
                z = true;
            }
        }
        C0021a c0021a = new C0021a(map);
        this.f1652b.put(cls, c0021a);
        this.f1653c.put(cls, Boolean.valueOf(z));
        return c0021a;
    }

    private Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    private void e(Map<b, Lifecycle.Event> map, b bVar, Lifecycle.Event event, Class cls) {
        Lifecycle.Event event2 = map.get(bVar);
        if (event2 == null || event == event2) {
            if (event2 == null) {
                map.put(bVar, event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f1657b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }

    C0021a c(Class cls) {
        C0021a c0021a = this.f1652b.get(cls);
        return c0021a != null ? c0021a : a(cls, null);
    }

    boolean d(Class cls) {
        Boolean bool = this.f1653c.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] methodArrB = b(cls);
        for (Method method : methodArrB) {
            if (((m) method.getAnnotation(m.class)) != null) {
                a(cls, methodArrB);
                return true;
            }
        }
        this.f1653c.put(cls, Boolean.FALSE);
        return false;
    }
}
