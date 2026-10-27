package b.g.g;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TraceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static long f1705a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f1706b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Method f1707c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f1708d;
    private static Method e;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i < 18 || i >= 29) {
            return;
        }
        try {
            f1705a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
            Class cls = Long.TYPE;
            f1706b = Trace.class.getMethod("isTagEnabled", cls);
            Class cls2 = Integer.TYPE;
            f1707c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
            f1708d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
            e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
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
