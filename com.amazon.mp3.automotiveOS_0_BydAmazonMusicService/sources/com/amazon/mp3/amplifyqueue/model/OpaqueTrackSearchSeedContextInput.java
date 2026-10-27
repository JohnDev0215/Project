package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class OpaqueTrackSearchSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final String opaqueTrackSearch;

    public interface BuildStep {
    }

    public static class Builder implements OpaqueTrackSearchStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface OpaqueTrackSearchStep {
    }

    public String getOpaqueTrackSearch() {
        return this.opaqueTrackSearch;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || OpaqueTrackSearchSeedContextInput.class != obj.getClass()) {
            return false;
        }
        OpaqueTrackSearchSeedContextInput opaqueTrackSearchSeedContextInput = (OpaqueTrackSearchSeedContextInput) obj;
        return ObjectsCompat.equals(getOpaqueTrackSearch(), opaqueTrackSearchSeedContextInput.getOpaqueTrackSearch()) && ObjectsCompat.equals(getMetricsContext(), opaqueTrackSearchSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getOpaqueTrackSearch() + getMetricsContext()).hashCode();
    }
}
