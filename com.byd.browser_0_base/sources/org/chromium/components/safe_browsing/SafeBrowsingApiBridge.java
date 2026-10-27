package org.chromium.components.safe_browsing;

import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class SafeBrowsingApiBridge {
    public static final Object a = new Object();
    public static boolean b;

    public static void a() {
        if (b) {
            return;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("SafeBrowsingApiBridge.initHandler", null);
        if (traceEventH0 != null) {
            traceEventH0.close();
        }
        b = true;
    }

    @CalledByNative
    public static boolean ensureInitialized() {
        synchronized (a) {
            a();
        }
        return false;
    }

    @CalledByNative
    public static boolean startAllowlistLookup(String str, int i) {
        synchronized (a) {
            TraceEvent traceEventH0 = TraceEvent.H0("SafeBrowsingApiBridge.startAllowlistLookup", null);
            try {
                a();
                throw null;
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
    }

    @CalledByNative
    public static void startUriLookup(long j, String str, int[] iArr) {
        synchronized (a) {
            TraceEvent traceEventH0 = TraceEvent.H0("SafeBrowsingApiBridge.startUriLookup", null);
            try {
                a();
                throw null;
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
    }
}
