package com.amazon.video.sdk.avod.playbackclient.utils;

import android.os.Handler;
import android.os.Looper;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class LoopRunner {
    private final Handler mHandler;
    private final long mHeartBeatIntervalMillis;
    private AtomicBoolean mIsTicking;
    private final Runnable mProgressTick;
    private final Runnable mTask;

    private LoopRunner(long j, @Nonnull Runnable runnable) {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mIsTicking = new AtomicBoolean(false);
        this.mProgressTick = new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.utils.LoopRunner.1
            @Override // java.lang.Runnable
            public void run() {
                if (LoopRunner.this.mIsTicking.get()) {
                    LoopRunner.this.mTask.run();
                    LoopRunner.this.mHandler.postDelayed(LoopRunner.this.mProgressTick, LoopRunner.this.mHeartBeatIntervalMillis);
                }
            }
        };
        Preconditions.checkArgument(j > 0, "The interval value between each update should be possitive.");
        this.mTask = (Runnable) Preconditions.checkNotNull(runnable, "The task to be run periodically cannot be NULL");
        this.mHeartBeatIntervalMillis = j;
    }

    public void start() {
        DLog.devf("Starting loop runner for %s", this.mTask);
        if (this.mIsTicking.getAndSet(true)) {
            return;
        }
        this.mHandler.post(this.mProgressTick);
    }

    public void stop() {
        DLog.devf("Stopping loop runner for %s", this.mTask);
        if (this.mIsTicking.getAndSet(false)) {
            this.mHandler.removeCallbacks(this.mProgressTick);
        }
    }

    public static class Factory {
        public LoopRunner newLoopRunner(long j, Runnable runnable) {
            return new LoopRunner(j, runnable);
        }
    }
}
