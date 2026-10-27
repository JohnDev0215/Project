package com.amazon.avod.perf.internal;

/* JADX INFO: loaded from: classes2.dex */
public interface PerfSettings {
    int getMemoryBufferMaxSize();

    int getTraceLevel();

    boolean isLazyFormattingEnabled();

    boolean isPerfLoggingEnabled();
}
