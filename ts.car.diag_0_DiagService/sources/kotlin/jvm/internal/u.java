package kotlin.jvm.internal;

import kotlin.jvm.b.w;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class u {
    public static Object a(Object obj, int i) {
        if (obj == null || c(obj, i)) {
            return obj;
        }
        f(obj, "kotlin.jvm.functions.Function" + i);
        throw null;
    }

    public static int b(Object obj) {
        if (obj instanceof p) {
            return ((p) obj).getArity();
        }
        if (obj instanceof kotlin.jvm.b.a) {
            return 0;
        }
        if (obj instanceof kotlin.jvm.b.l) {
            return 1;
        }
        if (obj instanceof kotlin.jvm.b.p) {
            return 2;
        }
        if (obj instanceof kotlin.jvm.b.q) {
            return 3;
        }
        if (obj instanceof kotlin.jvm.b.r) {
            return 4;
        }
        if (obj instanceof kotlin.jvm.b.s) {
            return 5;
        }
        if (obj instanceof kotlin.jvm.b.t) {
            return 6;
        }
        if (obj instanceof kotlin.jvm.b.u) {
            return 7;
        }
        if (obj instanceof kotlin.jvm.b.v) {
            return 8;
        }
        if (obj instanceof w) {
            return 9;
        }
        if (obj instanceof kotlin.jvm.b.b) {
            return 10;
        }
        if (obj instanceof kotlin.jvm.b.c) {
            return 11;
        }
        if (obj instanceof kotlin.jvm.b.d) {
            return 12;
        }
        if (obj instanceof kotlin.jvm.b.e) {
            return 13;
        }
        if (obj instanceof kotlin.jvm.b.f) {
            return 14;
        }
        if (obj instanceof kotlin.jvm.b.g) {
            return 15;
        }
        if (obj instanceof kotlin.jvm.b.h) {
            return 16;
        }
        if (obj instanceof kotlin.jvm.b.i) {
            return 17;
        }
        if (obj instanceof kotlin.jvm.b.j) {
            return 18;
        }
        if (obj instanceof kotlin.jvm.b.k) {
            return 19;
        }
        if (obj instanceof kotlin.jvm.b.m) {
            return 20;
        }
        if (obj instanceof kotlin.jvm.b.n) {
            return 21;
        }
        return obj instanceof kotlin.jvm.b.o ? 22 : -1;
    }

    public static boolean c(Object obj, int i) {
        return (obj instanceof kotlin.a) && b(obj) == i;
    }

    private static <T extends Throwable> T d(T t) {
        q.g(t, u.class.getName());
        return t;
    }

    public static ClassCastException e(ClassCastException classCastException) {
        d(classCastException);
        throw classCastException;
    }

    public static void f(Object obj, String str) {
        g((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
        throw null;
    }

    public static void g(String str) {
        e(new ClassCastException(str));
        throw null;
    }
}
