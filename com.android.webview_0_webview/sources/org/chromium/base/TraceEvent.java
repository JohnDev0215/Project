package org.chromium.base;

import J.N;
import defpackage.AbstractC0907ht;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TraceEvent implements AutoCloseable {
    public static volatile boolean t;
    public static volatile boolean u;
    public final String s;

    public TraceEvent(String str, String str2) {
        this.s = str;
        x(str, str2);
    }

    public static void A(String str) {
        EarlyTraceEvent.c(str);
        if (t) {
            N.Mw73xTww(str, null);
        }
    }

    public static void G(String str) {
        if (t) {
            N.ML40H8ed(str, null);
        }
    }

    public static void I(String str, String str2) {
        if (t) {
            N.ML40H8ed(str, str2);
        }
    }

    public static TraceEvent M(String str) {
        if (EarlyTraceEvent.b() || t) {
            return new TraceEvent(str, null);
        }
        return null;
    }

    public static void setEnabled(boolean z) {
        if (z) {
            synchronized (EarlyTraceEvent.a) {
                if (EarlyTraceEvent.b()) {
                    EarlyTraceEvent.b = 2;
                    EarlyTraceEvent.f();
                }
            }
        }
        if (t != z) {
            t = z;
            if (u) {
                return;
            }
            ThreadUtils.d().setMessageLogging(z ? AbstractC0907ht.a : null);
        }
    }

    public static void x(String str, String str2) {
        EarlyTraceEvent.a(str);
        if (t) {
            N.M9XfPu17(str, str2);
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        A(this.s);
    }
}
