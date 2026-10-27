package b.g.g;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TraceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static long f2559a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f2560b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Method f2561c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f2562d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Method f2563e;

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 18 || i2 >= 29) {
            return;
        }
        try {
            f2559a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
            Class cls = Long.TYPE;
            f2560b = Trace.class.getMethod("isTagEnabled", cls);
            Class cls2 = Integer.TYPE;
            f2561c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
            f2562d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
            f2563e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
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
