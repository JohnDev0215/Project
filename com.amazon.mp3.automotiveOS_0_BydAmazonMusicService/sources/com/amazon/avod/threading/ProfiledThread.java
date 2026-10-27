package com.amazon.avod.threading;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ProfiledThread extends Thread {
    private final boolean mShouldPerformTracing;
    private final Profiler.TraceLevel mTraceLevel;

    public ProfiledThread(@Nonnull Runnable runnable, @Nonnull String str) {
        this(runnable, str, Profiler.TraceLevel.INFO);
    }

    public ProfiledThread(@Nonnull Runnable runnable, @Nonnull String str, @Nonnull Profiler.TraceLevel traceLevel) {
        this(runnable, str, traceLevel, Profiler.isIsFileLoggingEnabled());
    }

    @VisibleForTesting
    ProfiledThread(@Nonnull Runnable runnable, @Nonnull String str, @Nonnull Profiler.TraceLevel traceLevel, boolean z) {
        super(runnable, str);
        this.mTraceLevel = (Profiler.TraceLevel) Preconditions.checkNotNull(traceLevel);
        this.mShouldPerformTracing = z;
    }

    public static void startFor(@Nonnull Runnable runnable, @Nonnull String str) {
        Preconditions.checkNotNull(runnable, "runnable");
        Preconditions.checkNotNull(str, ContextMappingConstants.LABEL);
        new ProfiledThread(runnable, str).start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (this.mShouldPerformTracing) {
            runWithTracing();
        } else {
            super.run();
        }
    }

    private void runWithTracing() {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(this.mTraceLevel, getName());
        try {
            super.run();
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }
}
