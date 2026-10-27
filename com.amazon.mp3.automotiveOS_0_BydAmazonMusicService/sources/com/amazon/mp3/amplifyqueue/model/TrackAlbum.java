package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackAlbum {
    private final String artistName;
    private final List<TrackArtist> artists;
    private final String asin;
    private final String name;

    public interface ArtistsStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements ArtistsStep, NameStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface NameStep {
    }

    public String getArtistName() {
        return this.artistName;
    }

    public List<TrackArtist> getArtists() {
        return this.artists;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackAlbum.class != obj.getClass()) {
            return false;
        }
        TrackAlbum trackAlbum = (TrackAlbum) obj;
        return ObjectsCompat.equals(getArtistName(), trackAlbum.getArtistName()) && ObjectsCompat.equals(getArtists(), trackAlbum.getArtists()) && ObjectsCompat.equals(getAsin(), trackAlbum.getAsin()) && ObjectsCompat.equals(getName(), trackAlbum.getName());
    }

    public int hashCode() {
        return (getArtistName() + getArtists() + getAsin() + getName()).hashCode();
    }
}
