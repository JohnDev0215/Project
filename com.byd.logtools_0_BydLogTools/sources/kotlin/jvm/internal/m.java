package kotlin.jvm.internal;

import java.util.List;

/* JADX INFO: compiled from: TypeIntrinsics.java */
/* JADX INFO: loaded from: classes.dex */
public class m {
    public static List a(Object obj) {
        if (obj instanceof kotlin.jvm.internal.n.a) {
            e(obj, "kotlin.collections.MutableList");
        }
        return b(obj);
    }

    public static List b(Object obj) {
        try {
            return (List) obj;
        } catch (ClassCastException e2) {
            throw d(e2);
        }
    }

    private static <T extends Throwable> T c(T t) {
        return (T) i.e(t, m.class.getName());
    }

    public static ClassCastException d(ClassCastException classCastException) {
        throw ((ClassCastException) c(classCastException));
    }

    public static void e(Object obj, String str) {
        f((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
    }

    public static void f(String str) {
        throw d(new ClassCastException(str));
    }
}
