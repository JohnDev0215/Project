package androidx.lifecycle;

import androidx.lifecycle.i;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ClassesInfoCache.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static c f2536c = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Class<?>, a> f2537a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class<?>, Boolean> f2538b = new HashMap();

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Map<i.b, List<b>> f2539a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Map<b, i.b> f2540b;

        a(Map<b, i.b> map) {
            this.f2540b = map;
            for (Map.Entry<b, i.b> entry : map.entrySet()) {
                i.b value = entry.getValue();
                List<b> arrayList = this.f2539a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f2539a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        private static void b(List<b> list, o oVar, i.b bVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    list.get(size).a(oVar, bVar, obj);
                }
            }
        }

        void a(o oVar, i.b bVar, Object obj) {
            b(this.f2539a.get(bVar), oVar, bVar, obj);
            b(this.f2539a.get(i.b.ON_ANY), oVar, bVar, obj);
        }
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f2541a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Method f2542b;

        b(int i7, Method method) {
            this.f2541a = i7;
            this.f2542b = method;
            method.setAccessible(true);
        }

        void a(o oVar, i.b bVar, Object obj) {
            try {
                int i7 = this.f2541a;
                if (i7 == 0) {
                    this.f2542b.invoke(obj, new Object[0]);
                } else if (i7 == 1) {
                    this.f2542b.invoke(obj, oVar);
                } else {
                    if (i7 != 2) {
                        return;
                    }
                    this.f2542b.invoke(obj, oVar, bVar);
                }
            } catch (IllegalAccessException e7) {
                throw new RuntimeException(e7);
            } catch (InvocationTargetException e8) {
                throw new RuntimeException("Failed to call observer method", e8.getCause());
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
            return this.f2541a == bVar.f2541a && this.f2542b.getName().equals(bVar.f2542b.getName());
        }

        public int hashCode() {
            return (this.f2541a * 31) + this.f2542b.getName().hashCode();
        }
    }

    c() {
    }

    private a a(Class<?> cls, Method[] methodArr) {
        int i7;
        a aVarC;
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (aVarC = c(superclass)) != null) {
            map.putAll(aVarC.f2540b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, i.b> entry : c(cls2).f2540b.entrySet()) {
                e(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z6 = false;
        for (Method method : methodArr) {
            v vVar = (v) method.getAnnotation(v.class);
            if (vVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i7 = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(o.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i7 = 1;
                }
                i.b bVarValue = vVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(i.b.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (bVarValue != i.b.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i7 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(map, new b(i7, method), bVarValue, cls);
                z6 = true;
            }
        }
        a aVar = new a(map);
        this.f2537a.put(cls, aVar);
        this.f2538b.put(cls, Boolean.valueOf(z6));
        return aVar;
    }

    private Method[] b(Class<?> cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e7) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e7);
        }
    }

    private void e(Map<b, i.b> map, b bVar, i.b bVar2, Class<?> cls) {
        i.b bVar3 = map.get(bVar);
        if (bVar3 == null || bVar2 == bVar3) {
            if (bVar3 == null) {
                map.put(bVar, bVar2);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f2542b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + bVar3 + ", new value " + bVar2);
    }

    a c(Class<?> cls) {
        a aVar = this.f2537a.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }

    boolean d(Class<?> cls) {
        Boolean bool = this.f2538b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] methodArrB = b(cls);
        for (Method method : methodArrB) {
            if (((v) method.getAnnotation(v.class)) != null) {
                a(cls, methodArrB);
                return true;
            }
        }
        this.f2538b.put(cls, Boolean.FALSE);
        return false;
    }
}
