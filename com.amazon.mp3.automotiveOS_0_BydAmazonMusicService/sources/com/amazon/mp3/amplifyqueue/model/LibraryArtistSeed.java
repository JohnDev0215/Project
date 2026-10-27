package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class LibraryArtistSeed {
    private final String artistName;

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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LibraryArtistSeed.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getArtistName(), ((LibraryArtistSeed) obj).getArtistName());
    }

    public int hashCode() {
        return (getArtistName()).hashCode();
    }
}
