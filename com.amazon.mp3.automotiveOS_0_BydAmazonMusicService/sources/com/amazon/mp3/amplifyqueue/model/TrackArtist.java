package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackArtist {
    private final String asin;
    private final String contributorAsin;
    private final String name;
    private final List<ArtistRole> roles;

    public interface AsinStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements AsinStep, NameStep, RolesStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface NameStep {
    }

    public interface RolesStep {
    }

    public String getAsin() {
        return this.asin;
    }

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public String getName() {
        return this.name;
    }

    public List<ArtistRole> getRoles() {
        return this.roles;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackArtist.class != obj.getClass()) {
            return false;
        }
        TrackArtist trackArtist = (TrackArtist) obj;
        return ObjectsCompat.equals(getAsin(), trackArtist.getAsin()) && ObjectsCompat.equals(getContributorAsin(), trackArtist.getContributorAsin()) && ObjectsCompat.equals(getName(), trackArtist.getName()) && ObjectsCompat.equals(getRoles(), trackArtist.getRoles());
    }

    public int hashCode() {
        return (getAsin() + getContributorAsin() + getName() + getRoles()).hashCode();
    }
}
