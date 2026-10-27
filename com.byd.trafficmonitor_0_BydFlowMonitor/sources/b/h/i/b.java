package b.h.i;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TraceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f2477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f2478b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f2479c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f2480d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Method f2481e;

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 18 || i2 >= 29) {
            return;
        }
        try {
            f2477a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
            Class cls = Long.TYPE;
            f2478b = Trace.class.getMethod("isTagEnabled", cls);
            Class cls2 = Integer.TYPE;
            f2479c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
            f2480d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
            f2481e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
        } catch (Exception e2) {
            Log.i("TraceCompat", "Unable to initialize via reflection.", e2);
        }
    }

    public static void a(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void b() {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}
