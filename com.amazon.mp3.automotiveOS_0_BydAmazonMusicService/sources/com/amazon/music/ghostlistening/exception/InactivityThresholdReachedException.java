package com.amazon.music.ghostlistening.exception;

/* JADX INFO: loaded from: classes4.dex */
public class InactivityThresholdReachedException extends Exception {
    private final long inactivityDuration;
    private final long inactivityThreshold;

    public InactivityThresholdReachedException(String str, long j, long j2) {
        super(str);
        this.inactivityThreshold = j;
        this.inactivityDuration = j2;
    }

    public long getInactivityThreshold() {
        return this.inactivityThreshold;
    }

    public long getInactivityDuration() {
        return this.inactivityDuration;
    }
}
