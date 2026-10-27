package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SmartLibraryPlaylistSeed {
    private final SmartLibraryPlaylistIdentifierEnum id;

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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SmartLibraryPlaylistSeed.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getId(), ((SmartLibraryPlaylistSeed) obj).getId());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId());
        return sb.toString().hashCode();
    }
}
