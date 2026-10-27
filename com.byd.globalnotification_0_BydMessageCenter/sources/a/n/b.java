package a.n;

import a.n.g;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static b f1078c = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<Class<?>, a> f1079a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<Class<?>, Boolean> f1080b = new HashMap();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<g.a, List<C0023b>> f1081a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<C0023b, g.a> f1082b;

        public a(Map<C0023b, g.a> map) {
            this.f1082b = map;
            for (Map.Entry<C0023b, g.a> entry : map.entrySet()) {
                g.a value = entry.getValue();
                List<C0023b> arrayList = this.f1081a.get(value);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.f1081a.put(value, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        public static void a(List<C0023b> list, k kVar, g.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    C0023b c0023b = list.get(size);
                    if (c0023b == null) {
                        throw null;
                    }
                    try {
                        int i = c0023b.f1083a;
                        if (i == 0) {
                            c0023b.f1084b.invoke(obj, new Object[0]);
                        } else if (i == 1) {
                            c0023b.f1084b.invoke(obj, kVar);
                        } else if (i == 2) {
                            c0023b.f1084b.invoke(obj, kVar, aVar);
                        }
                    } catch (IllegalAccessException e2) {
                        throw new RuntimeException(e2);
                    } catch (InvocationTargetException e3) {
                        throw new RuntimeException("Failed to call observer method", e3.getCause());
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a.n.b$b, reason: collision with other inner class name */
    public static class C0023b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1083a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f1084b;

        public C0023b(int i, Method method) {
            this.f1083a = i;
            this.f1084b = method;
            method.setAccessible(true);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || C0023b.class != obj.getClass()) {
                return false;
            }
            C0023b c0023b = (C0023b) obj;
            return this.f1083a == c0023b.f1083a && this.f1084b.getName().equals(c0023b.f1084b.getName());
        }

        public int hashCode() {
            return this.f1084b.getName().hashCode() + (this.f1083a * 31);
        }
    }

    public final a a(Class<?> cls, Method[] methodArr) {
        int i;
        a aVarB;
        Class<?> superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (aVarB = b(superclass)) != null) {
            map.putAll(aVarB.f1082b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<C0023b, g.a> entry : b(cls2).f1082b.entrySet()) {
                c(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e2) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
            }
        }
        boolean z = false;
        for (Method method : methodArr) {
            r rVar = (r) method.getAnnotation(r.class);
            if (rVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(k.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                g.a aVarValue = rVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(g.a.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (aVarValue != g.a.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                c(map, new C0023b(i, method), aVarValue, cls);
                z = true;
            }
        }
        a aVar = new a(map);
        this.f1079a.put(cls, aVar);
        this.f1080b.put(cls, Boolean.valueOf(z));
        return aVar;
    }

    public a b(Class<?> cls) {
        a aVar = this.f1079a.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }

    public final void c(Map<C0023b, g.a> map, C0023b c0023b, g.a aVar, Class<?> cls) {
        g.a aVar2 = map.get(c0023b);
        if (aVar2 == null || aVar == aVar2) {
            if (aVar2 == null) {
                map.put(c0023b, aVar);
                return;
            }
            return;
        }
        Method method = c0023b.f1084b;
        StringBuilder sbD = b.a.a.a.a.d("Method ");
        sbD.append(method.getName());
        sbD.append(" in ");
        sbD.append(cls.getName());
        sbD.append(" already declared with different @OnLifecycleEvent value: previous value ");
        sbD.append(aVar2);
        sbD.append(", new value ");
        sbD.append(aVar);
        throw new IllegalArgumentException(sbD.toString());
    }
}
