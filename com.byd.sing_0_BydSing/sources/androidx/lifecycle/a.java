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
    static a f1628a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class, C0047a> f1629b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<Class, Boolean> f1630c = new HashMap();

    /* JADX INFO: renamed from: androidx.lifecycle.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class C0047a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Map<Lifecycle.Event, List<b>> f1631a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Map<b, Lifecycle.Event> f1632b;

        C0047a(Map<b, Lifecycle.Event> map) {
            this.f1632b = map;
            for (Map.Entry<b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<b> arrayList = this.f1631a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f1631a.put(value, arrayList);
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
            b(this.f1631a.get(event), fVar, event, obj);
            b(this.f1631a.get(Lifecycle.Event.ON_ANY), fVar, event, obj);
        }
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f1633a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Method f1634b;

        b(int i, Method method) {
            this.f1633a = i;
            this.f1634b = method;
            method.setAccessible(true);
        }

        void a(f fVar, Lifecycle.Event event, Object obj) {
            try {
                int i = this.f1633a;
                if (i == 0) {
                    this.f1634b.invoke(obj, new Object[0]);
                } else if (i == 1) {
                    this.f1634b.invoke(obj, fVar);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.f1634b.invoke(obj, fVar, event);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("Failed to call observer method", e2.getCause());
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
            return this.f1633a == bVar.f1633a && this.f1634b.getName().equals(bVar.f1634b.getName());
        }

        public int hashCode() {
            return (this.f1633a * 31) + this.f1634b.getName().hashCode();
        }
    }

    a() {
    }

    private C0047a a(Class cls, Method[] methodArr) {
        int i;
        C0047a c0047aC;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (c0047aC = c(superclass)) != null) {
            map.putAll(c0047aC.f1632b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, Lifecycle.Event> entry : c(cls2).f1632b.entrySet()) {
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
        C0047a c0047a = new C0047a(map);
        this.f1629b.put(cls, c0047a);
        this.f1630c.put(cls, Boolean.valueOf(z));
        return c0047a;
    }

    private Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
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
        throw new IllegalArgumentException("Method " + bVar.f1634b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }

    C0047a c(Class cls) {
        C0047a c0047a = this.f1629b.get(cls);
        return c0047a != null ? c0047a : a(cls, null);
    }

    boolean d(Class cls) {
        Boolean bool = this.f1630c.get(cls);
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
        this.f1630c.put(cls, Boolean.FALSE);
        return false;
    }
}
