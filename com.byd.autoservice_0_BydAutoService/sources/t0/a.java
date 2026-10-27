package t0;

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
    public static a f4990a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<Class, C0061a> f4991b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<Class, Boolean> f4992c = new HashMap();

    /* JADX INFO: renamed from: t0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static class C0061a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<Lifecycle.Event, List<b>> f4993a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<b, Lifecycle.Event> f4994b;

        public C0061a(Map<b, Lifecycle.Event> map) {
            this.f4994b = map;
            for (Map.Entry<b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<b> arrayList = this.f4993a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f4993a.put(value, arrayList);
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
            b(this.f4993a.get(event), fVar, event, obj);
            b(this.f4993a.get(Lifecycle.Event.ON_ANY), fVar, event, obj);
        }
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f4995a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f4996b;

        public b(int i3, Method method) {
            this.f4995a = i3;
            this.f4996b = method;
            method.setAccessible(true);
        }

        public void a(f fVar, Lifecycle.Event event, Object obj) {
            try {
                int i3 = this.f4995a;
                if (i3 == 0) {
                    this.f4996b.invoke(obj, new Object[0]);
                } else if (i3 == 1) {
                    this.f4996b.invoke(obj, fVar);
                } else {
                    if (i3 != 2) {
                        return;
                    }
                    this.f4996b.invoke(obj, fVar, event);
                }
            } catch (IllegalAccessException e3) {
                throw new RuntimeException(e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Failed to call observer method", e4.getCause());
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
            return this.f4995a == bVar.f4995a && this.f4996b.getName().equals(bVar.f4996b.getName());
        }

        public int hashCode() {
            return (this.f4995a * 31) + this.f4996b.getName().hashCode();
        }
    }

    public final C0061a a(Class cls, Method[] methodArr) {
        int i3;
        C0061a c0061aC;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (c0061aC = c(superclass)) != null) {
            map.putAll(c0061aC.f4994b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, Lifecycle.Event> entry : c(cls2).f4994b.entrySet()) {
                e(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z3 = false;
        for (Method method : methodArr) {
            m mVar = (m) method.getAnnotation(m.class);
            if (mVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i3 = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(f.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i3 = 1;
                }
                Lifecycle.Event eventValue = mVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(Lifecycle.Event.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (eventValue != Lifecycle.Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i3 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(map, new b(i3, method), eventValue, cls);
                z3 = true;
            }
        }
        C0061a c0061a = new C0061a(map);
        this.f4991b.put(cls, c0061a);
        this.f4992c.put(cls, Boolean.valueOf(z3));
        return c0061a;
    }

    public final Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e3) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e3);
        }
    }

    public C0061a c(Class cls) {
        C0061a c0061a = this.f4991b.get(cls);
        return c0061a != null ? c0061a : a(cls, null);
    }

    public boolean d(Class cls) {
        Boolean bool = this.f4992c.get(cls);
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
        this.f4992c.put(cls, Boolean.FALSE);
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
        throw new IllegalArgumentException("Method " + bVar.f4996b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }
}
