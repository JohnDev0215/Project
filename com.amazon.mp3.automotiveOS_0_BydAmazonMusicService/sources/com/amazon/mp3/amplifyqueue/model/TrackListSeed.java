package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackListSeed {
    private final List<TrackReference> tracks;

    public interface BuildStep {
    }

    public static class Builder implements TracksStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface TracksStep {
    }

    public List<TrackReference> getTracks() {
        return this.tracks;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackListSeed.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getTracks(), ((TrackListSeed) obj).getTracks());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTracks());
        return sb.toString().hashCode();
    }
}
