package com.amazon.podcast.media.playback;

import android.os.CountDownTimer;
import com.amazon.podcast.Podcast;

/* JADX INFO: loaded from: classes5.dex */
public class SleepTimer {
    private CountDownTimer timer;
    private final Object timerLock = new Object();

    public void stopTimer() {
        Podcast.getPlayback().setSleepTimerRemainingDuration(0L);
        synchronized (this.timerLock) {
            CountDownTimer countDownTimer = this.timer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.timer = null;
            }
        }
    }

    public void startTimer(final long msUntilSleep) {
        Playback playback = Podcast.getPlayback();
        stopTimer();
        playback.setSleepTimerRemainingDuration(msUntilSleep);
        synchronized (this.timerLock) {
            CountDownTimer countDownTimer = this.timer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.timer = null;
            }
            SleepCountDownTimer sleepCountDownTimer = new SleepCountDownTimer(playback, msUntilSleep, 1000L);
            this.timer = sleepCountDownTimer;
            sleepCountDownTimer.start();
        }
    }
}
