package com.amazon.identity.auth.device.utils;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.aa;
import com.amazon.identity.auth.device.c6;
import com.amazon.identity.auth.device.ga;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ReflectionHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, c6<Class<?>>> f618a = new ConcurrentHashMap();
    public static final Map<a, c6<Method>> b = new ConcurrentHashMap();
    public static final String c = ReflectionHelper.class.getName();

    /* JADX INFO: compiled from: DCP */
    public static class CannotCallMethodException extends Exception {
        private static final long serialVersionUID = 1;

        public CannotCallMethodException(String str) {
            super(str);
        }

        public CannotCallMethodException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Class f619a;
        public final String b;
        public final Class[] c;

        public a(Class<?> cls, String str, Class... clsArr) {
            this.f619a = cls;
            this.b = str;
            this.c = clsArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return TextUtils.equals(this.b, aVar.b) && Arrays.equals(this.c, aVar.c) && aa.a(this.f619a, aVar.f619a);
        }

        public int hashCode() {
            Class cls = this.f619a;
            int iHashCode = ((cls == null ? 0 : cls.hashCode()) + 31) * 31;
            String str = this.b;
            return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Arrays.hashCode(this.c);
        }
    }

    public Object a(String str, String str2, Class[] clsArr, Object... objArr) throws CannotCallMethodException {
        Class<?> cls = null;
        if (!TextUtils.isEmpty(str2)) {
            c6<Class<?>> c6Var = f618a.get(str2);
            if (c6Var == null) {
                try {
                    cls = Class.forName(str2);
                } catch (ClassNotFoundException unused) {
                    Log.w(ga.a(c), String.format("Failed to locate class named %s. If this is not a Kindle device, you can ignore this warning.", str2));
                }
                c6Var = new c6<>(cls);
                f618a.put(str2, c6Var);
            }
            cls = c6Var.f261a;
        }
        Class<?> cls2 = cls;
        if (cls2 != null) {
            return a(str, cls2, null, clsArr, objArr);
        }
        String str3 = "Cannot find class " + str2;
        Log.e(ga.a(c), str3);
        throw new CannotCallMethodException(str3);
    }

    public final Object a(String str, Class<?> cls, Object obj, Class[] clsArr, Object... objArr) throws CannotCallMethodException {
        try {
            Method methodA = a(cls, str, clsArr);
            if (methodA != null) {
                return methodA.invoke(obj, objArr);
            }
            throw new CannotCallMethodException(String.format("Method %s cannot be found or accessed!", str));
        } catch (IllegalAccessException e) {
            throw new CannotCallMethodException("IllegalAccessException calling method", e);
        } catch (IllegalArgumentException e2) {
            throw new CannotCallMethodException("IllegalArguemntException calling method", e2);
        } catch (SecurityException e3) {
            throw new CannotCallMethodException(e3.getMessage(), e3);
        } catch (InvocationTargetException e4) {
            Log.e(ga.a(c), String.format("Exception thrown while calling method %s", str), e4.getCause());
            throw new CannotCallMethodException("Exception calling method", e4);
        }
    }

    public final Object a(Class<?> cls, Object obj, String str) throws CannotCallMethodException {
        try {
            return cls.getField(str).get(obj);
        } catch (IllegalAccessException e) {
            throw new CannotCallMethodException("Cannot get field because of IllegalAccessException", e);
        } catch (IllegalArgumentException e2) {
            throw new CannotCallMethodException("Cannot get field because of IllegalArgumentException", e2);
        } catch (NoSuchFieldException e3) {
            throw new CannotCallMethodException(String.format("Field %s cannot be found", str), e3);
        } catch (SecurityException e4) {
            throw new CannotCallMethodException("Cannot get field because of a security exception", e4);
        }
    }

    public final Method a(Class<?> cls, String str, Class[] clsArr) {
        a aVar = new a(cls, str, clsArr);
        c6<Method> c6Var = b.get(aVar);
        if (c6Var == null) {
            Method method = null;
            try {
                method = cls.getMethod(str, clsArr);
            } catch (IllegalArgumentException unused) {
                Log.e(ga.a(c), "IllegalArguemntException calling method");
            } catch (NoSuchMethodException unused2) {
                Log.e(ga.a(c), "Method cannot be found. Are you sure it is public?");
            } catch (SecurityException e) {
                Log.e(ga.a(c), "Security Exception! Error: " + e.getMessage());
            }
            c6<Method> c6Var2 = new c6<>(method);
            b.put(aVar, c6Var2);
            c6Var = c6Var2;
        }
        return c6Var.f261a;
    }
}
