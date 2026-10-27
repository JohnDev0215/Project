package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackListSeedInput {
    private final List<TrackReferenceInput> tracks;

    public interface BuildStep {
    }

    public static class Builder implements TracksStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface TracksStep {
    }

    public List<TrackReferenceInput> getTracks() {
        return this.tracks;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackListSeedInput.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getTracks(), ((TrackListSeedInput) obj).getTracks());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTracks());
        return sb.toString().hashCode();
    }
}
