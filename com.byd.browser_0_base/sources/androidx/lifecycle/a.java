package androidx.lifecycle;

import androidx.lifecycle.d;
import defpackage.b71;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ClassesInfoCache.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class a {
    public static final a c = new a();
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();

    /* JADX INFO: renamed from: androidx.lifecycle.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClassesInfoCache.java */
    @Deprecated
    public static class C0010a {
        public final HashMap a = new HashMap();
        public final Map<b, d.a> b;

        public C0010a(HashMap map) {
            this.b = map;
            for (Map.Entry entry : map.entrySet()) {
                d.a aVar = (d.a) entry.getValue();
                List arrayList = (List) this.a.get(aVar);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.a.put(aVar, arrayList);
                }
                arrayList.add((b) entry.getKey());
            }
        }

        public static void a(List<b> list, b71 b71Var, d.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    b bVar = list.get(size);
                    bVar.getClass();
                    try {
                        int i = bVar.a;
                        Method method = bVar.b;
                        if (i == 0) {
                            method.invoke(obj, new Object[0]);
                        } else if (i == 1) {
                            method.invoke(obj, b71Var);
                        } else if (i == 2) {
                            method.invoke(obj, b71Var, aVar);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException("Failed to call observer method", e2.getCause());
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: ClassesInfoCache.java */
    @Deprecated
    public static final class b {
        public final int a;
        public final Method b;

        public b(int i, Method method) {
            this.a = i;
            this.b = method;
            method.setAccessible(true);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a == bVar.a && this.b.getName().equals(bVar.b.getName());
        }

        public final int hashCode() {
            return this.b.getName().hashCode() + (this.a * 31);
        }
    }

    public static void c(HashMap map, b bVar, d.a aVar, Class cls) {
        d.a aVar2 = (d.a) map.get(bVar);
        if (aVar2 == null || aVar == aVar2) {
            if (aVar2 == null) {
                map.put(bVar, aVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
    }

    public final C0010a a(Class<?> cls, Method[] methodArr) {
        int i;
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null) {
            map.putAll(b(superclass).b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry<b, d.a> entry : b(cls2).b.entrySet()) {
                c(map, entry.getKey(), entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
            }
        }
        boolean z = false;
        for (Method method : methodArr) {
            h hVar = (h) method.getAnnotation(h.class);
            if (hVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!b71.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                d.a aVarValue = hVar.value();
                if (parameterTypes.length > 1) {
                    if (!d.a.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (aVarValue != d.a.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                c(map, new b(i, method), aVarValue, cls);
                z = true;
            }
        }
        C0010a c0010a = new C0010a(map);
        this.a.put(cls, c0010a);
        this.b.put(cls, Boolean.valueOf(z));
        return c0010a;
    }

    public final C0010a b(Class<?> cls) {
        C0010a c0010a = (C0010a) this.a.get(cls);
        return c0010a != null ? c0010a : a(cls, null);
    }
}
