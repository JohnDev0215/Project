package com.android.systemui.shared.recents.utilities;

import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public class AppTrace {
    public static void start(String key, int cookie) {
        Trace.asyncTraceBegin(4096L, key, cookie);
    }

    public static void start(String key) {
        Trace.asyncTraceBegin(4096L, key, 0);
    }

    public static void end(String key) {
        Trace.asyncTraceEnd(4096L, key, 0);
    }

    public static void end(String key, int cookie) {
        Trace.asyncTraceEnd(4096L, key, cookie);
    }

    public static void beginSection(String key) {
        Trace.beginSection(key);
    }

    public static void endSection() {
        Trace.endSection();
    }

    public static void count(String name, int count) {
        Trace.traceCounter(4096L, name, count);
    }
}
