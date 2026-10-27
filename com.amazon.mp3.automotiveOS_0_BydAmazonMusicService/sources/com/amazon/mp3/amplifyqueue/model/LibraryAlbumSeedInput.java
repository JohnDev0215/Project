package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryAlbumSeedInput {
    private final String albumName;
    private final String artistName;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LibraryAlbumSeedInput.class != obj.getClass()) {
            return false;
        }
        LibraryAlbumSeedInput libraryAlbumSeedInput = (LibraryAlbumSeedInput) obj;
        return ObjectsCompat.equals(getAlbumName(), libraryAlbumSeedInput.getAlbumName()) && ObjectsCompat.equals(getArtistName(), libraryAlbumSeedInput.getArtistName()) && ObjectsCompat.equals(getUseSortFields(), libraryAlbumSeedInput.getUseSortFields());
    }

    public int hashCode() {
        return (getAlbumName() + getArtistName() + getUseSortFields()).hashCode();
    }
}
