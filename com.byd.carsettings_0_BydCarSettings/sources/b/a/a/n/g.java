package b.a.a.n;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: JavaBeanInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<?> f448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<?> f449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Constructor<?> f450c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Constructor<?> f451d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f452e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Method f453f;
    public final int g;
    public final c[] h;
    public final c[] i;
    public final int j;
    public final b.a.a.h.d k;
    public final String l;
    public final String m;
    public String[] n;
    public Type[] o;
    public String[] p;
    public boolean q;
    public Constructor<?> r;

    public g(Class<?> cls, Class<?> cls2, Constructor<?> constructor, Constructor<?> constructor2, Method method, Method method2, b.a.a.h.d dVar, List<c> list) {
        b.a.a.h.b bVar;
        this.f448a = cls;
        this.f449b = cls2;
        this.f450c = constructor;
        this.f451d = constructor2;
        this.f452e = method;
        this.j = l.e0(cls);
        this.f453f = method2;
        this.k = dVar;
        if (dVar != null) {
            String strTypeName = dVar.typeName();
            String strTypeKey = dVar.typeKey();
            this.m = strTypeKey.length() <= 0 ? null : strTypeKey;
            if (strTypeName.length() != 0) {
                this.l = strTypeName;
            } else {
                this.l = cls.getName();
            }
            String[] strArrOrders = dVar.orders();
            this.n = strArrOrders.length == 0 ? null : strArrOrders;
        } else {
            this.l = cls.getName();
            this.m = null;
            this.n = null;
        }
        c[] cVarArr = new c[list.size()];
        this.h = cVarArr;
        list.toArray(cVarArr);
        c[] cVarArr2 = new c[cVarArr.length];
        boolean z = false;
        if (this.n != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (c cVar : cVarArr) {
                linkedHashMap.put(cVar.f429a, cVar);
            }
            int i = 0;
            for (String str : this.n) {
                c cVar2 = (c) linkedHashMap.get(str);
                if (cVar2 != null) {
                    cVarArr2[i] = cVar2;
                    linkedHashMap.remove(str);
                    i++;
                }
            }
            Iterator it = linkedHashMap.values().iterator();
            while (it.hasNext()) {
                cVarArr2[i] = (c) it.next();
                i++;
            }
        } else {
            System.arraycopy(cVarArr, 0, cVarArr2, 0, cVarArr.length);
            Arrays.sort(cVarArr2);
        }
        this.i = Arrays.equals(this.h, cVarArr2) ? this.h : cVarArr2;
        if (constructor != null) {
            this.g = constructor.getParameterTypes().length;
        } else if (method != null) {
            this.g = method.getParameterTypes().length;
        } else {
            this.g = 0;
        }
        if (constructor2 != null) {
            this.o = constructor2.getParameterTypes();
            boolean zS0 = l.s0(cls);
            this.q = zS0;
            if (!zS0) {
                if (this.o.length == this.h.length) {
                    int i2 = 0;
                    while (true) {
                        Type[] typeArr = this.o;
                        if (i2 >= typeArr.length) {
                            z = true;
                            break;
                        } else if (typeArr[i2] != this.h[i2].f433e) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                if (z) {
                    return;
                }
                this.p = b.f(constructor2);
                return;
            }
            this.p = l.Z(cls);
            try {
                this.r = cls.getConstructor(new Class[0]);
            } catch (Throwable unused) {
            }
            Annotation[][] annotationArrC0 = l.c0(constructor2);
            for (int i3 = 0; i3 < this.p.length && i3 < annotationArrC0.length; i3++) {
                Annotation[] annotationArr = annotationArrC0[i3];
                int length = annotationArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        bVar = null;
                        break;
                    }
                    Annotation annotation = annotationArr[i4];
                    if (annotation instanceof b.a.a.h.b) {
                        bVar = (b.a.a.h.b) annotation;
                        break;
                    }
                    i4++;
                }
                if (bVar != null) {
                    String strName = bVar.name();
                    if (strName.length() > 0) {
                        this.p[i3] = strName;
                    }
                }
            }
        }
    }

    public static boolean a(List<c> list, c cVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            c cVar2 = list.get(size);
            if (cVar2.f429a.equals(cVar.f429a) && (!cVar2.h || cVar.h)) {
                if (cVar2.f433e.isAssignableFrom(cVar.f433e)) {
                    list.set(size, cVar);
                    return true;
                }
                if (cVar2.compareTo(cVar) >= 0) {
                    return false;
                }
                list.set(size, cVar);
                return true;
            }
        }
        list.add(cVar);
        return true;
    }

    public static g b(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy) {
        return c(cls, type, propertyNamingStrategy, false, l.f463b, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:174:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x068a A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x074d A[PHI: r23 r29 r31 r32
  0x074d: PHI (r23v10 java.lang.Class<?>) = (r23v7 java.lang.Class<?>), (r23v13 java.lang.Class<?>) binds: [B:321:0x074b, B:315:0x06fa] A[DONT_GENERATE, DONT_INLINE]
  0x074d: PHI (r29v7 java.lang.Class<java.lang.String>) = (r29v4 java.lang.Class<java.lang.String>), (r29v10 java.lang.Class<java.lang.String>) binds: [B:321:0x074b, B:315:0x06fa] A[DONT_GENERATE, DONT_INLINE]
  0x074d: PHI (r31v5 int) = (r31v2 int), (r31v8 int) binds: [B:321:0x074b, B:315:0x06fa] A[DONT_GENERATE, DONT_INLINE]
  0x074d: PHI (r32v5 int) = (r32v2 int), (r32v8 int) binds: [B:321:0x074b, B:315:0x06fa] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:325:0x075b  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x08f5  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0927  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x09a2  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x09b4  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0ad0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static b.a.a.n.g c(java.lang.Class<?> r51, java.lang.reflect.Type r52, com.alibaba.fastjson.PropertyNamingStrategy r53, boolean r54, boolean r55, boolean r56) {
        /*
            Method dump skipped, instruction units count: 2949
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.n.g.c(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, boolean, boolean, boolean):b.a.a.n.g");
    }

    public static Map<TypeVariable, Type> d(Class<?> cls) {
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap map = null;
        if (superclass == null) {
            return null;
        }
        while (true) {
            Class<? super Object> cls2 = superclass;
            Class<?> cls3 = cls;
            cls = cls2;
            if (cls == null || cls == Object.class) {
                break;
            }
            if (cls3.getGenericSuperclass() instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) cls3.getGenericSuperclass()).getActualTypeArguments();
                TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    if (map.containsKey(actualTypeArguments[i])) {
                        map.put(typeParameters[i], map.get(actualTypeArguments[i]));
                    } else {
                        map.put(typeParameters[i], actualTypeArguments[i]);
                    }
                }
            }
            superclass = cls.getSuperclass();
        }
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void e(java.lang.Class<?> r20, java.lang.reflect.Type r21, com.alibaba.fastjson.PropertyNamingStrategy r22, java.util.List<b.a.a.n.c> r23, java.lang.reflect.Field[] r24) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.n.g.e(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, java.util.List, java.lang.reflect.Field[]):void");
    }

    public static Class<?> f(Class<?> cls, b.a.a.h.d dVar) {
        Class<?> clsBuilder;
        if (cls != null && cls.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
            return l.z0("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
        }
        if (dVar == null || (clsBuilder = dVar.builder()) == Void.class) {
            return null;
        }
        return clsBuilder;
    }

    public static Constructor<?> g(Constructor[] constructorArr) {
        boolean z;
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            if (((b.a.a.h.a) constructor2.getAnnotation(b.a.a.h.a.class)) != null) {
                if (constructor != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                constructor = constructor2;
            }
        }
        if (constructor != null) {
            return constructor;
        }
        for (Constructor constructor3 : constructorArr) {
            Annotation[][] annotationArrC0 = l.c0(constructor3);
            if (annotationArrC0.length != 0) {
                int length = annotationArrC0.length;
                int i = 0;
                while (true) {
                    z = true;
                    if (i >= length) {
                        break;
                    }
                    Annotation[] annotationArr = annotationArrC0[i];
                    int length2 = annotationArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            z = false;
                            break;
                        }
                        if (annotationArr[i2] instanceof b.a.a.h.b) {
                            break;
                        }
                        i2++;
                    }
                    if (!z) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    continue;
                } else {
                    if (constructor != null) {
                        throw new JSONException("multi-JSONCreator");
                    }
                    constructor = constructor3;
                }
            }
        }
        if (constructor != null) {
        }
        return constructor;
    }

    public static Constructor<?> h(Class<?> cls, Constructor<?>[] constructorArr) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        int length = constructorArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor<?> constructor2 = constructorArr[i];
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
            i++;
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Class<?>[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Method i(Class<?> cls, Method[] methodArr, boolean z) {
        Method method = null;
        for (Method method2 : methodArr) {
            if (Modifier.isStatic(method2.getModifiers()) && cls.isAssignableFrom(method2.getReturnType()) && ((b.a.a.h.a) l.O(method2, b.a.a.h.a.class)) != null) {
                if (method != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                method = method2;
            }
        }
        if (method != null || !z) {
            return method;
        }
        for (Method method3 : methodArr) {
            if (l.r0(method3)) {
                return method3;
            }
        }
        return method;
    }

    public static c j(List<c> list, String str) {
        for (c cVar : list) {
            if (cVar.f429a.equals(str)) {
                return cVar;
            }
            Field field = cVar.f431c;
            if (field != null && cVar.d() != null && field.getName().equals(str)) {
                return cVar;
            }
        }
        return null;
    }
}
