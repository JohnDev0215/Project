package x0;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ClassesInfoCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f9048a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<Class<?>, C0141a> f9049b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<Class<?>, Boolean> f9050c = new HashMap();

    /* JADX INFO: renamed from: x0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static class C0141a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<Lifecycle.Event, List<b>> f9051a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<b, Lifecycle.Event> f9052b;

        public C0141a(Map<b, Lifecycle.Event> map) {
            this.f9052b = map;
            for (Map.Entry<b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<b> arrayList = this.f9051a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f9051a.put(value, arrayList);
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
            b(this.f9051a.get(event), fVar, event, obj);
            b(this.f9051a.get(Lifecycle.Event.ON_ANY), fVar, event, obj);
        }
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9053a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f9054b;

        public b(int i8, Method method) {
            this.f9053a = i8;
            this.f9054b = method;
            method.setAccessible(true);
        }

        public void a(f fVar, Lifecycle.Event event, Object obj) {
            try {
                int i8 = this.f9053a;
                if (i8 == 0) {
                    this.f9054b.invoke(obj, new Object[0]);
                } else if (i8 == 1) {
                    this.f9054b.invoke(obj, fVar);
                } else {
                    if (i8 != 2) {
                        return;
                    }
                    this.f9054b.invoke(obj, fVar, event);
                }
            } catch (IllegalAccessException e8) {
                throw new RuntimeException(e8);
            } catch (InvocationTargetException e9) {
                throw new RuntimeException("Failed to call observer method", e9.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f9053a == bVar.f9053a && this.f9054b.getName().equals(bVar.f9054b.getName());
        }

        public int hashCode() {
            return (this.f9053a * 31) + this.f9054b.getName().hashCode();
        }
    }

    public final C0141a a(Class<?> cls, Method[] methodArr) {
        int i8;
        C0141a c0141aC;
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (c0141aC = c(superclass)) != null) {
            map.putAll(c0141aC.f9052b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, Lifecycle.Event> entry : c(cls2).f9052b.entrySet()) {
                e(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z7 = false;
        for (Method method : methodArr) {
            m mVar = (m) method.getAnnotation(m.class);
            if (mVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i8 = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(f.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i8 = 1;
                }
                Lifecycle.Event eventValue = mVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(Lifecycle.Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (eventValue != Lifecycle.Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i8 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(map, new b(i8, method), eventValue, cls);
                z7 = true;
            }
        }
        C0141a c0141a = new C0141a(map);
        this.f9049b.put(cls, c0141a);
        this.f9050c.put(cls, Boolean.valueOf(z7));
        return c0141a;
    }

    public final Method[] b(Class<?> cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e8) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e8);
        }
    }

    public C0141a c(Class<?> cls) {
        C0141a c0141a = this.f9049b.get(cls);
        return c0141a != null ? c0141a : a(cls, null);
    }

    public boolean d(Class<?> cls) {
        Boolean bool = this.f9050c.get(cls);
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
        this.f9050c.put(cls, Boolean.FALSE);
        return false;
    }

    public final void e(Map<b, Lifecycle.Event> map, b bVar, Lifecycle.Event event, Class<?> cls) {
        Lifecycle.Event event2 = map.get(bVar);
        if (event2 == null || event == event2) {
            if (event2 == null) {
                map.put(bVar, event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f9054b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }
}
