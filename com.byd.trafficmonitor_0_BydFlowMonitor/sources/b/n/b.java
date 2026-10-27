package b.n;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ClassesInfoCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b f2937a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<Class<?>, a> f2938b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<Class<?>, Boolean> f2939c = new HashMap();

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<Lifecycle.Event, List<C0053b>> f2940a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<C0053b, Lifecycle.Event> f2941b;

        public a(Map<C0053b, Lifecycle.Event> map) {
            this.f2941b = map;
            for (Map.Entry<C0053b, Lifecycle.Event> entry : map.entrySet()) {
                Lifecycle.Event value = entry.getValue();
                List<C0053b> arrayList = this.f2940a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f2940a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        public static void b(List<C0053b> list, g gVar, Lifecycle.Event event, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(gVar, event, obj);
                }
            }
        }

        public void a(g gVar, Lifecycle.Event event, Object obj) {
            b(this.f2940a.get(event), gVar, event, obj);
            b(this.f2940a.get(Lifecycle.Event.ON_ANY), gVar, event, obj);
        }
    }

    /* JADX INFO: renamed from: b.n.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    public static final class C0053b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2942a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f2943b;

        public C0053b(int i2, Method method) {
            this.f2942a = i2;
            this.f2943b = method;
            method.setAccessible(true);
        }

        public void a(g gVar, Lifecycle.Event event, Object obj) {
            try {
                int i2 = this.f2942a;
                if (i2 == 0) {
                    this.f2943b.invoke(obj, new Object[0]);
                } else if (i2 == 1) {
                    this.f2943b.invoke(obj, gVar);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    this.f2943b.invoke(obj, gVar, event);
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
            if (!(obj instanceof C0053b)) {
                return false;
            }
            C0053b c0053b = (C0053b) obj;
            return this.f2942a == c0053b.f2942a && this.f2943b.getName().equals(c0053b.f2943b.getName());
        }

        public int hashCode() {
            return (this.f2942a * 31) + this.f2943b.getName().hashCode();
        }
    }

    public final a a(Class<?> cls, Method[] methodArr) {
        int i2;
        a aVarC;
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (aVarC = c(superclass)) != null) {
            map.putAll(aVarC.f2941b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<C0053b, Lifecycle.Event> entry : c(cls2).f2941b.entrySet()) {
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
                Lifecycle.Event eventValue = nVar.value();
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
                e(map, new C0053b(i2, method), eventValue, cls);
                z = true;
            }
        }
        a aVar = new a(map);
        this.f2938b.put(cls, aVar);
        this.f2939c.put(cls, Boolean.valueOf(z));
        return aVar;
    }

    public final Method[] b(Class<?> cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    public a c(Class<?> cls) {
        a aVar = this.f2938b.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }

    public boolean d(Class<?> cls) {
        Boolean bool = this.f2939c.get(cls);
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
        this.f2939c.put(cls, Boolean.FALSE);
        return false;
    }

    public final void e(Map<C0053b, Lifecycle.Event> map, C0053b c0053b, Lifecycle.Event event, Class<?> cls) {
        Lifecycle.Event event2 = map.get(c0053b);
        if (event2 == null || event == event2) {
            if (event2 == null) {
                map.put(c0053b, event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + c0053b.f2943b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }
}
