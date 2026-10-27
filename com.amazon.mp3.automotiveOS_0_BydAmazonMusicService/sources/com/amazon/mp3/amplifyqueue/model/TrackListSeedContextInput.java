package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackListSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final List<TrackReferenceInput> tracks;

    public interface BuildStep {
    }

    public static class Builder implements TracksStep, MetricsContextStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface MetricsContextStep {
    }

    public interface TracksStep {
    }

    public List<TrackReferenceInput> getTracks() {
        return this.tracks;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackListSeedContextInput.class != obj.getClass()) {
            return false;
        }
        TrackListSeedContextInput trackListSeedContextInput = (TrackListSeedContextInput) obj;
        return ObjectsCompat.equals(getTracks(), trackListSeedContextInput.getTracks()) && ObjectsCompat.equals(getMetricsContext(), trackListSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTracks());
        sb.append(getMetricsContext());
        return sb.toString().hashCode();
    }
}
