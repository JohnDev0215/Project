package l2;

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
    public final Class<?> f6215a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class<?> f6216b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Constructor<?> f6217c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Constructor<?> f6218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f6219e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Method f6220f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f6221g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final c[] f6222h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c[] f6223i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f6224j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final d2.d f6225k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f6226l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f6227m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String[] f6228n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Type[] f6229o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String[] f6230p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6231q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Constructor<?> f6232r;

    public g(Class<?> cls, Class<?> cls2, Constructor<?> constructor, Constructor<?> constructor2, Method method, Method method2, d2.d dVar, List<c> list) {
        d2.b bVar;
        this.f6215a = cls;
        this.f6216b = cls2;
        this.f6217c = constructor;
        this.f6218d = constructor2;
        this.f6219e = method;
        this.f6224j = l.e0(cls);
        this.f6220f = method2;
        this.f6225k = dVar;
        if (dVar != null) {
            String strTypeName = dVar.typeName();
            String strTypeKey = dVar.typeKey();
            this.f6227m = strTypeKey.length() <= 0 ? null : strTypeKey;
            if (strTypeName.length() != 0) {
                this.f6226l = strTypeName;
            } else {
                this.f6226l = cls.getName();
            }
            String[] strArrOrders = dVar.orders();
            this.f6228n = strArrOrders.length == 0 ? null : strArrOrders;
        } else {
            this.f6226l = cls.getName();
            this.f6227m = null;
            this.f6228n = null;
        }
        c[] cVarArr = new c[list.size()];
        this.f6222h = cVarArr;
        list.toArray(cVarArr);
        c[] cVarArr2 = new c[cVarArr.length];
        boolean z7 = false;
        if (this.f6228n != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (c cVar : cVarArr) {
                linkedHashMap.put(cVar.f6169d, cVar);
            }
            int i8 = 0;
            for (String str : this.f6228n) {
                c cVar2 = (c) linkedHashMap.get(str);
                if (cVar2 != null) {
                    cVarArr2[i8] = cVar2;
                    linkedHashMap.remove(str);
                    i8++;
                }
            }
            Iterator it = linkedHashMap.values().iterator();
            while (it.hasNext()) {
                cVarArr2[i8] = (c) it.next();
                i8++;
            }
        } else {
            System.arraycopy(cVarArr, 0, cVarArr2, 0, cVarArr.length);
            Arrays.sort(cVarArr2);
        }
        this.f6223i = Arrays.equals(this.f6222h, cVarArr2) ? this.f6222h : cVarArr2;
        if (constructor != null) {
            this.f6221g = constructor.getParameterTypes().length;
        } else if (method != null) {
            this.f6221g = method.getParameterTypes().length;
        } else {
            this.f6221g = 0;
        }
        if (constructor2 != null) {
            this.f6229o = constructor2.getParameterTypes();
            boolean zS0 = l.s0(cls);
            this.f6231q = zS0;
            if (!zS0) {
                if (this.f6229o.length == this.f6222h.length) {
                    int i9 = 0;
                    while (true) {
                        Type[] typeArr = this.f6229o;
                        if (i9 >= typeArr.length) {
                            z7 = true;
                            break;
                        } else if (typeArr[i9] != this.f6222h[i9].f6173h) {
                            break;
                        } else {
                            i9++;
                        }
                    }
                }
                if (z7) {
                    return;
                }
                this.f6230p = b.f(constructor2);
                return;
            }
            this.f6230p = l.Z(cls);
            try {
                this.f6232r = cls.getConstructor(new Class[0]);
            } catch (Throwable unused) {
            }
            Annotation[][] annotationArrC0 = l.c0(constructor2);
            for (int i10 = 0; i10 < this.f6230p.length && i10 < annotationArrC0.length; i10++) {
                Annotation[] annotationArr = annotationArrC0[i10];
                int length = annotationArr.length;
                int i11 = 0;
                while (true) {
                    if (i11 >= length) {
                        bVar = null;
                        break;
                    }
                    Annotation annotation = annotationArr[i11];
                    if (annotation instanceof d2.b) {
                        bVar = (d2.b) annotation;
                        break;
                    }
                    i11++;
                }
                if (bVar != null) {
                    String strName = bVar.name();
                    if (strName.length() > 0) {
                        this.f6230p[i10] = strName;
                    }
                }
            }
        }
    }

    public static boolean a(List<c> list, c cVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            c cVar2 = list.get(size);
            if (cVar2.f6169d.equals(cVar.f6169d) && (!cVar2.f6176k || cVar.f6176k)) {
                if (cVar2.f6173h.isAssignableFrom(cVar.f6173h)) {
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
        return c(cls, type, propertyNamingStrategy, false, l.f6242b, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:174:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0689 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x074c A[PHI: r23 r29 r31 r32
  0x074c: PHI (r23v10 java.lang.Class<?>) = (r23v7 java.lang.Class<?>), (r23v13 java.lang.Class<?>) binds: [B:321:0x074a, B:315:0x06f9] A[DONT_GENERATE, DONT_INLINE]
  0x074c: PHI (r29v7 java.lang.Class<java.lang.String>) = (r29v4 java.lang.Class<java.lang.String>), (r29v10 java.lang.Class<java.lang.String>) binds: [B:321:0x074a, B:315:0x06f9] A[DONT_GENERATE, DONT_INLINE]
  0x074c: PHI (r31v5 int) = (r31v2 int), (r31v8 int) binds: [B:321:0x074a, B:315:0x06f9] A[DONT_GENERATE, DONT_INLINE]
  0x074c: PHI (r32v5 int) = (r32v2 int), (r32v8 int) binds: [B:321:0x074a, B:315:0x06f9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:325:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x08f4  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0926  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x09a1  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x09b3  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0acf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static l2.g c(java.lang.Class<?> r51, java.lang.reflect.Type r52, com.alibaba.fastjson.PropertyNamingStrategy r53, boolean r54, boolean r55, boolean r56) {
        /*
            Method dump skipped, instruction units count: 2948
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.g.c(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, boolean, boolean, boolean):l2.g");
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
                for (int i8 = 0; i8 < actualTypeArguments.length; i8++) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    if (map.containsKey(actualTypeArguments[i8])) {
                        map.put(typeParameters[i8], map.get(actualTypeArguments[i8]));
                    } else {
                        map.put(typeParameters[i8], actualTypeArguments[i8]);
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
    public static void e(java.lang.Class<?> r20, java.lang.reflect.Type r21, com.alibaba.fastjson.PropertyNamingStrategy r22, java.util.List<l2.c> r23, java.lang.reflect.Field[] r24) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.g.e(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, java.util.List, java.lang.reflect.Field[]):void");
    }

    public static Class<?> f(Class<?> cls, d2.d dVar) {
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
        boolean z7;
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            if (((d2.a) constructor2.getAnnotation(d2.a.class)) != null) {
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
                int i8 = 0;
                while (true) {
                    z7 = true;
                    if (i8 >= length) {
                        break;
                    }
                    Annotation[] annotationArr = annotationArrC0[i8];
                    int length2 = annotationArr.length;
                    int i9 = 0;
                    while (true) {
                        if (i9 >= length2) {
                            z7 = false;
                            break;
                        }
                        if (annotationArr[i9] instanceof d2.b) {
                            break;
                        }
                        i9++;
                    }
                    if (!z7) {
                        z7 = false;
                        break;
                    }
                    i8++;
                }
                if (!z7) {
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
        int i8 = 0;
        while (true) {
            if (i8 >= length) {
                break;
            }
            Constructor<?> constructor2 = constructorArr[i8];
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
            i8++;
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

    public static Method i(Class<?> cls, Method[] methodArr, boolean z7) {
        Method method = null;
        for (Method method2 : methodArr) {
            if (Modifier.isStatic(method2.getModifiers()) && cls.isAssignableFrom(method2.getReturnType()) && ((d2.a) l.O(method2, d2.a.class)) != null) {
                if (method != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                method = method2;
            }
        }
        if (method != null || !z7) {
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
            if (cVar.f6169d.equals(str)) {
                return cVar;
            }
            Field field = cVar.f6171f;
            if (field != null && cVar.d() != null && field.getName().equals(str)) {
                return cVar;
            }
        }
        return null;
    }
}
