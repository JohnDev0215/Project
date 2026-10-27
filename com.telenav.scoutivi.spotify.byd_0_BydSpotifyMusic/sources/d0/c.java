package d0;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TraceCompat.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static long f5610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f5611b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Method f5612c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f5613d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Method f5614e;

    static {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                f5610a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                Class cls = Long.TYPE;
                f5611b = Trace.class.getMethod("isTagEnabled", cls);
                Class cls2 = Integer.TYPE;
                f5612c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
                f5613d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
                f5614e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
            } catch (Exception e7) {
                Log.i("TraceCompat", "Unable to initialize via reflection.", e7);
            }
        }
    }

    public static void a(String str) {
        Trace.beginSection(str);
    }

    public static void b() {
        Trace.endSection();
    }
}
