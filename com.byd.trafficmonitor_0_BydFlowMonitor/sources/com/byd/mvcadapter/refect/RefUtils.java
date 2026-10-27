package com.byd.mvcadapter.refect;

import android.util.Log;
import com.bydux.api.common.tools.util.ListUtils;
import d.a.a.a.a.b;
import d.a.a.a.a.e.a;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class RefUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<String, Field> f4365a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap<String, Method> f4366b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final HashMap<String, Constructor<?>> f4367c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final WeakHashMap<Object, HashMap<String, Object>> f4368d = new WeakHashMap<>();

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
            return l(obj.getClass(), str, objArr).invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            q(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (InvocationTargetException e4) {
            throw new InvocationTargetError(e4.getCause());
        }
    }

    public static Class<?> b(String str, ClassLoader classLoader) throws ClassNotFoundException {
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        try {
            return b.b(classLoader, str, false);
        } catch (ClassNotFoundException e2) {
            throw new ClassNotFoundError(e2);
        }
    }

    public static Constructor<?> c(Class<?> cls, Class<?>... clsArr) {
        String str = cls.getName() + o(clsArr) + "#bestmatch";
        HashMap<String, Constructor<?>> map = f4367c;
        if (map.containsKey(str)) {
            Constructor<?> constructor = map.get(str);
            if (constructor != null) {
                return constructor;
            }
            throw new NoSuchMethodError(str);
        }
        try {
            Constructor<?> constructorF = f(cls, clsArr);
            map.put(str, constructorF);
            return constructorF;
        } catch (NoSuchMethodError unused) {
            Constructor<?> constructor2 = null;
            for (Constructor<?> constructor3 : cls.getDeclaredConstructors()) {
                if (b.g(clsArr, constructor3.getParameterTypes(), true) && (constructor2 == null || a.a(constructor3.getParameterTypes(), constructor2.getParameterTypes(), clsArr) < 0)) {
                    constructor2 = constructor3;
                }
            }
            if (constructor2 != null) {
                constructor2.setAccessible(true);
                f4367c.put(str, constructor2);
                return constructor2;
            }
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError(str);
            f4367c.put(str, null);
            throw noSuchMethodError;
        }
    }

    public static Constructor<?> d(Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        Class<?>[] clsArrN = null;
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (clsArr[i2] == null) {
                if (clsArrN == null) {
                    clsArrN = n(objArr);
                }
                clsArr[i2] = clsArrN[i2];
            }
        }
        return c(cls, clsArr);
    }

    public static Constructor<?> e(Class<?> cls, Object... objArr) {
        return c(cls, n(objArr));
    }

    public static Constructor<?> f(Class<?> cls, Class<?>... clsArr) {
        String str = cls.getName() + o(clsArr) + "#exact";
        HashMap<String, Constructor<?>> map = f4367c;
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
            f4367c.put(str, null);
            throw new NoSuchMethodError(str);
        }
    }

    public static Field g(Class<?> cls, String str) {
        String str2 = cls.getName() + '#' + str;
        HashMap<String, Field> map = f4365a;
        if (map.containsKey(str2)) {
            Field field = map.get(str2);
            if (field != null) {
                return field;
            }
            throw new NoSuchFieldError(str2);
        }
        try {
            Field fieldH = h(cls, str);
            fieldH.setAccessible(true);
            map.put(str2, fieldH);
            return fieldH;
        } catch (NoSuchFieldException unused) {
            f4365a.put(str2, null);
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
    public static java.lang.reflect.Field h(java.lang.Class<?> r2, java.lang.String r3) throws java.lang.NoSuchFieldException {
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
        throw new UnsupportedOperationException("Method not decompiled: com.byd.mvcadapter.refect.RefUtils.h(java.lang.Class, java.lang.String):java.lang.reflect.Field");
    }

    public static Method i(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + '#' + str + o(clsArr) + "#bestmatch";
        HashMap<String, Method> map = f4366b;
        if (map.containsKey(str2)) {
            return map.get(str2);
        }
        try {
            Method methodM = m(cls, str, clsArr);
            map.put(str2, methodM);
            return methodM;
        } catch (NoSuchMethodError unused) {
            Method method = null;
            for (Method method2 : cls.getDeclaredMethods()) {
                if (!method2.isSynthetic() && !Modifier.isPrivate(method2.getModifiers()) && method2.getName().equals(str) && b.g(clsArr, method2.getParameterTypes(), true) && (method == null || a.a(method2.getParameterTypes(), method.getParameterTypes(), clsArr) < 0)) {
                    method = method2;
                }
            }
            if (method == null) {
                f4366b.put(str2, null);
                return null;
            }
            method.setAccessible(true);
            f4366b.put(str2, method);
            return method;
        }
    }

    public static Method j(Class<?> cls, String str, Object... objArr) {
        return i(cls, str, n(objArr));
    }

    public static Method k(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + '#' + str + o(clsArr) + "#bestmatch";
        HashMap<String, Method> map = f4366b;
        if (map.containsKey(str2)) {
            Method method = map.get(str2);
            if (method != null) {
                return method;
            }
            throw new NoSuchMethodError(str2);
        }
        try {
            Method methodM = m(cls, str, clsArr);
            map.put(str2, methodM);
            return methodM;
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
                f4366b.put(str2, method2);
                return method2;
            }
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError(str2);
            f4366b.put(str2, null);
            throw noSuchMethodError;
        }
    }

    public static Method l(Class<?> cls, String str, Object... objArr) {
        return k(cls, str, n(objArr));
    }

    public static Method m(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + '#' + str + o(clsArr) + "#exact";
        HashMap<String, Method> map = f4366b;
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
            f4366b.put(str2, null);
            throw new NoSuchMethodError(str2);
        }
    }

    public static Class<?>[] n(Object... objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            clsArr[i2] = objArr[i2] != null ? objArr[i2].getClass() : null;
        }
        return clsArr;
    }

    public static String o(Class<?>... clsArr) {
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

    public static Object p(Class<?> cls, String str) {
        try {
            return g(cls, str).get(null);
        } catch (IllegalAccessException e2) {
            q(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        }
    }

    public static synchronized void q(Throwable th) {
        Log.i("RefUtils", Log.getStackTraceString(th));
    }

    public static Object r(Class<?> cls, Class<?>[] clsArr, Object... objArr) {
        try {
            return d(cls, clsArr, objArr).newInstance(objArr);
        } catch (IllegalAccessException e2) {
            q(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (InstantiationException e4) {
            throw new InstantiationError(e4.getMessage());
        } catch (InvocationTargetException e5) {
            throw new InvocationTargetError(e5.getCause());
        }
    }

    public static Object s(Class<?> cls, Object... objArr) {
        try {
            return e(cls, objArr).newInstance(objArr);
        } catch (IllegalAccessException e2) {
            q(e2);
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (InstantiationException e4) {
            throw new InstantiationError(e4.getMessage());
        } catch (InvocationTargetException e5) {
            throw new InvocationTargetError(e5.getCause());
        }
    }

    public static Object t(Class<?> cls) {
        try {
            return s(cls, new Object[0]);
        } catch (Throwable unused) {
            return a(p(b("sun.misc.Unsafe", null), "theUnsafe"), "allocateInstance", cls);
        }
    }
}
