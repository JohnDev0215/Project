package defpackage;

import android.content.Context;
import org.chromium.base.BundleUtils;

/* JADX INFO: compiled from: Module.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hi1<T> {
    public final String a;
    public final Class<T> b;
    public final String c;
    public T d;
    public gq0 e;
    public boolean f;

    public hi1(String str, Class<T> cls, String str2) {
        this.a = str;
        this.b = cls;
        this.c = str2;
    }

    public final T a() {
        Class<T> cls = this.b;
        String str = this.a;
        va3 va3Var = new va3();
        try {
            T t = this.d;
            if (t != null) {
                return t;
            }
            Object obj = BundleUtils.a;
            String str2 = this.c;
            Context contextA = xz.a;
            if (BundleUtils.d(str)) {
                contextA = BundleUtils.a(contextA, str);
            }
            try {
                ku2 ku2VarT = ku2.t();
                try {
                    Object objNewInstance = contextA.getClassLoader().loadClass(str2).newInstance();
                    ku2VarT.close();
                    try {
                        T tCast = cls.cast(objNewInstance);
                        this.d = tCast;
                        va3Var.close();
                        return tCast;
                    } catch (ClassCastException e) {
                        ClassLoader classLoader = cls.getClassLoader();
                        ClassLoader classLoader2 = objNewInstance.getClass().getClassLoader();
                        throw new uv("Failure casting " + str + " module class, interface ClassLoader: " + classLoader + " (parent " + classLoader.getParent() + "), impl ClassLoader: " + classLoader2 + " (parent " + classLoader2.getParent() + "), equal: " + classLoader.equals(classLoader2) + " (parents equal: " + classLoader.getParent().equals(classLoader2.getParent()) + ")", e);
                    }
                } catch (Throwable th) {
                    try {
                        ku2VarT.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            } catch (Exception e2) {
                throw new uv(e2);
            }
        } finally {
            try {
                va3Var.close();
            } catch (Throwable unused2) {
            }
        }
    }

    public final o01 b() {
        if (this.e == null) {
            va3 va3Var = new va3();
            try {
                this.e = new gq0(this.c);
                va3Var.close();
            } catch (Throwable th) {
                try {
                    va3Var.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        return this.e;
    }

    public final void c(p01 p01Var) {
        va3 va3Var = new va3();
        try {
            b().b(this.a, p01Var);
            va3Var.close();
        } catch (Throwable th) {
            try {
                va3Var.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final boolean d() {
        va3 va3Var = new va3();
        try {
            boolean zD = b().d(this.a);
            va3Var.close();
            return zD;
        } catch (Throwable th) {
            try {
                va3Var.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
