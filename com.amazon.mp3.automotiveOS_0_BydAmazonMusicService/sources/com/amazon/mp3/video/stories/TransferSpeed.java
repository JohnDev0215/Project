package com.amazon.mp3.video.stories;

import kotlin.Metadata;

/* JADX INFO: compiled from: TransferSpeedManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/video/stories/TransferSpeed;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "speed", "J", "getSpeed", "()J", "startTime", "getStartTime", "<init>", "(JJ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class TransferSpeed {
    private final long speed;
    private final long startTime;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TransferSpeed)) {
            return false;
        }
        TransferSpeed transferSpeed = (TransferSpeed) other;
        return this.speed == transferSpeed.speed && this.startTime == transferSpeed.startTime;
    }

    public int hashCode() {
        return (Long.hashCode(this.speed) * 31) + Long.hashCode(this.startTime);
    }

    public String toString() {
        return "TransferSpeed(speed=" + this.speed + ", startTime=" + this.startTime + ')';
    }

    public TransferSpeed(long j, long j2) {
        this.speed = j;
        this.startTime = j2;
    }

    public final long getSpeed() {
        return this.speed;
    }

    public final long getStartTime() {
        return this.startTime;
    }
}
