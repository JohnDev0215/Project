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
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static b f1492a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class, a> f1493b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Class, Boolean> f1494c = new HashMap();

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Map<Lifecycle.Event, List<C0029b>> f1495a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Map<C0029b, Lifecycle.Event> f1496b;

        a(Map<C0029b, Lifecycle.Event> map) {
            this.f1496b = map;
            for (Map.Entry<C0029b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<C0029b> arrayList = this.f1495a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f1495a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        private static void b(List<C0029b> list, g gVar, Lifecycle.Event event, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(gVar, event, obj);
                }
            }
        }

        void a(g gVar, Lifecycle.Event event, Object obj) {
            b(this.f1495a.get(event), gVar, event, obj);
            b(this.f1495a.get(Lifecycle.Event.ON_ANY), gVar, event, obj);
        }
    }

    /* JADX INFO: renamed from: androidx.lifecycle.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class C0029b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f1497a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Method f1498b;

        C0029b(int i, Method method) {
            this.f1497a = i;
            this.f1498b = method;
            method.setAccessible(true);
        }

        void a(g gVar, Lifecycle.Event event, Object obj) {
            try {
                int i = this.f1497a;
                if (i == 0) {
                    this.f1498b.invoke(obj, new Object[0]);
                } else if (i == 1) {
                    this.f1498b.invoke(obj, gVar);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.f1498b.invoke(obj, gVar, event);
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
            if (obj == null || C0029b.class != obj.getClass()) {
                return false;
            }
            C0029b c0029b = (C0029b) obj;
            return this.f1497a == c0029b.f1497a && this.f1498b.getName().equals(c0029b.f1498b.getName());
        }

        public int hashCode() {
            return (this.f1497a * 31) + this.f1498b.getName().hashCode();
        }
    }

    b() {
    }

    private a a(Class cls, Method[] methodArr) {
        int i;
        a aVarC;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (aVarC = c(superclass)) != null) {
            map.putAll(aVarC.f1496b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<C0029b, Lifecycle.Event> entry : c(cls2).f1496b.entrySet()) {
                e(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            o oVar = (o) method.getAnnotation(o.class);
            if (oVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(g.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                Lifecycle.Event eventValue = oVar.value();
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
                e(map, new C0029b(i, method), eventValue, cls);
                z = true;
            }
        }
        a aVar = new a(map);
        this.f1493b.put(cls, aVar);
        this.f1494c.put(cls, Boolean.valueOf(z));
        return aVar;
    }

    private Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    private void e(Map<C0029b, Lifecycle.Event> map, C0029b c0029b, Lifecycle.Event event, Class cls) {
        Lifecycle.Event event2 = map.get(c0029b);
        if (event2 == null || event == event2) {
            if (event2 == null) {
                map.put(c0029b, event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + c0029b.f1498b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }

    a c(Class cls) {
        a aVar = this.f1493b.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }

    boolean d(Class cls) {
        Boolean bool = this.f1494c.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] methodArrB = b(cls);
        for (Method method : methodArrB) {
            if (((o) method.getAnnotation(o.class)) != null) {
                a(cls, methodArrB);
                return true;
            }
        }
        this.f1494c.put(cls, Boolean.FALSE);
        return false;
    }
}
