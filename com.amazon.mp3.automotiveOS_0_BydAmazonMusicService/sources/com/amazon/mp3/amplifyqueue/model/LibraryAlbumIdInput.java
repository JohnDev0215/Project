package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryAlbumIdInput {
    private final String albumName;
    private final String artistName;

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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LibraryAlbumIdInput.class != obj.getClass()) {
            return false;
        }
        LibraryAlbumIdInput libraryAlbumIdInput = (LibraryAlbumIdInput) obj;
        return ObjectsCompat.equals(getAlbumName(), libraryAlbumIdInput.getAlbumName()) && ObjectsCompat.equals(getArtistName(), libraryAlbumIdInput.getArtistName());
    }

    public int hashCode() {
        return (getAlbumName() + getArtistName()).hashCode();
    }
}
