package com.amazon.mp3.util;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CountDownTimer {
    private final long countDownInterval;
    private final long millisInFuture;
    private long pauseTime;
    private long stopTimeInFuture;
    private boolean mCancelled = false;
    private boolean mPaused = false;
    private Handler mHandler = new CountDownTimerHandler(this);

    public abstract void onFinish();

    public abstract void onTick(long millisUntilFinished);

    public CountDownTimer(final long millisInFuture, final long countDownInterval) {
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
    }

    public final synchronized void cancel() {
        if (!this.mCancelled) {
            this.mHandler.removeMessages(1);
            this.mCancelled = true;
        }
    }

    public final synchronized CountDownTimer start() {
        if (this.millisInFuture <= 0) {
            onFinish();
            return this;
        }
        if (!this.mCancelled) {
            this.mHandler.removeMessages(1);
        }
        this.stopTimeInFuture = SystemClock.elapsedRealtime() + this.millisInFuture;
        this.mCancelled = false;
        this.mPaused = false;
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1));
        return this;
    }

    public synchronized void pause() {
        if (!this.mPaused) {
            this.mHandler.removeMessages(1);
            this.pauseTime = this.stopTimeInFuture - SystemClock.elapsedRealtime();
            this.mPaused = true;
        }
    }

    public synchronized void resume() {
        if (this.mPaused) {
            this.stopTimeInFuture = this.pauseTime + SystemClock.elapsedRealtime();
            this.mPaused = false;
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1));
        }
    }

    private static class CountDownTimerHandler extends Handler {
        private final WeakReference<CountDownTimer> countDownTimerWeakReference;

        public CountDownTimerHandler(final CountDownTimer countDownTimer) {
            this.countDownTimerWeakReference = new WeakReference<>(countDownTimer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            CountDownTimer countDownTimer = this.countDownTimerWeakReference.get();
            if (countDownTimer != null) {
                synchronized (countDownTimer) {
                    if (!countDownTimer.mPaused) {
                        long jElapsedRealtime = countDownTimer.stopTimeInFuture - SystemClock.elapsedRealtime();
                        if (jElapsedRealtime <= 0) {
                            countDownTimer.onFinish();
                        } else if (jElapsedRealtime < countDownTimer.countDownInterval) {
                            sendMessageDelayed(obtainMessage(1), jElapsedRealtime);
                        } else {
                            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                            countDownTimer.onTick(jElapsedRealtime);
                            long jElapsedRealtime3 = (jElapsedRealtime2 + countDownTimer.countDownInterval) - SystemClock.elapsedRealtime();
                            while (jElapsedRealtime3 < 0) {
                                jElapsedRealtime3 += countDownTimer.countDownInterval;
                            }
                            if (!countDownTimer.mCancelled) {
                                sendMessageDelayed(obtainMessage(1), jElapsedRealtime3);
                            }
                        }
                    }
                }
            }
        }
    }
}
