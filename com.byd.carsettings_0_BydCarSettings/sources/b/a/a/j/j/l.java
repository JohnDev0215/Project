package b.a.a.j.j;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: FieldDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.a.a.n.c f282a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<?> f283b;

    public l(Class<?> cls, b.a.a.n.c cVar) {
        this.f283b = cls;
        this.f282a = cVar;
    }

    public static void a(Field field, Method method, Object obj, Object obj2) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (d(field, obj, obj2)) {
            return;
        }
        obj.getClass().getDeclaredMethod("set" + method.getName().substring(3), method.getReturnType()).invoke(obj, obj2);
    }

    public static boolean d(Field field, Object obj, Object obj2) throws IllegalAccessException {
        if (field == null || Modifier.isFinal(field.getModifiers())) {
            return false;
        }
        field.set(obj, obj2);
        return true;
    }

    public int b() {
        return 0;
    }

    public abstract void c(b.a.a.j.a aVar, Object obj, Type type, Map<String, Object> map);

    public void e(Object obj, int i) {
        g(obj, Integer.valueOf(i));
    }

    public void f(Object obj, long j) {
        g(obj, Long.valueOf(j));
    }

    public void g(Object obj, Object obj2) {
        b.a.a.n.c cVar;
        Method method;
        String str;
        if (obj2 == null && this.f282a.f433e.isPrimitive()) {
            return;
        }
        b.a.a.n.c cVar2 = this.f282a;
        if (cVar2.f433e == String.class && (str = cVar2.t) != null && str.equals("trim")) {
            obj2 = ((String) obj2).trim();
        }
        try {
            cVar = this.f282a;
            method = cVar.f430b;
        } catch (Exception e2) {
            throw new JSONException("set property error, " + this.f283b.getName() + "#" + this.f282a.f429a, e2);
        }
        if (method == null) {
            Field field = cVar.f431c;
            if (!cVar.h) {
                if (field != null) {
                    field.set(obj, obj2);
                    return;
                }
                return;
            }
            Class<?> cls = cVar.f433e;
            if (cls == AtomicInteger.class) {
                AtomicInteger atomicInteger = (AtomicInteger) field.get(obj);
                if (atomicInteger != null) {
                    atomicInteger.set(((AtomicInteger) obj2).get());
                    return;
                }
                return;
            }
            if (cls == AtomicLong.class) {
                AtomicLong atomicLong = (AtomicLong) field.get(obj);
                if (atomicLong != null) {
                    atomicLong.set(((AtomicLong) obj2).get());
                    return;
                }
                return;
            }
            if (cls == AtomicBoolean.class) {
                AtomicBoolean atomicBoolean = (AtomicBoolean) field.get(obj);
                if (atomicBoolean != null) {
                    atomicBoolean.set(((AtomicBoolean) obj2).get());
                    return;
                }
                return;
            }
            if (Map.class.isAssignableFrom(cls)) {
                Map map = (Map) field.get(obj);
                if (map != null && map != Collections.emptyMap() && !map.getClass().getName().startsWith("java.util.Collections$Unmodifiable")) {
                    map.putAll((Map) obj2);
                    return;
                }
                return;
            }
            Collection collection = (Collection) field.get(obj);
            if (collection != null && obj2 != null && collection != Collections.emptySet() && collection != Collections.emptyList() && !collection.getClass().getName().startsWith("java.util.Collections$Unmodifiable")) {
                collection.clear();
                collection.addAll((Collection) obj2);
                return;
            }
            return;
        }
        if (!cVar.h) {
            method.invoke(obj, obj2);
            return;
        }
        Class<?> cls2 = cVar.f433e;
        if (cls2 == AtomicInteger.class) {
            AtomicInteger atomicInteger2 = (AtomicInteger) method.invoke(obj, new Object[0]);
            if (atomicInteger2 != null) {
                atomicInteger2.set(((AtomicInteger) obj2).get());
                return;
            } else {
                a(this.f282a.f431c, method, obj, obj2);
                return;
            }
        }
        if (cls2 == AtomicLong.class) {
            AtomicLong atomicLong2 = (AtomicLong) method.invoke(obj, new Object[0]);
            if (atomicLong2 != null) {
                atomicLong2.set(((AtomicLong) obj2).get());
                return;
            } else {
                a(this.f282a.f431c, method, obj, obj2);
                return;
            }
        }
        if (cls2 == AtomicBoolean.class) {
            AtomicBoolean atomicBoolean2 = (AtomicBoolean) method.invoke(obj, new Object[0]);
            if (atomicBoolean2 != null) {
                atomicBoolean2.set(((AtomicBoolean) obj2).get());
                return;
            } else {
                a(this.f282a.f431c, method, obj, obj2);
                return;
            }
        }
        if (Map.class.isAssignableFrom(method.getReturnType())) {
            try {
                Map map2 = (Map) method.invoke(obj, new Object[0]);
                if (map2 == null) {
                    if (obj2 != null) {
                        a(this.f282a.f431c, method, obj, obj2);
                        return;
                    }
                    return;
                } else {
                    if (map2 == Collections.emptyMap()) {
                        return;
                    }
                    if (map2.isEmpty() && ((Map) obj2).isEmpty()) {
                        return;
                    }
                    String name = map2.getClass().getName();
                    if (!name.equals("java.util.ImmutableCollections$Map1") && !name.equals("java.util.ImmutableCollections$MapN") && !name.startsWith("java.util.Collections$Unmodifiable")) {
                        if (map2.getClass().getName().equals("kotlin.collections.EmptyMap")) {
                            a(this.f282a.f431c, method, obj, obj2);
                            return;
                        } else {
                            map2.putAll((Map) obj2);
                            return;
                        }
                    }
                    return;
                }
            } catch (InvocationTargetException unused) {
                a(this.f282a.f431c, method, obj, obj2);
                return;
            }
        }
        try {
            Collection collection2 = (Collection) method.invoke(obj, new Object[0]);
            if (collection2 == null || obj2 == null) {
                if (collection2 != null || obj2 == null) {
                    return;
                }
                a(this.f282a.f431c, method, obj, obj2);
                return;
            }
            String name2 = collection2.getClass().getName();
            if (collection2 != Collections.emptySet() && collection2 != Collections.emptyList() && name2 != "java.util.ImmutableCollections$ListN" && name2 != "java.util.ImmutableCollections$List12" && !name2.startsWith("java.util.Collections$Unmodifiable")) {
                if (!collection2.isEmpty()) {
                    collection2.clear();
                } else if (((Collection) obj2).isEmpty()) {
                    return;
                }
                if (!name2.equals("kotlin.collections.EmptyList") && !name2.equals("kotlin.collections.EmptySet")) {
                    collection2.addAll((Collection) obj2);
                    return;
                }
                a(this.f282a.f431c, method, obj, obj2);
                return;
            }
            return;
        } catch (InvocationTargetException unused2) {
            a(this.f282a.f431c, method, obj, obj2);
            return;
        }
        throw new JSONException("set property error, " + this.f283b.getName() + "#" + this.f282a.f429a, e2);
    }

    public void h(Object obj, String str) {
        g(obj, str);
    }

    public void i(Object obj, boolean z) {
        g(obj, Boolean.valueOf(z));
    }
}
