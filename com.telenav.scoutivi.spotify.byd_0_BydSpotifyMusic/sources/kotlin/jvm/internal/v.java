package kotlin.jvm.internal;

import java.util.List;
import r5.w;

/* JADX INFO: compiled from: TypeIntrinsics.java */
/* JADX INFO: loaded from: classes.dex */
public class v {
    public static List a(Object obj) {
        if ((obj instanceof s5.a) && !(obj instanceof s5.b)) {
            h(obj, "kotlin.collections.MutableList");
        }
        return c(obj);
    }

    public static Object b(Object obj, int i7) {
        if (obj != null && !e(obj, i7)) {
            h(obj, "kotlin.jvm.functions.Function" + i7);
        }
        return obj;
    }

    public static List c(Object obj) {
        try {
            return (List) obj;
        } catch (ClassCastException e7) {
            throw g(e7);
        }
    }

    public static int d(Object obj) {
        if (obj instanceof h) {
            return ((h) obj).getArity();
        }
        if (obj instanceof r5.a) {
            return 0;
        }
        if (obj instanceof r5.l) {
            return 1;
        }
        if (obj instanceof r5.p) {
            return 2;
        }
        if (obj instanceof r5.q) {
            return 3;
        }
        if (obj instanceof r5.r) {
            return 4;
        }
        if (obj instanceof r5.s) {
            return 5;
        }
        if (obj instanceof r5.t) {
            return 6;
        }
        if (obj instanceof r5.u) {
            return 7;
        }
        if (obj instanceof r5.v) {
            return 8;
        }
        if (obj instanceof w) {
            return 9;
        }
        if (obj instanceof r5.b) {
            return 10;
        }
        if (obj instanceof r5.c) {
            return 11;
        }
        if (obj instanceof r5.d) {
            return 12;
        }
        if (obj instanceof r5.e) {
            return 13;
        }
        if (obj instanceof r5.f) {
            return 14;
        }
        if (obj instanceof r5.g) {
            return 15;
        }
        if (obj instanceof r5.h) {
            return 16;
        }
        if (obj instanceof r5.i) {
            return 17;
        }
        if (obj instanceof r5.j) {
            return 18;
        }
        if (obj instanceof r5.k) {
            return 19;
        }
        if (obj instanceof r5.m) {
            return 20;
        }
        if (obj instanceof r5.n) {
            return 21;
        }
        return obj instanceof r5.o ? 22 : -1;
    }

    public static boolean e(Object obj, int i7) {
        return (obj instanceof g5.c) && d(obj) == i7;
    }

    private static <T extends Throwable> T f(T t6) {
        return (T) l.m(t6, v.class.getName());
    }

    public static ClassCastException g(ClassCastException classCastException) {
        throw ((ClassCastException) f(classCastException));
    }

    public static void h(Object obj, String str) {
        i((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
    }

    public static void i(String str) {
        throw g(new ClassCastException(str));
    }
}
