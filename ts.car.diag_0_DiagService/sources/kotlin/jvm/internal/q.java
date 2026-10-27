package kotlin.jvm.internal;

import java.util.Arrays;
import kotlin.KotlinNullPointerException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class q {
    private q() {
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static void b(Object obj, String str) {
        if (obj != null) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException(str + " must not be null");
        f(illegalStateException);
        throw illegalStateException;
    }

    public static void c(Object obj, String str) {
        if (obj != null) {
            return;
        }
        i(str);
        throw null;
    }

    public static int d(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static void e(int i, String str) {
        j();
        throw null;
    }

    private static <T extends Throwable> T f(T t) {
        g(t, q.class.getName());
        return t;
    }

    static <T extends Throwable> T g(T t, String str) {
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

    public static void h() {
        KotlinNullPointerException kotlinNullPointerException = new KotlinNullPointerException();
        f(kotlinNullPointerException);
        throw kotlinNullPointerException;
    }

    private static void i(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
        f(illegalArgumentException);
        throw illegalArgumentException;
    }

    public static void j() {
        k("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
        throw null;
    }

    public static void k(String str) {
        throw new UnsupportedOperationException(str);
    }
}
