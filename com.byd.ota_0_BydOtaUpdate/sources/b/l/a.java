package b.l;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ClassesInfoCache.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f1963a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<Class, C0036a> f1964b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<Class, Boolean> f1965c = new HashMap();

    /* JADX INFO: renamed from: b.l.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static class C0036a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<Lifecycle.Event, List<b>> f1966a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<b, Lifecycle.Event> f1967b;

        public C0036a(Map<b, Lifecycle.Event> map) {
            this.f1967b = map;
            for (Map.Entry<b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<b> arrayList = this.f1966a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f1966a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        public static void b(List<b> list, f fVar, Lifecycle.Event event, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(fVar, event, obj);
                }
            }
        }

        public void a(f fVar, Lifecycle.Event event, Object obj) {
            b(this.f1966a.get(event), fVar, event, obj);
            b(this.f1966a.get(Lifecycle.Event.ON_ANY), fVar, event, obj);
        }
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1968a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f1969b;

        public b(int i2, Method method) {
            this.f1968a = i2;
            this.f1969b = method;
            method.setAccessible(true);
        }

        public void a(f fVar, Lifecycle.Event event, Object obj) {
            try {
                int i2 = this.f1968a;
                if (i2 == 0) {
                    this.f1969b.invoke(obj, new Object[0]);
                } else if (i2 == 1) {
                    this.f1969b.invoke(obj, fVar);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    this.f1969b.invoke(obj, fVar, event);
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
            return this.f1968a == bVar.f1968a && this.f1969b.getName().equals(bVar.f1969b.getName());
        }

        public int hashCode() {
            return (this.f1968a * 31) + this.f1969b.getName().hashCode();
        }
    }

    public final C0036a a(Class cls, Method[] methodArr) {
        int i2;
        C0036a c0036aC;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (c0036aC = c(superclass)) != null) {
            map.putAll(c0036aC.f1967b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, Lifecycle.Event> entry : c(cls2).f1967b.entrySet()) {
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
                    i2 = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(f.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i2 = 1;
                }
                Lifecycle.Event eventValue = mVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(Lifecycle.Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (eventValue != Lifecycle.Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i2 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(map, new b(i2, method), eventValue, cls);
                z = true;
            }
        }
        C0036a c0036a = new C0036a(map);
        this.f1964b.put(cls, c0036a);
        this.f1965c.put(cls, Boolean.valueOf(z));
        return c0036a;
    }

    public final Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    public C0036a c(Class cls) {
        C0036a c0036a = this.f1964b.get(cls);
        return c0036a != null ? c0036a : a(cls, null);
    }

    public boolean d(Class cls) {
        Boolean bool = this.f1965c.get(cls);
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
        this.f1965c.put(cls, Boolean.FALSE);
        return false;
    }

    public final void e(Map<b, Lifecycle.Event> map, b bVar, Lifecycle.Event event, Class cls) {
        Lifecycle.Event event2 = map.get(bVar);
        if (event2 == null || event == event2) {
            if (event2 == null) {
                map.put(bVar, event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f1969b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }
}
