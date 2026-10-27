package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class AdEnabledPlaybackTimer {
    private final TimeSpan mTimeout;
    private Timer mTimer;

    public AdEnabledPlaybackTimer(TimeSpan timeSpan) {
        this.mTimeout = timeSpan;
    }

    public void start(TimerTask timerTask) {
        if (this.mTimeout.equals(TimeSpan.MAX_VALUE)) {
            return;
        }
        Preconditions.checkNotNull(timerTask);
        Timer timerNewTimer = newTimer();
        this.mTimer = timerNewTimer;
        timerNewTimer.schedule(timerTask, this.mTimeout.getTotalMilliseconds());
    }

    public void stop() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    @Nonnull
    public TimeSpan getTimeout() {
        return this.mTimeout;
    }

    protected Timer newTimer() {
        return new Timer();
    }
}
