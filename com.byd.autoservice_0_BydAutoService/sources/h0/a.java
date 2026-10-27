package h0;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TraceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f2950a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f2951b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f2952c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f2953d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Method f2954e;

    static {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 18 || i3 >= 29) {
            return;
        }
        try {
            f2950a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
            Class cls = Long.TYPE;
            f2951b = Trace.class.getMethod("isTagEnabled", cls);
            Class cls2 = Integer.TYPE;
            f2952c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
            f2953d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
            f2954e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
        } catch (Exception e3) {
            Log.i("TraceCompat", "Unable to initialize via reflection.", e3);
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
