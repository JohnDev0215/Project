package com.amazon.music.skyfire.ui.managers;

import android.os.CountDownTimer;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CountDownTimerManager {
    private boolean isRunning = false;
    private long msLeftUntilFinished;
    private long tickIntervalMs;
    private CountDownTimer timer;

    public abstract void onFinished();

    public abstract void onTicked(long j);

    public CountDownTimerManager(long j, long j2) {
        this.msLeftUntilFinished = j;
        this.tickIntervalMs = j2;
    }

    public void start() {
        recreateTimer();
        this.timer.start();
        this.isRunning = true;
    }

    public void stop() {
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.timer = null;
        }
        this.isRunning = false;
    }

    public void restart(long j) {
        reset(j);
        start();
    }

    public void reset(long j) {
        stop();
        this.msLeftUntilFinished = j;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public long getMsLeftUntilFinished() {
        return this.msLeftUntilFinished;
    }

    private void recreateTimer() {
        stop();
        this.timer = new CountDownTimer(this.msLeftUntilFinished, this.tickIntervalMs) { // from class: com.amazon.music.skyfire.ui.managers.CountDownTimerManager.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                CountDownTimerManager.this.msLeftUntilFinished = j;
                CountDownTimerManager.this.onTicked(j);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                CountDownTimerManager.this.isRunning = false;
                CountDownTimerManager.this.msLeftUntilFinished = 0L;
                CountDownTimerManager.this.onFinished();
            }
        };
    }
}
