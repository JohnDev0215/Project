package com.amazon.avod.media.framework.util;

import android.os.CountDownTimer;
import com.amazon.avod.threading.Tickers;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class PausableCountDownTimer {
    private long mDuration;
    private Runnable mLatestTask;
    private CountDownTimer mTimer;
    boolean mUseManualTrigger = false;
    private TimerState mState = TimerState.UNINITIALIZED;
    private final Stopwatch mStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());
    private final Object mLock = new Object();

    private enum TimerState {
        UNINITIALIZED,
        RUNNING,
        PAUSED
    }

    public void set(long j) {
        synchronized (this.mLock) {
            Preconditions.checkArgument(j > 0);
            cancel();
            this.mDuration = j;
            this.mState = TimerState.PAUSED;
        }
    }

    public void start(final Runnable runnable) {
        synchronized (this.mLock) {
            Preconditions.checkNotNull(runnable);
            if (this.mState != TimerState.PAUSED) {
                return;
            }
            this.mStopwatch.reset();
            this.mState = TimerState.RUNNING;
            this.mLatestTask = runnable;
            long j = this.mDuration;
            this.mTimer = new CountDownTimer(j, j) { // from class: com.amazon.avod.media.framework.util.PausableCountDownTimer.1
                @Override // android.os.CountDownTimer
                public void onTick(long j2) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    PausableCountDownTimer.this.cancel();
                    runnable.run();
                }
            };
            this.mStopwatch.start();
            if (!this.mUseManualTrigger) {
                this.mTimer.start();
            }
        }
    }

    public void pause() {
        synchronized (this.mLock) {
            if (this.mState != TimerState.RUNNING) {
                return;
            }
            this.mStopwatch.stop();
            this.mTimer.cancel();
            this.mDuration = getRemainingTime();
            this.mState = TimerState.PAUSED;
        }
    }

    public void cancel() {
        synchronized (this.mLock) {
            if (this.mState == TimerState.RUNNING) {
                this.mTimer.cancel();
            }
            this.mLatestTask = null;
            this.mState = TimerState.UNINITIALIZED;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.framework.util.PausableCountDownTimer$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$framework$util$PausableCountDownTimer$TimerState;

        static {
            int[] iArr = new int[TimerState.values().length];
            $SwitchMap$com$amazon$avod$media$framework$util$PausableCountDownTimer$TimerState = iArr;
            try {
                iArr[TimerState.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$framework$util$PausableCountDownTimer$TimerState[TimerState.PAUSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public long getRemainingTime() {
        long jElapsed;
        long j;
        synchronized (this.mLock) {
            int i = AnonymousClass2.$SwitchMap$com$amazon$avod$media$framework$util$PausableCountDownTimer$TimerState[this.mState.ordinal()];
            if (i == 1) {
                jElapsed = this.mDuration - this.mStopwatch.elapsed(TimeUnit.MILLISECONDS);
            } else {
                jElapsed = i != 2 ? 0L : this.mDuration;
            }
            j = jElapsed > 0 ? jElapsed : 0L;
        }
        return j;
    }
}
