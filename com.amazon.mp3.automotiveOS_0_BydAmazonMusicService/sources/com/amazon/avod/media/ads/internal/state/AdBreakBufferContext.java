package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.threading.Tickers;
import com.google.common.base.Stopwatch;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class AdBreakBufferContext {
    private int mCurrentBreakBuffers = 0;
    private Stopwatch mBufferStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());

    private void resetBufferStopwatch() {
        this.mBufferStopwatch.reset();
    }

    private void clearCurrentBreakBuffers() {
        this.mCurrentBreakBuffers = 0;
    }

    public void incrementCurrentBreakBuffersCount() {
        this.mCurrentBreakBuffers++;
    }

    public int getCurrentBreakBuffersCount() {
        return this.mCurrentBreakBuffers;
    }

    public void startBufferStopwatch() {
        if (this.mBufferStopwatch.isRunning()) {
            return;
        }
        this.mBufferStopwatch.start();
    }

    public void stopBufferStopwatch() {
        if (this.mBufferStopwatch.isRunning()) {
            this.mBufferStopwatch.stop();
        }
    }

    public void reset() {
        clearCurrentBreakBuffers();
        resetBufferStopwatch();
    }

    public TimeSpan getBufferStopwatchTime() {
        return new TimeSpan(this.mBufferStopwatch);
    }
}
