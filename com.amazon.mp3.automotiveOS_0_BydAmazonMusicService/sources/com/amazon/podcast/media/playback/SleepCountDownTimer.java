package com.amazon.podcast.media.playback;

import android.os.CountDownTimer;

/* JADX INFO: loaded from: classes5.dex */
class SleepCountDownTimer extends CountDownTimer {
    private final Playback playback;

    public SleepCountDownTimer(final Playback playback, final long millisInFuture, final long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.playback = playback;
    }

    @Override // android.os.CountDownTimer
    public void onTick(final long remainingDuration) {
        this.playback.setSleepTimerRemainingDuration(remainingDuration);
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.playback.onSleepTimerElapsed();
    }
}
