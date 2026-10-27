package com.byd.mvcadapter.refect;

import android.util.Log;
import c.a.a.a.a.b;
import c.a.a.a.a.e.a;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class RefUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<String, Field> f3688a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap<String, Method> f3689b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final HashMap<String, Constructor<?>> f3690c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final WeakHashMap<Object, HashMap<String, Object>> f3691d = new WeakHashMap<>();

    public static class ClassNotFoundError extends Error {
        private static final long serialVersionUID = -1070936889459514628L;

        public ClassNotFoundError(Throwable th) {
            super(th);
        }

        public ClassNotFoundError(String str, Throwable th) {
            super(str, th);
        }
    }

    public static class InvocationTargetError extends Error {
        private static final long serialVersionUID = -1070936889459514628L;

        public InvocationTargetError(Throwable th) {
            super(th);
        }

        public InvocationTargetError(String str, Throwable th) {
            super(str, th);
        }
    }

    public static Object a(Object obj, String str, Object... objArr) {
        try {
            return m(obj.getClass(), str, objArr).invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            s(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (InvocationTargetException e4) {
            throw new InvocationTargetError(e4.getCause());
        }
    }

    public static Object b(Class<?> cls, String str, Object... objArr) {
        try {
            return m(cls, str, objArr).invoke(null, objArr);
        } catch (IllegalAccessException e2) {
            s(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (InvocationTargetException e4) {
            throw new InvocationTargetError(e4.getCause());
        }
    }

    public static Class<?> c(String str, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        try {
            return b.b(classLoader, str, false);
        } catch (ClassNotFoundException e2) {
            throw new ClassNotFoundError(e2);
        }
    }

    public static Constructor<?> d(Class<?> cls, Class<?>... clsArr) {
        String str = cls.getName() + q(clsArr) + "#bestmatch";
        HashMap<String, Constructor<?>> map = f3690c;
        if (map.containsKey(str)) {
            Constructor<?> constructor = map.get(str);
            if (constructor != null) {
                return constructor;
            }
            throw new NoSuchMethodError(str);
        }
        try {
            Constructor<?> constructorG = g(cls, clsArr);
            map.put(str, constructorG);
            return constructorG;
        } catch (NoSuchMethodError unused) {
            Constructor<?> constructor2 = null;
            for (Constructor<?> constructor3 : cls.getDeclaredConstructors()) {
                if (b.g(clsArr, constructor3.getParameterTypes(), true) && (constructor2 == null || a.a(constructor3.getParameterTypes(), constructor2.getParameterTypes(), clsArr) < 0)) {
                    constructor2 = constructor3;
                }
            }
            if (constructor2 != null) {
                constructor2.setAccessible(true);
                f3690c.put(str, constructor2);
                return constructor2;
            }
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError(str);
            f3690c.put(str, null);
            throw noSuchMethodError;
        }
    }

    public static Constructor<?> e(Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        Class<?>[] clsArrP = null;
        for (int i = 0; i < clsArr.length; i++) {
            if (clsArr[i] == null) {
                if (clsArrP == null) {
                    clsArrP = p(objArr);
                }
                clsArr[i] = clsArrP[i];
            }
        }
        return d(cls, clsArr);
    }

    public static Constructor<?> f(Class<?> cls, Object... objArr) {
        return d(cls, p(objArr));
    }

    public static Constructor<?> g(Class<?> cls, Class<?>... clsArr) {
        String str = cls.getName() + q(clsArr) + "#exact";
        HashMap<String, Constructor<?>> map = f3690c;
        if (map.containsKey(str)) {
            Constructor<?> constructor = map.get(str);
            if (constructor != null) {
                return constructor;
            }
            throw new NoSuchMethodError(str);
        }
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            map.put(str, declaredConstructor);
            return declaredConstructor;
        } catch (NoSuchMethodException unused) {
            f3690c.put(str, null);
            throw new NoSuchMethodError(str);
        }
    }

    public static Field h(Class<?> cls, String str) {
        String str2 = cls.getName() + '#' + str;
        HashMap<String, Field> map = f3688a;
        if (map.containsKey(str2)) {
            Field field = map.get(str2);
            if (field != null) {
                return field;
            }
            throw new NoSuchFieldError(str2);
        }
        try {
            Field fieldI = i(cls, str);
            fieldI.setAccessible(true);
            map.put(str2, fieldI);
            return fieldI;
        } catch (NoSuchFieldException unused) {
            f3688a.put(str2, null);
            throw new NoSuchFieldError(str2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0019, code lost:
    
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.reflect.Field i(java.lang.Class<?> r2, java.lang.String r3) throws java.lang.NoSuchFieldException {
        /*
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch: java.lang.NoSuchFieldException -> L5
            return r2
        L5:
            r0 = move-exception
        L6:
            java.lang.Class r2 = r2.getSuperclass()
            if (r2 == 0) goto L19
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L19
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch: java.lang.NoSuchFieldException -> L6
            return r2
        L19:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.mvcadapter.refect.RefUtils.i(java.lang.Class, java.lang.String):java.lang.reflect.Field");
    }

    public static Method j(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + '#' + str + q(clsArr) + "#bestmatch";
        HashMap<String, Method> map = f3689b;
        if (map.containsKey(str2)) {
            return map.get(str2);
        }
        try {
            Method methodO = o(cls, str, clsArr);
            map.put(str2, methodO);
            return methodO;
        } catch (NoSuchMethodError unused) {
            Method method = null;
            for (Method method2 : cls.getDeclaredMethods()) {
                if (!method2.isSynthetic() && !Modifier.isPrivate(method2.getModifiers()) && method2.getName().equals(str) && b.g(clsArr, method2.getParameterTypes(), true) && (method == null || a.a(method2.getParameterTypes(), method.getParameterTypes(), clsArr) < 0)) {
                    method = method2;
                }
            }
            if (method == null) {
                f3689b.put(str2, null);
                return null;
            }
            method.setAccessible(true);
            f3689b.put(str2, method);
            return method;
        }
    }

    public static Method k(Class<?> cls, String str, Object... objArr) {
        return j(cls, str, p(objArr));
    }

    public static Method l(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + '#' + str + q(clsArr) + "#bestmatch";
        HashMap<String, Method> map = f3689b;
        if (map.containsKey(str2)) {
            Method method = map.get(str2);
            if (method != null) {
                return method;
            }
            throw new NoSuchMethodError(str2);
        }
        try {
            Method methodO = o(cls, str, clsArr);
            map.put(str2, methodO);
            return methodO;
        } catch (NoSuchMethodError unused) {
            Method method2 = null;
            boolean z = true;
            while (true) {
                for (Method method3 : cls.getDeclaredMethods()) {
                    if ((z || !Modifier.isPrivate(method3.getModifiers())) && method3.getName().equals(str) && b.g(clsArr, method3.getParameterTypes(), true) && (method2 == null || a.a(method3.getParameterTypes(), method2.getParameterTypes(), clsArr) < 0)) {
                        method2 = method3;
                    }
                }
                cls = cls.getSuperclass();
                if (cls == null) {
                    break;
                }
                z = false;
            }
            if (method2 != null) {
                method2.setAccessible(true);
                f3689b.put(str2, method2);
                return method2;
            }
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError(str2);
            f3689b.put(str2, null);
            throw noSuchMethodError;
        }
    }

    public static Method m(Class<?> cls, String str, Object... objArr) {
        return l(cls, str, p(objArr));
    }

    public static Method n(Class<?> cls, String str) {
        Method method;
        String str2 = cls.getName() + '#' + str + "#byName";
        HashMap<String, Method> map = f3689b;
        if (map.containsKey(str2)) {
            return map.get(str2);
        }
        int i = 0;
        try {
            Method methodO = o(cls, str, new Class[0]);
            map.put(str2, methodO);
            return methodO;
        } catch (NoSuchMethodError unused) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            int length = declaredMethods.length;
            while (true) {
                if (i >= length) {
                    method = null;
                    break;
                }
                method = declaredMethods[i];
                if (!method.isSynthetic() && !Modifier.isPrivate(method.getModifiers()) && method.getName().equals(str)) {
                    break;
                }
                i++;
            }
            if (method == null) {
                f3689b.put(str2, null);
                return null;
            }
            method.setAccessible(true);
            f3689b.put(str2, method);
            return method;
        }
    }

    public static Method o(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + '#' + str + q(clsArr) + "#exact";
        HashMap<String, Method> map = f3689b;
        if (map.containsKey(str2)) {
            Method method = map.get(str2);
            if (method != null) {
                return method;
            }
            throw new NoSuchMethodError(str2);
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            map.put(str2, declaredMethod);
            return declaredMethod;
        } catch (NoSuchMethodException unused) {
            f3689b.put(str2, null);
            throw new NoSuchMethodError(str2);
        }
    }

    public static Class<?>[] p(Object... objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i] != null ? objArr[i].getClass() : null;
        }
        return clsArr;
    }

    public static String q(Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder("(");
        boolean z = true;
        for (Class<?> cls : clsArr) {
            if (z) {
                z = false;
            } else {
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
            if (cls != null) {
                sb.append(cls.getCanonicalName());
            } else {
                sb.append("null");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static Object r(Class<?> cls, String str) {
        try {
            return h(cls, str).get(null);
        } catch (IllegalAccessException e2) {
            s(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        }
    }

    public static synchronized void s(Throwable th) {
        Log.i("RefUtils", Log.getStackTraceString(th));
    }

    public static Object t(Class<?> cls, Class<?>[] clsArr, Object... objArr) {
        try {
            return e(cls, clsArr, objArr).newInstance(objArr);
        } catch (IllegalAccessException e2) {
            s(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (InstantiationException e4) {
            throw new InstantiationError(e4.getMessage());
        } catch (InvocationTargetException e5) {
            throw new InvocationTargetError(e5.getCause());
        }
    }

    public static Object u(Class<?> cls, Object... objArr) {
        try {
            return f(cls, objArr).newInstance(objArr);
        } catch (IllegalAccessException e2) {
            s(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (InstantiationException e4) {
            throw new InstantiationError(e4.getMessage());
        } catch (InvocationTargetException e5) {
            throw new InvocationTargetError(e5.getCause());
        }
    }

    public static Object v(Class<?> cls) {
        try {
            return u(cls, new Object[0]);
        } catch (Throwable unused) {
            return a(r(c("sun.misc.Unsafe", null), "theUnsafe"), "allocateInstance", cls);
        }
    }
}
