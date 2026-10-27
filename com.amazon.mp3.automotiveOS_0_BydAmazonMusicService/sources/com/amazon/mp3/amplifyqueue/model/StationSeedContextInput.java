package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class StationSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final String seedId;
    private final StationSeedTypeEnum seedType;

    public interface BuildStep {
    }

    public static class Builder implements SeedTypeStep, SeedIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface SeedIdStep {
    }

    public interface SeedTypeStep {
    }

    public StationSeedTypeEnum getSeedType() {
        return this.seedType;
    }

    public String getSeedId() {
        return this.seedId;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StationSeedContextInput.class != obj.getClass()) {
            return false;
        }
        StationSeedContextInput stationSeedContextInput = (StationSeedContextInput) obj;
        return ObjectsCompat.equals(getSeedType(), stationSeedContextInput.getSeedType()) && ObjectsCompat.equals(getSeedId(), stationSeedContextInput.getSeedId()) && ObjectsCompat.equals(getMetricsContext(), stationSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getSeedType() + getSeedId() + getMetricsContext()).hashCode();
    }
}
