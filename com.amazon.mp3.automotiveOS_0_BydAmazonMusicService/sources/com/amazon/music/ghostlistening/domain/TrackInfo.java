package com.amazon.music.ghostlistening.domain;

/* JADX INFO: loaded from: classes4.dex */
public class TrackInfo {
    private long duration;
    private Boolean owned;
    private Boolean prime;
    private long startingPosition;

    public Boolean isOwned() {
        return this.owned;
    }

    public void setOwned(Boolean bool) {
        this.owned = bool;
    }

    public Boolean isPrime() {
        return this.prime;
    }

    public void setPrime(Boolean bool) {
        this.prime = bool;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public long getStartingPosition() {
        return this.startingPosition;
    }

    public void setStartingPosition(long j) {
        this.startingPosition = j;
    }
}
