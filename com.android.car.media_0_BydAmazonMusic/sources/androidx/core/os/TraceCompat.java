package androidx.core.os;

import android.os.Trace;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class TraceCompat {
    private static final String TAG = "TraceCompat";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    private TraceCompat() {
    }

    public static void beginAsyncSection(@NonNull String str, int i) {
        Trace.beginAsyncSection(str, i);
    }

    public static void beginSection(@NonNull String str) {
        Trace.beginSection(str);
    }

    public static void endAsyncSection(@NonNull String str, int i) {
        Trace.endAsyncSection(str, i);
    }

    public static void endSection() {
        Trace.endSection();
    }

    public static boolean isEnabled() {
        return Trace.isEnabled();
    }

    public static void setCounter(@NonNull String str, int i) {
        Trace.setCounter(str, i);
    }
}
