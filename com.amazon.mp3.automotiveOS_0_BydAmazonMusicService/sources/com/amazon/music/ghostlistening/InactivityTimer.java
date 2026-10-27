package com.amazon.music.ghostlistening;

import androidx.annotation.VisibleForTesting;
import com.amazon.music.ghostlistening.domain.InactivityTimerStatus;
import com.amazon.music.ghostlistening.domain.TrackInfo;
import com.amazon.music.ghostlistening.exception.InactivityThresholdReachedException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.greenrobot.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class InactivityTimer {
    private static final long DEFAULT_INACTIVITY_THRESHOLD_SECONDS = TimeUnit.HOURS.toSeconds(3);
    private long inactiveTime;
    private final long inactivityThreshold;
    private final Logger logger;
    private InactivityTimerStatus status;

    public InactivityTimer() {
        this(DEFAULT_INACTIVITY_THRESHOLD_SECONDS);
    }

    public InactivityTimer(long j) {
        this.logger = LoggerFactory.getLogger(InactivityTimer.class.getSimpleName());
        Validate.notNull(Long.valueOf(j));
        this.inactivityThreshold = j;
        if (j == -1 || j == -2) {
            this.status = InactivityTimerStatus.DISABLED;
        } else if (j >= 0) {
            this.status = InactivityTimerStatus.ENABLED;
        } else {
            throw new IllegalArgumentException("InactivityTimer must have a threshold of a positive number for an enabled timer or -1 or -2 for a disabled timer.");
        }
        EventBus.getDefault().register(new GhostListeningAppEventSubscriber(this));
    }

    @VisibleForTesting
    long getInactivityThreshold() {
        return this.inactivityThreshold;
    }

    @VisibleForTesting
    InactivityTimerStatus getStatus() {
        return this.status;
    }

    @VisibleForTesting
    void setStatus(InactivityTimerStatus inactivityTimerStatus) {
        this.status = inactivityTimerStatus;
    }

    @VisibleForTesting
    long getInactiveTime() {
        return this.inactiveTime;
    }

    @VisibleForTesting
    void setInactiveTime(long j) {
        this.inactiveTime = j;
    }

    private void incrementInactiveTime(long j) {
        this.inactiveTime += j;
    }

    @VisibleForTesting
    void start() {
        if (getStatus() != InactivityTimerStatus.DISABLED) {
            InactivityTimerStatus inactivityTimerStatus = InactivityTimerStatus.ENABLED;
            setStatus(inactivityTimerStatus);
            this.logger.trace("Inactivity timer status has been set to " + inactivityTimerStatus.toString());
        }
    }

    @VisibleForTesting
    void reset() {
        this.logger.trace("InactivityTimer about to be reset from " + getInactiveTime());
        setInactiveTime(0L);
    }

    @VisibleForTesting
    void increment(TrackInfo trackInfo) {
        Validate.notNull(trackInfo);
        if (trackInfo.isPrime() == null) {
            trackInfo.setPrime(Boolean.TRUE);
        }
        if (trackInfo.isOwned() == null) {
            trackInfo.setOwned(Boolean.FALSE);
        }
        if (getStatus() == InactivityTimerStatus.ENABLED && !trackInfo.isOwned().booleanValue() && trackInfo.isPrime().booleanValue()) {
            incrementInactiveTime(trackInfo.getDuration() - trackInfo.getStartingPosition());
            if (getInactiveTime() >= getInactivityThreshold()) {
                setStatus(InactivityTimerStatus.THRESHOLD_REACHED);
            }
        }
    }

    public void incrementForTrack(TrackInfo trackInfo) throws InactivityThresholdReachedException {
        Validate.notNull(trackInfo);
        if (InactivityTimerStatus.THRESHOLD_REACHED != getStatus()) {
            increment(trackInfo);
            this.logger.info("InactivityTimer incremented to " + getInactiveTime());
            return;
        }
        throw new InactivityThresholdReachedException("Time of inactivity is " + getInactiveTime(), getInactivityThreshold(), getInactiveTime());
    }

    public void resetTimerToDefaultState() {
        reset();
        start();
    }
}
