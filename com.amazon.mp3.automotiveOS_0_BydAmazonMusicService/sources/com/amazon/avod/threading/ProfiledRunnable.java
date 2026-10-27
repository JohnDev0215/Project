package com.amazon.avod.threading;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ProfiledRunnable implements Runnable {
    private final Runnable mRunnable;
    private final boolean mShouldPerformTracing;
    private final String mTraceFormat;
    private final Profiler.TraceLevel mTraceLevel;
    private final Object[] mTraceObjs;

    public ProfiledRunnable(@Nonnull Runnable runnable, @Nonnull Profiler.TraceLevel traceLevel, @Nullable String str, @Nullable Object... objArr) {
        this(Profiler.isIsFileLoggingEnabled(), runnable, traceLevel, str, objArr);
    }

    @VisibleForTesting
    ProfiledRunnable(boolean z, @Nonnull Runnable runnable, @Nonnull Profiler.TraceLevel traceLevel, @Nullable String str, @Nullable Object... objArr) {
        this.mShouldPerformTracing = z;
        this.mRunnable = (Runnable) Preconditions.checkNotNull(runnable, "runnable");
        this.mTraceLevel = (Profiler.TraceLevel) Preconditions.checkNotNull(traceLevel, "traceLevel");
        this.mTraceFormat = (String) MoreObjects.firstNonNull(str, "");
        this.mTraceObjs = objArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.mShouldPerformTracing) {
            runWithTracing();
        } else {
            this.mRunnable.run();
        }
    }

    private void runWithTracing() {
        TraceKey traceKeyBeginTrace;
        Object[] objArr = this.mTraceObjs;
        if (objArr == null || objArr.length == 0) {
            traceKeyBeginTrace = Profiler.beginTrace(this.mTraceLevel, this.mTraceFormat);
        } else {
            traceKeyBeginTrace = Profiler.beginTrace(this.mTraceLevel, this.mTraceFormat, objArr);
        }
        try {
            this.mRunnable.run();
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("runnable", this.mRunnable).add(TracePublisherServiceMessageType.TRACE_MESSAGE_KEY, String.format(Locale.US, this.mTraceFormat, this.mTraceObjs)).toString();
    }
}
