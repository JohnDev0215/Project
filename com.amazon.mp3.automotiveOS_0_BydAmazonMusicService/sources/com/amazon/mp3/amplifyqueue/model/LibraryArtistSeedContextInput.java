package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryArtistSeedContextInput {
    private final String artistName;
    private final MetricsContextInput metricsContext;

    public interface ArtistNameStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements ArtistNameStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public String getArtistName() {
        return this.artistName;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LibraryArtistSeedContextInput.class != obj.getClass()) {
            return false;
        }
        LibraryArtistSeedContextInput libraryArtistSeedContextInput = (LibraryArtistSeedContextInput) obj;
        return ObjectsCompat.equals(getArtistName(), libraryArtistSeedContextInput.getArtistName()) && ObjectsCompat.equals(getMetricsContext(), libraryArtistSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getArtistName() + getMetricsContext()).hashCode();
    }
}
