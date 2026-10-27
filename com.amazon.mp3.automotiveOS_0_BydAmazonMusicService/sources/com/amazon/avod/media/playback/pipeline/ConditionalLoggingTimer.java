package com.amazon.avod.media.playback.pipeline;

import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class ConditionalLoggingTimer {
    private final Stopwatch mStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());
    private String mTag = null;

    public static ConditionalLoggingTimer create() {
        return new ConditionalLoggingTimer();
    }

    private ConditionalLoggingTimer() {
    }

    public void setTag(@Nullable String str) {
        this.mTag = str;
    }

    public void start() {
        if (this.mStopwatch.isRunning()) {
            this.mStopwatch.stop();
        }
        this.mStopwatch.reset().start();
    }

    public void stop(@Nonnull String str) {
        stop(str, TimeUnit.MILLISECONDS);
    }

    public void stop(@Nonnull String str, @Nonnull TimeUnit timeUnit) {
        if (this.mStopwatch.isRunning()) {
            DLog.logf("Operation %s took duration %s (unit %s, tag %s)", Preconditions.checkNotNull(str, "traceName"), Long.valueOf(this.mStopwatch.stop().elapsed(timeUnit)), timeUnit, this.mTag);
        }
    }

    public void stop(@Nonnull String str, long j) {
        stop(str, j, TimeUnit.MILLISECONDS);
    }

    public void stop(@Nonnull String str, long j, TimeUnit timeUnit) {
        if (this.mStopwatch.isRunning()) {
            long jElapsed = this.mStopwatch.stop().elapsed(timeUnit);
            if (jElapsed > j) {
                DLog.logf("Operation %s exceeded threshold (expected %s, actual %s, unit %s, tag %s)", Preconditions.checkNotNull(str, "traceName"), Long.valueOf(j), Long.valueOf(jElapsed), timeUnit, this.mTag);
            }
        }
    }
}
