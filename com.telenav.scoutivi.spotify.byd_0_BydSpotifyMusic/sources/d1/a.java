package d1;

import android.annotation.SuppressLint;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: Trace.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static long f5615a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f5616b;

    public static void a(String str) {
        b.a(str);
    }

    public static void b() {
        b.b();
    }

    private static void c(String str, Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof RuntimeException)) {
                throw new RuntimeException(cause);
            }
            throw ((RuntimeException) cause);
        }
        Log.v("Trace", "Unable to call " + str + " via reflection", exc);
    }

    @SuppressLint({"NewApi"})
    public static boolean d() {
        try {
            if (f5616b == null) {
                return Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        return e();
    }

    private static boolean e() {
        try {
            if (f5616b == null) {
                f5615a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                f5616b = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) f5616b.invoke(null, Long.valueOf(f5615a))).booleanValue();
        } catch (Exception e7) {
            c("isTagEnabled", e7);
            return false;
        }
    }
}
