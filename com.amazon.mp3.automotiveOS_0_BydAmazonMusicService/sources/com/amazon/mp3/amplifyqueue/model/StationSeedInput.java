package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class StationSeedInput {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StationSeedInput.class != obj.getClass()) {
            return false;
        }
        StationSeedInput stationSeedInput = (StationSeedInput) obj;
        return ObjectsCompat.equals(getSeedType(), stationSeedInput.getSeedType()) && ObjectsCompat.equals(getSeedId(), stationSeedInput.getSeedId());
    }

    public int hashCode() {
        return (getSeedType() + getSeedId()).hashCode();
    }
}
