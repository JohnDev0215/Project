package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SmartLibraryPlaylistSeedContextInput {
    private final SmartLibraryPlaylistIdentifierEnum id;
    private final MetricsContextInput metricsContext;

    public interface BuildStep {
    }

    public static class Builder implements IdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface IdStep {
    }

    public SmartLibraryPlaylistIdentifierEnum getId() {
        return this.id;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SmartLibraryPlaylistSeedContextInput.class != obj.getClass()) {
            return false;
        }
        SmartLibraryPlaylistSeedContextInput smartLibraryPlaylistSeedContextInput = (SmartLibraryPlaylistSeedContextInput) obj;
        return ObjectsCompat.equals(getId(), smartLibraryPlaylistSeedContextInput.getId()) && ObjectsCompat.equals(getMetricsContext(), smartLibraryPlaylistSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId());
        sb.append(getMetricsContext());
        return sb.toString().hashCode();
    }
}
