package com.spotify.localfiles.localfiles;

import p.c71;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class LocalContentSummary {
    private final int numberOfTracks;
    private final long totalDurationSeconds;

    public LocalContentSummary(@z61(name = "numberOfTracks") int i, @z61(name = "totalDurationSeconds") long j) {
        this.numberOfTracks = i;
        this.totalDurationSeconds = j;
    }

    public static /* synthetic */ LocalContentSummary copy$default(LocalContentSummary localContentSummary, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = localContentSummary.numberOfTracks;
        }
        if ((i2 & 2) != 0) {
            j = localContentSummary.totalDurationSeconds;
        }
        return localContentSummary.copy(i, j);
    }

    public final int component1() {
        return this.numberOfTracks;
    }

    public final long component2() {
        return this.totalDurationSeconds;
    }

    public final LocalContentSummary copy(@z61(name = "numberOfTracks") int i, @z61(name = "totalDurationSeconds") long j) {
        return new LocalContentSummary(i, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocalContentSummary)) {
            return false;
        }
        LocalContentSummary localContentSummary = (LocalContentSummary) obj;
        return this.numberOfTracks == localContentSummary.numberOfTracks && this.totalDurationSeconds == localContentSummary.totalDurationSeconds;
    }

    public final int getNumberOfTracks() {
        return this.numberOfTracks;
    }

    public final long getTotalDurationSeconds() {
        return this.totalDurationSeconds;
    }

    public int hashCode() {
        return Long.hashCode(this.totalDurationSeconds) + (Integer.hashCode(this.numberOfTracks) * 31);
    }

    public String toString() {
        return "LocalContentSummary(numberOfTracks=" + this.numberOfTracks + ", totalDurationSeconds=" + this.totalDurationSeconds + ')';
    }
}
