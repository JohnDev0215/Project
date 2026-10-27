package kotlin.jvm.internal;

import java.util.Arrays;
import kotlin.KotlinNullPointerException;
import kotlin.UninitializedPropertyAccessException;

/* JADX INFO: compiled from: Intrinsics.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    private i() {
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static void b(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((IllegalStateException) d(new IllegalStateException(str + " must not be null")));
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            h(str);
        }
    }

    private static <T extends Throwable> T d(T t) {
        return (T) e(t, i.class.getName());
    }

    static <T extends Throwable> T e(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }

    public static String f(String str, Object obj) {
        return str + obj;
    }

    public static void g() {
        throw ((KotlinNullPointerException) d(new KotlinNullPointerException()));
    }

    private static void h(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        throw ((IllegalArgumentException) d(new IllegalArgumentException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str)));
    }

    public static void i(String str) {
        throw ((UninitializedPropertyAccessException) d(new UninitializedPropertyAccessException(str)));
    }

    public static void j(String str) {
        i("lateinit property " + str + " has not been initialized");
    }
}
