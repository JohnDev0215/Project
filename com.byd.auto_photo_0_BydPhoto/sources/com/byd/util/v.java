package com.byd.util;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile v f3008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<?> f3009b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Method f3010c;
    private Method d;
    private Method e;

    private v() {
        try {
            this.f3009b = Class.forName("android.os.SystemProperties");
        } catch (Exception e) {
            e.printStackTrace();
            w.c("SystemProperties not found!!!");
        }
        Class<?> cls = this.f3009b;
        if (cls == null) {
            return;
        }
        try {
            Method method = cls.getMethod("get", String.class);
            this.d = method;
            method.setAccessible(true);
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("SystemProperties init getInt method exception : " + e2.getMessage());
        }
        try {
            Method method2 = this.f3009b.getMethod("get", String.class, String.class);
            this.e = method2;
            method2.setAccessible(true);
        } catch (Exception e3) {
            e3.printStackTrace();
            w.c("SystemProperties init get(def) method exception : " + e3.getMessage());
        }
        try {
            Method method3 = this.f3009b.getMethod("getInt", String.class, Integer.TYPE);
            this.f3010c = method3;
            method3.setAccessible(true);
        } catch (Exception e4) {
            e4.printStackTrace();
            w.c("SystemProperties init getInt method exception : " + e4.getMessage());
        }
    }

    public static v d() {
        if (f3008a == null) {
            synchronized (v.class) {
                if (f3008a == null) {
                    f3008a = new v();
                }
            }
        }
        return f3008a;
    }

    public String a(String str) {
        Method method = this.d;
        if (method == null) {
            return "";
        }
        try {
            Object objInvoke = method.invoke(this.f3009b, str);
            if (objInvoke == null) {
                return null;
            }
            return String.valueOf(objInvoke);
        } catch (Exception e) {
            e.printStackTrace();
            w.c("SystemProperties get exception : " + e.getMessage());
            return "";
        }
    }

    public String b(String str, String str2) {
        Method method = this.e;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(this.f3009b, str, str2);
                if (objInvoke == null) {
                    return null;
                }
                return String.valueOf(objInvoke);
            } catch (Exception e) {
                e.printStackTrace();
                w.c("SystemProperties get(def) exception : " + e.getMessage());
            }
        }
        return str2;
    }

    public boolean c(String str, boolean z) {
        Class<?> cls = this.f3009b;
        if (cls != null) {
            try {
                Method method = cls.getMethod("getBoolean", String.class, Boolean.TYPE);
                method.setAccessible(true);
                Object objInvoke = method.invoke(this.f3009b, str, Boolean.valueOf(z));
                if (objInvoke instanceof Boolean) {
                    return ((Boolean) objInvoke).booleanValue();
                }
            } catch (Exception e) {
                e.printStackTrace();
                w.c("SystemProperties getBoolean exception : " + e.getMessage());
            }
        }
        return z;
    }

    public int e(String str, int i) {
        Method method = this.f3010c;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(this.f3009b, str, Integer.valueOf(i));
                if (objInvoke instanceof Integer) {
                    return ((Integer) objInvoke).intValue();
                }
            } catch (Exception e) {
                e.printStackTrace();
                w.c("SystemProperties getInt exception : " + e.getMessage());
            }
        }
        return i;
    }
}
