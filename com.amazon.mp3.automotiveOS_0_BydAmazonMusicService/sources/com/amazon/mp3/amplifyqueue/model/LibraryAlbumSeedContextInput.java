package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryAlbumSeedContextInput {
    private final String albumName;
    private final String artistName;
    private final MetricsContextInput metricsContext;
    private final Boolean useSortFields;

    public interface AlbumNameStep {
    }

    public interface ArtistNameStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements AlbumNameStep, ArtistNameStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public Boolean getUseSortFields() {
        return this.useSortFields;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LibraryAlbumSeedContextInput.class != obj.getClass()) {
            return false;
        }
        LibraryAlbumSeedContextInput libraryAlbumSeedContextInput = (LibraryAlbumSeedContextInput) obj;
        return ObjectsCompat.equals(getAlbumName(), libraryAlbumSeedContextInput.getAlbumName()) && ObjectsCompat.equals(getArtistName(), libraryAlbumSeedContextInput.getArtistName()) && ObjectsCompat.equals(getUseSortFields(), libraryAlbumSeedContextInput.getUseSortFields()) && ObjectsCompat.equals(getMetricsContext(), libraryAlbumSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getAlbumName() + getArtistName() + getUseSortFields() + getMetricsContext()).hashCode();
    }
}
