package k0;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TraceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f5690a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f5691b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f5692c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f5693d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Method f5694e;

    static {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 18 || i8 >= 29) {
            return;
        }
        try {
            f5690a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
            Class cls = Long.TYPE;
            f5691b = Trace.class.getMethod("isTagEnabled", cls);
            Class cls2 = Integer.TYPE;
            f5692c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
            f5693d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
            f5694e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
        } catch (Exception e8) {
            Log.i("TraceCompat", "Unable to initialize via reflection.", e8);
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
