package com.byd.vrassistant.utils;

import android.os.Handler;

/* JADX INFO: loaded from: classes2.dex */
public class InterrupterTimer {
    private boolean isRunning = false;
    private final Handler mHandler = new Handler();
    Runnable wrapTimer;

    public InterrupterTimer(final Runnable runnable) {
        this.wrapTimer = new Runnable() { // from class: com.byd.vrassistant.utils.InterrupterTimer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m186lambda$new$0$combydvrassistantutilsInterrupterTimer(runnable);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$new$0$com-byd-vrassistant-utils-InterrupterTimer, reason: not valid java name */
    /* synthetic */ void m186lambda$new$0$combydvrassistantutilsInterrupterTimer(Runnable runnable) {
        this.isRunning = false;
        runnable.run();
    }

    public final synchronized void startTimer(Long l) {
        this.isRunning = true;
        this.mHandler.postDelayed(this.wrapTimer, l.longValue());
    }

    public final synchronized void interruptTimer() {
        this.isRunning = false;
        this.mHandler.removeCallbacks(this.wrapTimer);
    }

    public boolean isTimerRunning() {
        return this.isRunning;
    }
}
