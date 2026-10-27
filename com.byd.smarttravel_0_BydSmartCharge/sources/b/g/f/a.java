package b.g.f;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TraceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f1008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f1009b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f1010c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f1011d;
    public static Method e;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i < 18 || i >= 29) {
            return;
        }
        try {
            f1008a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
            Class cls = Long.TYPE;
            f1009b = Trace.class.getMethod("isTagEnabled", cls);
            Class cls2 = Integer.TYPE;
            f1010c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
            f1011d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
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
