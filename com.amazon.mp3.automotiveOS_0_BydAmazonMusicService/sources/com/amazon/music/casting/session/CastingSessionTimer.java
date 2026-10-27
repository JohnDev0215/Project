package com.amazon.music.casting.session;

import android.os.CountDownTimer;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class CastingSessionTimer {
    private TerminationReason terminationReason;
    protected CountDownTimer timer;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) CastingSessionTimer.class);
    private static final long COUNT_DOWN_INTERVAL = TimeUnit.SECONDS.toMillis(1);

    public interface CastingSessionTimerListener {
        void onTimerExpired(TerminationReason terminationReason);
    }

    public CastingSessionTimer(CastingSessionTimerListener castingSessionTimerListener, long j, TerminationReason terminationReason) {
        Validate.notNull(castingSessionTimerListener);
        Validate.isTrue(j > 0);
        this.timer = createCountDownTimer(castingSessionTimerListener, j);
        this.terminationReason = terminationReason;
        LOG.debug("Created CastingSessionTimer with timeoutMillis: " + j);
    }

    protected CountDownTimer createCountDownTimer(final CastingSessionTimerListener castingSessionTimerListener, long j) {
        return new CountDownTimer(j, COUNT_DOWN_INTERVAL) { // from class: com.amazon.music.casting.session.CastingSessionTimer.1
            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                castingSessionTimerListener.onTimerExpired(CastingSessionTimer.this.terminationReason);
            }
        };
    }

    public synchronized void start() {
        this.timer.start();
    }

    public synchronized void stop() {
        this.timer.cancel();
    }
}
